package com.account.handler;

import com.account.domain.Account;
import com.account.exception.AccountNotFoundException;
import com.account.exception.DuplicateAccountException;
import com.account.model.AccountModel;
import com.account.model.AccountResponseModel;
import com.account.model.JWTResponse;
import com.account.service.AccountService;
import com.account.service.JWTService;
import com.account.transformer.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountHandler {
    private final AccountService accountService;
    private final Transformer<AccountModel, Account> accountTransformer;
    private final JWTService jwtService;

    public AccountResponseModel createAccount(AccountModel model) throws DuplicateAccountException {
        Account account = accountTransformer.toEntity(model);
        Account savedAccount = accountService.save(account);
        return buildResponse(savedAccount);
    }

    public AccountModel updateAccount(Long accountId, AccountModel model) throws AccountNotFoundException {
        Account account = accountTransformer.toEntity(model);
        Account updatedAccount = accountService.update(accountId, account);
        return accountTransformer.toModel(updatedAccount);
    }

    private AccountResponseModel buildResponse(Account account){
        //TODO: Just for showcase it is in there otherwise it should be in a proper sign in api
        String token = jwtService.generateToken();
        return AccountResponseModel.builder()
                .account(accountTransformer.toModel(account))
                .tokenResponse(JWTResponse.builder().token(token).build())
                .build();
    }
}