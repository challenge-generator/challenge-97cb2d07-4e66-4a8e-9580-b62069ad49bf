package com.bank.transaction.application.service;

import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.model.Transaction.TransactionStatus;
import com.bank.transaction.domain.model.Transaction.TransactionType;
import com.bank.transaction.domain.port.AuthenticationService;
import com.bank.transaction.domain.port.TransactionRepository;
import com.bank.transaction.infrastructure.exception.TransactionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TransactionService - Pruebas Unitarias")
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private TransactionService transactionService;

    private Transaction transaccionValida;
    private Transaction transaccionAltoValor;
    private UUID cuentaId;
    private UUID transaccionId;

    @BeforeEach
    void setUp() {
        cuentaId = UUID.randomUUID();
        transaccionId = UUID.randomUUID();
        
        transaccionValida = new Transaction(
            transaccionId,
            cuentaId,
            TransactionType.TRANSFER,
            new BigDecimal("500.00"),
            "USD",
            "CUENTA_ORIGEN",
            "CUENTA_DESTINO",
            TransactionStatus.COMPLETED,
            LocalDateTime.now(),
            "USER_001"
        );
        
        transaccionAltoValor = new Transaction(
            UUID.randomUUID(),
            cuentaId,
            TransactionType.TRANSFER,
            new BigDecimal("15000.00"),
            "USD",
            "CUENTA_ORIGEN",
            "CUENTA_DESTINO",
            TransactionStatus.PENDING,
            LocalDateTime.now(),
            "USER_001"
        );
    }

    @Nested
    @DisplayName("Crear Transacción")
    class CrearTransaccion {

        @Test
        @DisplayName("Debe crear transacción exitosamente con token válido")
        void debeCrearTransaccionExitosamente() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.save(any(Transaction.class))).thenReturn(transaccionValida);

            Transaction resultado = transactionService.createTransaction(transaccionValida, tokenValido);

            assertNotNull(resultado);
            assertEquals(transaccionId, resultado.id());
            assertEquals(TransactionStatus.COMPLETED, resultado.status());
            verify(transactionRepository).save(any(Transaction.class));
            verify(authenticationService).validateToken(tokenValido);
        }

        @Test
        @DisplayName("Debe rechazar creación con token inválido")
        void debeRechazarCreacionConTokenInvalido() {
            String tokenInvalido = "Bearer token-invalido";
            when(authenticationService.validateToken(tokenInvalido)).thenReturn(Set.of());

            TransactionException exception = assertThrows(
                TransactionException.class,
                () -> transactionService.createTransaction(transaccionValida, tokenInvalido)
            );

            assertEquals("Token de autenticación inválido", exception.getMessage());
            verify(transactionRepository, never()).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe rechazar creación sin token")
        void debeRechazarCreacionSinToken() {
            TransactionException exception = assertThrows(
                TransactionException.class,
                () -> transactionService.createTransaction(transaccionValida, null)
            );

            assertEquals("Token de autenticación requerido", exception.getMessage());
            verify(transactionRepository, never()).save(any(Transaction.class));
        }

        @Test
        @DisplayName("Debe rechazar creación con monto negativo")
        void debeRechazarCreacionConMontoNegativo() {
            Transaction transaccionMontoNegativo = new Transaction(
                transaccionId,
                cuentaId,
                TransactionType.TRANSFER,
                new BigDecimal("-100.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));

            TransactionException exception = assertThrows(
                TransactionException.class,
                () -> transactionService.createTransaction(transaccionMontoNegativo, tokenValido)
            );

            assertEquals("El monto de la transacción debe ser positivo", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Obtener Transacción por ID")
    class ObtenerTransaccionPorId {

        @Test
        @DisplayName("Debe obtener transacción existente")
        void debeObtenerTransaccionExistente() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findById(transaccionId)).thenReturn(Optional.of(transaccionValida));

            Optional<Transaction> resultado = transactionService.getTransactionById(transaccionId, tokenValido);

            assertTrue(resultado.isPresent());
            assertEquals(transaccionId, resultado.get().id());
            verify(transactionRepository).findById(transaccionId);
        }

        @Test
        @DisplayName("Debe retornar vacío para transacción no existente")
        void debeRetornarVacioParaTransaccionNoExistente() {
            UUID idNoExistente = UUID.randomUUID();
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findById(idNoExistente)).thenReturn(Optional.empty());

            Optional<Transaction> resultado = transactionService.getTransactionById(idNoExistente, tokenValido);

            assertTrue(resultado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Obtener Transacciones por Cuenta")
    class ObtenerTransaccionesPorCuenta {

        @Test
        @DisplayName("Debe obtener todas las transacciones de una cuenta")
        void debeObtenerTransaccionesDeCuenta() {
            String tokenValido = "Bearer token-valido";
            List<Transaction> transacciones = List.of(transaccionValida, transaccionAltoValor);
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findByAccountId(cuentaId)).thenReturn(transacciones);

            List<Transaction> resultado = transactionService.getTransactionsByAccount(cuentaId, tokenValido);

            assertEquals(2, resultado.size());
            verify(transactionRepository).findByAccountId(cuentaId);
        }

        @Test
        @DisplayName("Debe obtener transacciones por rango de fechas")
        void debeObtenerTransaccionesPorRangoDeFechas() {
            String tokenValido = "Bearer token-valido";
            LocalDateTime inicio = LocalDateTime.now().minusDays(7);
            LocalDateTime fin = LocalDateTime.now();
            List<Transaction> transacciones = List.of(transaccionValida);
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findByAccountIdAndDateRange(cuentaId, inicio, fin)).thenReturn(transacciones);

            List<Transaction> resultado = transactionService.getTransactionsByAccountAndDateRange(
                cuentaId, inicio, fin, tokenValido
            );

            assertEquals(1, resultado.size());
            verify(transactionRepository).findByAccountIdAndDateRange(cuentaId, inicio, fin);
        }
    }

    @Nested
    @DisplayName("Transacciones de Alto Valor")
    class TransaccionesAltoValor {

        @Test
        @DisplayName("Debe identificar transacciones de alto valor")
        void debeIdentificarTransaccionesDeAltoValor() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_ADMIN"));
            when(transactionRepository.findHighValueTransactions()).thenReturn(List.of(transaccionAltoValor));

            List<Transaction> resultado = transactionService.getHighValueTransactions(tokenValido);

            assertEquals(1, resultado.size());
            assertTrue(resultado.get(0).isHighValue());
            verify(transactionRepository).findHighValueTransactions();
        }

        @Test
        @DisplayName("Debe rechazar acceso a transacciones de alto valor sin rol admin")
        void debeRechazarAccesoSinRolAdmin() {
            String tokenUsuario = "Bearer token-usuario";
            when(authenticationService.validateToken(tokenUsuario)).thenReturn(Set.of("ROLE_USER"));
            when(authenticationService.hasRole(tokenUsuario, "ROLE_ADMIN")).thenReturn(false);

            TransactionException exception = assertThrows(
                TransactionException.class,
                () -> transactionService.getHighValueTransactions(tokenUsuario)
            );

            assertEquals("Acceso denegado. Se requiere rol ADMIN", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Validación de Roles y Permisos")
    class ValidacionRolesYPermisos {

        @Test
        @DisplayName("Debe verificar que usuario tiene acceso a sus propias transacciones")
        void debeVerificarAccesoATransaccionesPropias() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findById(transaccionId)).thenReturn(Optional.of(transaccionValida));

            boolean tieneAcceso = transactionService.hasAccessToTransaction(transaccionId, tokenValido, "USER_001");

            assertTrue(tieneAcceso);
        }

        @Test
        @DisplayName("Debe denegar acceso a transacciones de otro usuario")
        void debeDenegarAccesoATransaccionesDeOtroUsuario() {
            String tokenValido = "Bearer token-valido";
            when(authenticationService.validateToken(tokenValido)).thenReturn(Set.of("ROLE_USER"));
            when(transactionRepository.findById(transaccionId)).thenReturn(Optional.of(transaccionValida));

            boolean tieneAcceso = transactionService.hasAccessToTransaction(transaccionId, tokenValido, "USER_OTRO");

            assertFalse(tieneAcceso);
        }

        @Test
        @DisplayName("Admin debe poder acceder a cualquier transacción")
        void adminDebePoderAccederCualquerTransaccion() {
            String tokenAdmin = "Bearer token-admin";
            when(authenticationService.validateToken(tokenAdmin)).thenReturn(Set.of("ROLE_ADMIN"));
            when(authenticationService.hasRole(tokenAdmin, "ROLE_ADMIN")).thenReturn(true);
            when(transactionRepository.findById(transaccionId)).thenReturn(Optional.of(transaccionValida));

            boolean tieneAcceso = transactionService.hasAccessToTransaction(transaccionId, tokenAdmin, "USER_OTRO");

            assertTrue(tieneAcceso);
        }
    }

    @Nested
    @DisplayName("Auditoría de Transacciones")
    class AuditoriaTransacciones {

        @Test
        @DisplayName("Debe marcar transacción de alto valor para auditoría")
        void debeMarcarTransaccionAltoValorParaAuditoria() {
            assertTrue(transaccionAltoValor.requiresAudit());
        }

        @Test
        @DisplayName("Debe marcar transacción de transferencia internacional para auditoría")
        void debeMarcarTransaccionInternacionalParaAuditoria() {
            Transaction transaccionInternacional = new Transaction(
                UUID.randomUUID(),
                cuentaId,
                TransactionType.INTERNATIONAL_TRANSFER,
                new BigDecimal("1000.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO_INTERNACIONAL",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            assertTrue(transaccionInternacional.requiresAudit());
        }

        @Test
        @DisplayName("Transacción normal no requiere auditoría")
        void transaccionNormalNoRequiereAuditoria() {
            assertFalse(transaccionValida.requiresAudit());
        }
    }
}