package com.account.transformer;

import com.account.domain.Account;
import com.account.model.AccountModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountTransformerTest {

    private AccountTransformer accountTransformer;

    @BeforeEach
    void setup() {
        accountTransformer = new AccountTransformer();
    }

    @Test
    void whenNullAccountModelShouldReturnNullEntity() {
        //given accountModel = null

        //when
        Account account = accountTransformer.toEntity(null);
        //then
        Assertions.assertNull(account);
    }

    @Test
    void whenAccountModelShouldReturnEntity() {
        //given
        AccountModel accountModel = AccountModel.builder()
                .name("Bilal")
                .email("Bilal@email.com")
                .phoneNumber("123456789")
                .password("12345")
                .build();
        //when
        Account account = accountTransformer.toEntity(accountModel);
        //then
        Assertions.assertNotNull(account);
        Assertions.assertEquals("Bilal", account.getName());
        Assertions.assertEquals("Bilal@email.com", account.getEmail());
        Assertions.assertEquals("123456789", account.getPhoneNumber());
        Assertions.assertEquals("12345", account.getPassword());
    }

    @Test
    void whenNullAccountShouldReturnEmptyModel() {
        //given account = null

        //when
        AccountModel accountModel = accountTransformer.toModel(null);
        //then
        Assertions.assertNull(accountModel);
    }

    @Test
    void whenAccountShouldReturnConvertedModel() {
        //given
        Account account = Account.builder()
                .id(12345L)
                .name("Bilal")
                .email("Bilal@email.com")
                .phoneNumber("123456789")
                .password("12345")
                .build();
        //when
        AccountModel accountModel = accountTransformer.toModel(account);
        //then
        Assertions.assertNotNull(accountModel);
        Assertions.assertEquals(12345L, accountModel.getId());
        Assertions.assertEquals("Bilal", accountModel.getName());
        Assertions.assertEquals("Bilal@email.com", accountModel.getEmail());
        Assertions.assertEquals("123456789", account.getPhoneNumber());
        Assertions.assertEquals("12345", account.getPassword());
    }

}