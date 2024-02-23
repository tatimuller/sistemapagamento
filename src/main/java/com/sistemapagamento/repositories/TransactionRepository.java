package com.sistemapagamento.repositories;

import com.sistemapagamento.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
