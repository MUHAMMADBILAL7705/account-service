package com.account.transformer;

import com.account.domain.Account;
import com.account.model.AccountModel;
import org.springframework.stereotype.Component;

@Component
public class AccountTransformer implements Transformer<AccountModel, Account> {

    @Override
    public AccountModel toModel(Account entity) {
        if (entity == null)
            return null;
        else
            return AccountModel.builder()
                    .id(entity.getId())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .phoneNumber(entity.getPhoneNumber())
                    .build();
    }

    @Override
    public Account toEntity(AccountModel model) {
        if (model == null)
            return null;
        else
            return Account.builder()
                    .email(model.getEmail())
                    .name(model.getName())
                    .phoneNumber(model.getPhoneNumber())
                    .password(model.getPassword())
                    .build();
    }
}
