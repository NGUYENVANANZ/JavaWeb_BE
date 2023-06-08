package com.example.javaweb_be.controller;


import com.example.javaweb_be.model.Account;
import com.example.javaweb_be.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
@CrossOrigin("*")
public class AccountAPI {
    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account accounts = accountRepository.findById(id).get();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<Account>> login(@RequestBody Account account) {
        Optional<Account> existingAccount = accountRepository.findAccountByUserNameAndPassWord(
                account.getUserName(),
                account.getPassWord()
        );
        if (existingAccount.isPresent()) {
            return new ResponseEntity<>(existingAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if (accountRepository.findAccountByUserName(account.getUserName()) != null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        try {
            account.setRole_User(2L);
            Account newAccount = accountRepository.save(account);
            return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Optional<Account> existingAccountOptional = accountRepository.findById(id);
        if (!existingAccountOptional.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Account existingAccount = existingAccountOptional.get();
        existingAccount.setName(account.getName());
        existingAccount.setUserName(account.getUserName());
        existingAccount.setPassWord(account.getPassWord());
        existingAccount.setRole_User(account.getRole_User());

        try {
            Account updatedAccount = accountRepository.save(existingAccount);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable Long id) {
        try {
            accountRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
