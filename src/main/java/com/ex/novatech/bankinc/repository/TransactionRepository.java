package com.ex.novatech.bankinc.repository;

import com.ex.novatech.bankinc.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

}
