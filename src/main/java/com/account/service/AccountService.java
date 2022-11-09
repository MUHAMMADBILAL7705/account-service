package com.account.service;

import com.account.domain.Account;
import com.account.exception.AccountNotFoundException;
import com.account.exception.DuplicateAccountException;

public interface AccountService {
    Account save(Account account) throws DuplicateAccountException;

    Account update(Long accountId, Account account) throws AccountNotFoundException;
}
