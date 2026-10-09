package com.example.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
 * RESTful Controller for the Bank System.
 * Demonstrates proper use of HTTP methods (GET, POST, PUT, DELETE)
 * and RESTful URL patterns.
 */
@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountRepository accountRepository;

    // 1. GET /api/accounts -> Retrieve all accounts
    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return accountRepository.findAll();
    }

    // 2. GET /api/accounts/{id} -> Retrieve a specific account by ID
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable Long id) {
        Optional<BankAccount> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return ResponseEntity.ok(account.get()); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    // 3. POST /api/accounts -> Create a new account
    @PostMapping
    public ResponseEntity<BankAccount> createAccount(@RequestBody BankAccount account) {
        BankAccount savedAccount = accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount); // 201 Created
    }

    // 4. PUT /api/accounts/{id} -> Update an existing account (e.g., Deposit/Withdraw)
    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> updateAccount(@PathVariable Long id, @RequestBody BankAccount updatedAccount) {
        Optional<BankAccount> existingAccount = accountRepository.findById(id);
        
        if (existingAccount.isPresent()) {
            BankAccount account = existingAccount.get();
            account.setAccountHolderName(updatedAccount.getAccountHolderName());
            account.setBalance(updatedAccount.getBalance());
            
            BankAccount savedAccount = accountRepository.save(account);
            return ResponseEntity.ok(savedAccount); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }

    // 5. DELETE /api/accounts/{id} -> Delete an account
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
}
