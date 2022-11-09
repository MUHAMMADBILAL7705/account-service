package com.account.expert;

import com.account.domain.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMergeExpert implements MergeExpert<Account> {
    @Override
    public Account merge(Account oldValue, Account newValue) {
        if (oldValue == null)
            return newValue;
        else if (newValue == null)
            return oldValue;
        else {
            mergeName(oldValue, newValue);
            mergeEmail(oldValue, newValue);
        }
        return oldValue;
    }

    private void mergeName(Account oldValue, Account newValue) {
        if (StringUtils.isBlank(oldValue.getName()) && StringUtils.isNotBlank(newValue.getName()))
            oldValue.setName(newValue.getName());
        else if (!StringUtils.equalsIgnoreCase(oldValue.getName(), newValue.getName()))
            oldValue.setName(newValue.getName());
    }

    private void mergeEmail(Account oldValue, Account newValue) {
        if (StringUtils.isBlank(oldValue.getEmail()) && StringUtils.isNotBlank(newValue.getEmail()))
            oldValue.setEmail(newValue.getEmail());
        else if (!StringUtils.equalsIgnoreCase(oldValue.getEmail(), newValue.getEmail()))
            oldValue.setEmail(newValue.getEmail());
    }
}
