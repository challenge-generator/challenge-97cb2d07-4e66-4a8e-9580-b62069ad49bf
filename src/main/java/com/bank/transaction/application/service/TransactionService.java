package com.bank.transaction.application.service;

import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.model.Transaction.TransactionStatus;
import com.bank.transaction.domain.model.Transaction.TransactionType;
import com.bank.transaction.domain.port.AuthenticationService;
import com.bank.transaction.domain.port.TransactionRepository;
import com.bank.transaction.infrastructure.exception.TransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private static final BigDecimal HIGH_VALUE_THRESHOLD = new BigDecimal("10000");
    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    private final TransactionRepository transactionRepository;
    private final AuthenticationService authenticationService;

    public TransactionService(TransactionRepository transactionRepository,
                              AuthenticationService authenticationService) {
        this.transactionRepository = transactionRepository;
        this.authenticationService = authenticationService;
    }

    @Transactional
    public Transaction createTransaction(UUID accountId, BigDecimal amount,
                                         TransactionType type, String description,
                                         String authToken) {
        logger.info("Iniciando creación de transacción para cuenta: {}, monto: {}", accountId, amount);

        validateAuthentication(authToken);
        validateTransactionInput(accountId, amount, type);

        Transaction transaction = new Transaction(
            UUID.randomUUID(),
            accountId,
            amount,
            type,
            TransactionStatus.PENDING,
            description,
            LocalDateTime.now(),
            authToken
        );

        Transaction savedTransaction = transactionRepository.save(transaction);
        logger.info("Transacción creada exitosamente con ID: {}", savedTransaction.id());

        if (savedTransaction.isHighValue()) {
            logger.warn("Transacción de alto valor detectada: {} para cuenta: {}",
                savedTransaction.amount(), accountId);
        }

        return savedTransaction;
    }

    @Transactional(readOnly = true)
    public Optional<Transaction> getTransaction(UUID transactionId, String authToken) {
        logger.debug("Consultando transacción con ID: {}", transactionId);

        validateAuthentication(authToken);

        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent() && transaction.get().isHighValue()) {
            verifyHighValueAccess(authToken);
        }

        return transaction;
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAccountTransactions(UUID accountId, String authToken) {
        logger.debug("Consultando transacciones para cuenta: {}", accountId);

        validateAuthentication(authToken);

        return transactionRepository.findByAccountId(accountId);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAccountTransactionsByDateRange(UUID accountId,
                                                                LocalDateTime startDate,
                                                                LocalDateTime endDate,
                                                                String authToken) {
        logger.debug("Consultando transacciones para cuenta: {} entre {} y {}",
            accountId, startDate, endDate);

        validateAuthentication(authToken);

        return transactionRepository.findByAccountIdAndDateRange(accountId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getHighValueTransactions(String authToken) {
        logger.debug("Consultando transacciones de alto valor");

        validateAuthentication(authToken);
        verifyHighValueAccess(authToken);

        return transactionRepository.findHighValueTransactions();
    }

    @Transactional
    public Transaction updateTransactionStatus(UUID transactionId, TransactionStatus newStatus,
                                                String authToken) {
        logger.info("Actualizando estado de transacción {} a {}", transactionId, newStatus);

        validateAuthentication(authToken);
        verifyHighValueAccess(authToken);

        Optional<Transaction> existingTransaction = transactionRepository.findById(transactionId);
        if (existingTransaction.isEmpty()) {
            throw new TransactionException("Transacción no encontrada: " + transactionId);
        }

        Transaction transaction = existingTransaction.get();
        Transaction updatedTransaction = new Transaction(
            transaction.id(),
            transaction.accountId(),
            transaction.amount(),
            transaction.type(),
            newStatus,
            transaction.description(),
            transaction.timestamp(),
            transaction.authToken()
        );

        return transactionRepository.save(updatedTransaction);
    }

    private void validateAuthentication(String authToken) {
        if (authToken == null || authToken.isBlank()) {
            throw new TransactionException("Token de autenticación requerido");
        }

        Set<String> roles = authenticationService.validateToken(authToken);
        if (roles.isEmpty()) {
            throw new TransactionException("Token de autenticación inválido");
        }
    }

    private void verifyHighValueAccess(String authToken) {
        if (!authenticationService.hasRole(authToken, ROLE_ADMIN)) {
            throw new TransactionException("Acceso denegado: se requiere rol ADMIN para operaciones de alto valor");
        }
    }

    private void validateTransactionInput(UUID accountId, BigDecimal amount, TransactionType type) {
        if (accountId == null) {
            throw new TransactionException("El ID de cuenta es requerido");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new TransactionException("El monto debe ser mayor a cero");
        }

        if (type == null) {
            throw new TransactionException("El tipo de transacción es requerido");
        }

        if (amount.compareTo(HIGH_VALUE_THRESHOLD) > 0) {
            logger.info("Monto de alto valor detectado: {}", amount);
        }
    }
}