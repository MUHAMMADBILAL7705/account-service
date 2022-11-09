package com.account.controller;

import com.account.exception.AccountNotFoundException;
import com.account.exception.DuplicateAccountException;
import com.account.handler.AccountHandler;
import com.account.model.AccountModel;
import com.account.model.AccountResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountHandler accountHandler;

    @PostMapping
    public AccountResponseModel createAccount(@RequestBody AccountModel model) throws DuplicateAccountException {
        return accountHandler.createAccount(model);
    }

    @PutMapping("/{accountId}")
    public AccountModel updateAccount(@PathVariable Long accountId, @RequestBody AccountModel model) throws AccountNotFoundException {
        return accountHandler.updateAccount(accountId, model);
    }
}
