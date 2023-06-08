package com.example.springbootdemo.services;

import com.example.springbootdemo.models.Account;
import com.example.springbootdemo.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {

        Optional<Account> account = this.accountRepository.findById(id);

        if(account.isEmpty()) {
            throw new RuntimeException("Account does not exist");
        }

        BigDecimal currentAmount = account.get().getBalance();
        if(amount.compareTo(currentAmount) > 0) {
            throw new RuntimeException("Not enough money in your balance");
        }

        account.get().setBalance(currentAmount.subtract(amount));

        this.accountRepository.save(account.get());
    }

    @Override
    public void depositMoney(BigDecimal amount, Long id) {

        Account account = this.accountRepository.findById(id).orElseThrow( () ->
                new RuntimeException("Sorry no account"));

        BigDecimal balance = account.getBalance();
        BigDecimal addedAmount = balance.add(amount);
        account.setBalance(addedAmount);

        this.accountRepository.save(account);
    }
}
