package com.example.springbootdemo;

import com.example.springbootdemo.models.User;
import com.example.springbootdemo.services.AccountService;
import com.example.springbootdemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional // dobavqme tova za da ne smenqme na Eager
    public void run(String... args) throws Exception {

    User user =  this.userService.findByUsername("first");

      this.accountService.depositMoney(BigDecimal.TEN,user.getAccountIds().get(0));

      this.accountService.withdrawMoney(BigDecimal.ONE, user.getAccountIds().get(0));
    }
}
