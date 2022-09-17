package com.example.tmt_spring_boot_02_01_spring_jpa.controller;

import com.example.tmt_spring_boot_02_01_spring_jpa.entity.Account;
import com.example.tmt_spring_boot_02_01_spring_jpa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAll(){
        return ResponseEntity.ok(this.accountService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getById(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(this.accountService.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> save(
            @RequestBody Account newAccount
    ){
        this.accountService.createAccount(newAccount);
        return ResponseEntity.ok("Create succeed");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> save(
            @PathVariable("id") Integer id,
            @RequestBody Account newAccount
    ){
        this.accountService.updateAccount(id, newAccount);
        return ResponseEntity.ok("Update succeed");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable("id") Integer id
    ){
        this.accountService.deleteAccount(id);
        return ResponseEntity.ok("Delete succeed");
    }
}
