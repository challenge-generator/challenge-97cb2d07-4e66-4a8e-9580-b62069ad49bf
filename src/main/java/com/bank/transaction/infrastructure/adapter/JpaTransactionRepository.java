package com.bank.transaction.infrastructure.adapter;

import com.bank.transaction.domain.model.Transaction;
import com.bank.transaction.domain.port.TransactionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final SpringDataTransactionRepository springDataRepository;

    public JpaTransactionRepository(SpringDataTransactionRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        return springDataRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(UUID id) {
        return springDataRepository.findById(id);
    }

    @Override
    public List<Transaction> findByAccountId(UUID accountId) {
        return springDataRepository.findByAccountIdOrderByTimestampDesc(accountId);
    }

    @Override
    public List<Transaction> findByAccountIdAndDateRange(UUID accountId, LocalDateTime start, LocalDateTime end) {
        return springDataRepository.findByAccountIdAndTimestampBetweenOrderByTimestampDesc(
            accountId, start, end);
    }

    @Override
    public List<Transaction> findHighValueTransactions() {
        return springDataRepository.findByAmountGreaterThanOrderByTimestampDesc(
            new java.math.BigDecimal("10000"));
    }

    @Repository
    public interface SpringDataTransactionRepository extends JpaRepository<Transaction, UUID> {
        List<Transaction> findByAccountIdOrderByTimestampDesc(UUID accountId);

        List<Transaction> findByAccountIdAndTimestampBetweenOrderByTimestampDesc(
            UUID accountId, LocalDateTime start, LocalDateTime end);

        @Query("SELECT t FROM Transaction t WHERE t.amount > :threshold ORDER BY t.timestamp DESC")
        List<Transaction> findByAmountGreaterThanOrderByTimestampDesc(
            @Param("threshold") java.math.BigDecimal threshold);
    }
}