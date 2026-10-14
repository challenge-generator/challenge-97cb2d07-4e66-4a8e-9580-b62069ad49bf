package com.bank.transaction.infrastructure.controller;

import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.model.Transaction.TransactionStatus;
import com.bank.transaction.domain.model.Transaction.TransactionType;
import com.bank.transaction.domain.port.AuthenticationService;
import com.bank.transaction.domain.port.TransactionRepository;
import com.bank.transaction.infrastructure.exception.TransactionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transactions")
@Tag(name = "Transactions", description = "API para gestión de transacciones bancarias")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final AuthenticationService authenticationService;

    public TransactionController(TransactionRepository transactionRepository,
                                  AuthenticationService authenticationService) {
        this.transactionRepository = transactionRepository;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva transacción", description = "Registra una nueva transacción bancaria en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transacción creada exitosamente",
                     content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de transacción inválidos"),
        @ApiResponse(responseCode = "401", description = "No autorizado - Token JWT inválido o ausente"),
        @ApiResponse(responseCode = "403", description = "Prohibido - Rol insuficiente para esta operación")
    })
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody TransactionRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_CREATE");
        
        Transaction transaction = new Transaction(
            UUID.randomUUID(),
            request.accountId(),
            request.amount(),
            request.type(),
            request.description(),
            TransactionStatus.PENDING,
            LocalDateTime.now(),
            null,
            null
        );
        
        Transaction saved = transactionRepository.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(saved));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener transacción por ID", description = "Recupera los detalles de una transacción específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Transacción encontrada",
                     content = @Content(schema = @Schema(implementation = TransactionResponse.class))),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    public ResponseEntity<TransactionResponse> getTransactionById(
            @Parameter(description = "ID de la transacción", required = true) @PathVariable UUID id,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_READ");
        
        return transactionRepository.findById(id)
            .map(t -> ResponseEntity.ok(toResponse(t)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/account/{accountId}")
    @Operation(summary = "Listar transacciones por cuenta", description = "Obtiene todas las transacciones asociadas a una cuenta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transacciones de la cuenta"),
        @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    public ResponseEntity<List<TransactionResponse>> getTransactionsByAccount(
            @Parameter(description = "ID de la cuenta", required = true) @PathVariable UUID accountId,
            @Parameter(description = "Fecha de inicio (opcional)") @RequestParam(required = false) LocalDateTime startDate,
            @Parameter(description = "Fecha de fin (opcional)") @RequestParam(required = false) LocalDateTime endDate,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_READ");
        
        List<Transaction> transactions;
        if (startDate != null && endDate != null) {
            transactions = transactionRepository.findByAccountIdAndDateRange(accountId, startDate, endDate);
        } else {
            transactions = transactionRepository.findByAccountId(accountId);
        }
        
        List<TransactionResponse> responses = transactions.stream()
            .map(this::toResponse)
            .toList();
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/high-value")
    @Operation(summary = "Listar transacciones de alto valor", description = "Obtiene transacciones que superan el umbral configurado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de transacciones de alto valor"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido - Se requiere rol de auditor")
    })
    public ResponseEntity<List<TransactionResponse>> getHighValueTransactions(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_AUDIT");
        
        List<Transaction> transactions = transactionRepository.findHighValueTransactions();
        List<TransactionResponse> responses = transactions.stream()
            .map(this::toResponse)
            .toList();
        
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Actualizar estado de transacción", description = "Modifica el estado de una transacción existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada"),
        @ApiResponse(responseCode = "401", description = "No autorizado"),
        @ApiResponse(responseCode = "403", description = "Prohibido")
    })
    public ResponseEntity<TransactionResponse> updateTransactionStatus(
            @Parameter(description = "ID de la transacción", required = true) @PathVariable UUID id,
            @Valid @RequestBody UpdateStatusRequest request,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        
        String token = extractToken(authHeader);
        validateAuthentication(token, "TRANSACTION_UPDATE");
        
        Transaction existing = transactionRepository.findById(id)
            .orElseThrow(() -> new TransactionException("Transacción no encontrada: " + id));
        
        Transaction updated = new Transaction(
            existing.id(),
            existing.accountId(),
            existing.amount(),
            existing.type(),
            existing.description(),
            request.status(),
            existing.createdAt(),
            LocalDateTime.now(),
            existing.auditTrail()
        );
        
        Transaction saved = transactionRepository.save(updated);
        return ResponseEntity.ok(toResponse(saved));
    }

    private String extractToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getCredentials() instanceof String token) {
            return token;
        }
        throw new TransactionException("Token de autenticación no proporcionado");
    }

    private void validateAuthentication(String token, String requiredRole) {
        if (!authenticationService.validateToken(token).isEmpty()) {
            if (!authenticationService.hasRole(token, requiredRole)) {
                throw new TransactionException("Rol insuficiente para esta operación: " + requiredRole);
            }
        } else {
            throw new TransactionException("Token inválido o expirado");
        }
    }

    private TransactionResponse toResponse(Transaction transaction) {
        return new TransactionResponse(
            transaction.id(),
            transaction.accountId(),
            transaction.amount(),
            transaction.type(),
            transaction.description(),
            transaction.status(),
            transaction.createdAt(),
            transaction.updatedAt()
        );
    }

    public record TransactionRequest(
        @NotNull(message = "El ID de cuenta es obligatorio")
        UUID accountId,
        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser positivo")
        BigDecimal amount,
        @NotNull(message = "El tipo de transacción es obligatorio")
        TransactionType type,
        @NotBlank(message = "La descripción es obligatoria")
        String description
    ) {}

    public record TransactionResponse(
        UUID id,
        UUID accountId,
        BigDecimal amount,
        TransactionType type,
        String description,
        TransactionStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {}

    public record UpdateStatusRequest(
        @NotNull(message = "El estado es obligatorio")
        TransactionStatus status
    ) {}
}