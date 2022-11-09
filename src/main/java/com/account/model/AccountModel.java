package com.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
