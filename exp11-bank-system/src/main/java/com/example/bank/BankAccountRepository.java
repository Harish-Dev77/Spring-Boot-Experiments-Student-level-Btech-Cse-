package com.example.bank;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Repository interface for BankAccount.
 * Provides standard CRUD (Create, Read, Update, Delete) methods.
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
