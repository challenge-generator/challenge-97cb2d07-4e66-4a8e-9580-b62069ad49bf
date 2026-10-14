package com.bank.transaction.domain.port;

import com.bank.transaction.domain.model.Transaction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {
    Transaction save(Transaction transaction);

    Optional<Transaction> findById(UUID id);

    List<Transaction> findByAccountId(UUID accountId);

    List<Transaction> findByAccountIdAndDateRange(UUID accountId, LocalDateTime start, LocalDateTime end);

    List<Transaction> findHighValueTransactions();
}