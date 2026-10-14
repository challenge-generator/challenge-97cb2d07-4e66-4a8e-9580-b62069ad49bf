package com.bank.transaction.infrastructure.controller;

import com.bank.transaction.application.service.TransactionService;
import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.model.Transaction.TransactionStatus;
import com.bank.transaction.domain.model.Transaction.TransactionType;
import com.bank.transaction.infrastructure.exception.AuthenticationException;
import com.bank.transaction.infrastructure.exception.TransactionException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
@DisplayName("TransactionController - Pruebas de Integración")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
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
    @DisplayName("POST /api/v1/transactions - Crear Transacción")
    class CrearTransaccion {

        @Test
        @DisplayName("Debe crear transacción exitosamente con usuario autenticado")
        @WithMockUser(roles = "USER")
        void debeCrearTransaccionExitosamente() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenReturn(transaccionValida);

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(transaccionId.toString()))
                .andExpect(jsonPath("$.status").value("COMPLETED"));
        }

        @Test
        @DisplayName("Debe rechazar creación sin autenticación")
        void debeRechazarCreacionSinAutenticacion() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new AuthenticationException("Token requerido"));

            mockMvc.perform(post("/api/v1/transactions")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("Debe rechazar creación con token inválido")
        @WithMockUser(roles = "USER")
        void debeRechazarCreacionConTokenInvalido() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new TransactionException("Token de autenticación inválido"));

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe rechazar creación con monto inválido")
        @WithMockUser(roles = "USER")
        void debeRechazarCreacionConMontoInvalido() throws Exception {
            Transaction transaccionInvalida = new Transaction(
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

            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new TransactionException("El monto de la transacción debe ser positivo"));

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionInvalida)))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions/{id} - Obtener Transacción por ID")
    class ObtenerTransaccionPorId {

        @Test
        @DisplayName("Debe obtener transacción existente")
        @WithMockUser(roles = "USER")
        void debeObtenerTransaccionExistente() throws Exception {
            when(transactionService.getTransactionById(eq(transaccionId), anyString()))
                .thenReturn(Optional.of(transaccionValida));

            mockMvc.perform(get("/api/v1/transactions/{id}", transaccionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(transaccionId.toString()))
                .andExpect(jsonPath("$.amount").value(500.00));
        }

        @Test
        @DisplayName("Debe retornar 404 para transacción no encontrada")
        @WithMockUser(roles = "USER")
        void debeRetornar404ParaTransaccionNoEncontrada() throws Exception {
            UUID idNoExistente = UUID.randomUUID();
            when(transactionService.getTransactionById(eq(idNoExistente), anyString()))
                .thenReturn(Optional.empty());

            mockMvc.perform(get("/api/v1/transactions/{id}", idNoExistente))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions/account/{accountId} - Obtener Transacciones por Cuenta")
    class ObtenerTransaccionesPorCuenta {

        @Test
        @DisplayName("Debe obtener todas las transacciones de una cuenta")
        @WithMockUser(roles = "USER")
        void debeObtenerTransaccionesDeCuenta() throws Exception {
            List<Transaction> transacciones = List.of(transaccionValida, transaccionAltoValor);
            when(transactionService.getTransactionsByAccount(eq(cuentaId), anyString()))
                .thenReturn(transacciones);

            mockMvc.perform(get("/api/v1/transactions/account/{accountId}", cuentaId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(transaccionId.toString()));
        }

        @Test
        @DisplayName("Debe obtener transacciones filtradas por rango de fechas")
        @WithMockUser(roles = "USER")
        void debeObtenerTransaccionesPorRangoDeFechas() throws Exception {
            LocalDateTime inicio = LocalDateTime.now().minusDays(7);
            LocalDateTime fin = LocalDateTime.now();
            List<Transaction> transacciones = List.of(transaccionValida);
            
            when(transactionService.getTransactionsByAccountAndDateRange(
                eq(cuentaId), eq(inicio), eq(fin), anyString()
            )).thenReturn(transacciones);

            mockMvc.perform(get("/api/v1/transactions/account/{accountId}", cuentaId)
                    .param("startDate", inicio.toString())
                    .param("endDate", fin.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
        }
    }

    @Nested
    @DisplayName("GET /api/v1/transactions/high-value - Transacciones de Alto Valor")
    class TransaccionesAltoValor {

        @Test
        @DisplayName("Debe permitir acceso a admin para transacciones de alto valor")
        @WithMockUser(roles = "ADMIN")
        void debePermitirAccesoAAdmin() throws Exception {
            List<Transaction> transacciones = List.of(transaccionAltoValor);
            when(transactionService.getHighValueTransactions(anyString()))
                .thenReturn(transacciones);

            mockMvc.perform(get("/api/v1/transactions/high-value"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].amount").value(15000.00));
        }

        @Test
        @DisplayName("Debe rechazar acceso de usuario regular a transacciones de alto valor")
        @WithMockUser(roles = "USER")
        void debeRechazarAccesoDeUsuarioRegular() throws Exception {
            when(transactionService.getHighValueTransactions(anyString()))
                .thenThrow(new TransactionException("Acceso denegado. Se requiere rol ADMIN"));

            mockMvc.perform(get("/api/v1/transactions/high-value"))
                .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("Manejo de Errores Global")
    class ManejoErrores {

        @Test
        @DisplayName("Debe manejar error de autenticación")
        @WithMockUser(roles = "USER")
        void debeManejarErrorDeAutenticacion() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new AuthenticationException("Credenciales inválidas"));

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe manejar error de transacción")
        @WithMockUser(roles = "USER")
        void debeManejarErrorDeTransaccion() throws Exception {
            when(transactionService.createTransaction(any(Transaction.class), anyString()))
                .thenThrow(new TransactionException("Error al procesar transacción"));

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionValida)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe manejar error interno del servidor")
        @WithMockUser(roles = "USER")
        void debeManejarErrorInternoDelServidor() throws Exception {
            when(transactionService.getTransactionById(any(UUID.class), anyString()))
                .thenThrow(new RuntimeException("Error interno"));

            mockMvc.perform(get("/api/v1/transactions/{id}", UUID.randomUUID()))
                .andExpect(status().isInternalServerError());
        }
    }

    @Nested
    @DisplayName("Validación de Request Body")
    class ValidacionRequestBody {

        @Test
        @DisplayName("Debe rechazar request con accountId nulo")
        @WithMockUser(roles = "USER")
        void debeRechazarRequestConAccountIdNulo() throws Exception {
            Transaction transaccionSinCuenta = new Transaction(
                transaccionId,
                null,
                TransactionType.TRANSFER,
                new BigDecimal("500.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionSinCuenta)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe rechazar request con tipo de transacción nulo")
        @WithMockUser(roles = "USER")
        void debeRechazarRequestConTipoNulo() throws Exception {
            Transaction transaccionSinTipo = new Transaction(
                transaccionId,
                cuentaId,
                null,
                new BigDecimal("500.00"),
                "USD",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionSinTipo)))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Debe rechazar request con currency vacía")
        @WithMockUser(roles = "USER")
        void debeRechazarRequestConCurrencyVacia() throws Exception {
            Transaction transaccionSinCurrency = new Transaction(
                transaccionId,
                cuentaId,
                TransactionType.TRANSFER,
                new BigDecimal("500.00"),
                "",
                "CUENTA_ORIGEN",
                "CUENTA_DESTINO",
                TransactionStatus.PENDING,
                LocalDateTime.now(),
                "USER_001"
            );

            mockMvc.perform(post("/api/v1/transactions")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(transaccionSinCurrency)))
                .andExpect(status().isBadRequest());
        }
    }
}