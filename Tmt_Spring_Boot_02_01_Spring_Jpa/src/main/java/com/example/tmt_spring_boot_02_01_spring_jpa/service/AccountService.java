package com.example.tmt_spring_boot_02_01_spring_jpa.service;

import com.example.tmt_spring_boot_02_01_spring_jpa.entity.Account;
import com.example.tmt_spring_boot_02_01_spring_jpa.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepo;

    public List<Account> getAll(){
        return this.accountRepo.findAll();
    }

    public Account getById(Integer id){
        Optional<Account> optionalAccount = this.accountRepo.findById(id);
        if (optionalAccount.isEmpty()){
            throw new EntityNotFoundException("Not found account");
        }
        Account account = optionalAccount.get();
        return account;
    }

    public void createAccount(Account newAccount){
        String email = newAccount.getEmail();
        Optional<Account> optionalAccount = this.accountRepo.findByEmail(email);
        if (optionalAccount.isPresent()){
            throw new EntityExistsException("Email already exist");
        }
        this.accountRepo.save(newAccount);
    }

    public void updateAccount(Integer id, Account newAccount){
        Optional<Account> optionalAccount = this.accountRepo.findById(id);
        if (optionalAccount.isEmpty()){
            throw new EntityNotFoundException("Not found account");
        }
        Account currentAccount = optionalAccount.get();
        String email = newAccount.getEmail();
        if (email != null){
            Optional<Account> optAccount = this.accountRepo.findByEmail(email);
            if (optAccount.isPresent()){
                throw new EntityExistsException("Email already exist");
            }
            currentAccount.setEmail(email);
        }

        String userName = newAccount.getUsername();
        if (userName != null && userName.trim().length() != 0){
            currentAccount.setUsername(userName);
        }

        String fullName = newAccount.getFullName();
        if (fullName != null && fullName.trim().length() != 0){
            currentAccount.setFullName(fullName);
        }

        String password = newAccount.getPassword();
        if (password != null && password.trim().length() != 0){
            currentAccount.setFullName(password);
        }

        this.accountRepo.save(currentAccount);
    }

    public void deleteAccount(Integer id){
        Optional<Account> optionalAccount = this.accountRepo.findById(id);
        if (optionalAccount.isEmpty()){
            throw new EntityNotFoundException("Not found account");
        }
        Account account = optionalAccount.get();
        this.accountRepo.delete(account);
    }


}
