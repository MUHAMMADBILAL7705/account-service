package com.account.service;

import com.account.domain.Account;
import com.account.domain.TopicType;
import com.account.exception.AccountNotFoundException;
import com.account.exception.DuplicateAccountException;
import com.account.expert.MergeExpert;
import com.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final MergeExpert<Account> accountMergeExpert;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public Account save(Account account) throws DuplicateAccountException {
        if (accountRepository.findByEmailIgnoreCase(account.getEmail()) != null)
            throw new DuplicateAccountException();
        Account savedAccount = accountRepository.save(account);
        kafkaTemplate.send(TopicType.ACCOUNT_CREATION.toString(), savedAccount.toString());
        return savedAccount;
    }

    @Override
    public Account update(Long accountId, Account account) throws AccountNotFoundException {
        Account accountFromDb = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new);
        Account mergedAccount = accountMergeExpert.merge(accountFromDb, account);
        return accountRepository.save(mergedAccount);
    }
}
