package com.account.expert;

import com.account.domain.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountMergeExpertTest {

    private AccountMergeExpert accountMergeExpert;

    @BeforeEach
    void setup() {
        this.accountMergeExpert = new AccountMergeExpert();
    }

    @Test
    void whenOldIsNullShouldReturnNew() {
        //given
        Account newAccount = Account.builder().id(12345L).build();
        //when
        Account mergedAccount = accountMergeExpert.merge(null, newAccount);
        //then
        Assertions.assertNotNull(mergedAccount);
        Assertions.assertEquals(12345L, mergedAccount.getId());
    }


    @Test
     void whenNewIsNullShouldReturnOld() {
        //given
        Account oldAccount = Account.builder().id(12345L).build();
        //when
        Account mergedAccount = accountMergeExpert.merge(oldAccount, null);
        //then
        Assertions.assertNotNull(mergedAccount);
        Assertions.assertEquals(12345L, mergedAccount.getId());
    }

    @Test
     void whenNewAndOldAreNotSameShouldReturnMerged() {
        //given
        Account oldAccount = Account.builder().id(12345L).name("Bilal").email("Bilal@gmail.com").build();
        Account newAccount = Account.builder().id(12345L).name("Ali").email("Ali@gmail.com").build();
        //when
        Account mergedAccount = accountMergeExpert.merge(oldAccount, newAccount);
        //then
        Assertions.assertNotNull(mergedAccount);
        Assertions.assertEquals(12345L, mergedAccount.getId());
        Assertions.assertEquals("Ali", mergedAccount.getName());
        Assertions.assertEquals("Ali@gmail.com", mergedAccount.getEmail());
    }

}