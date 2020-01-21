package com.amt.jama.core.po.users;

import lombok.Data;

@Data
public class User {

    private Integer id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String title;

    private String location;

    private String licenseType;

    private String avatarUrl;

    private Boolean active;

    @Override
    public String toString() {
        return this.username;
    }
}
