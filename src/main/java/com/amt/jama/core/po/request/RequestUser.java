package com.amt.jama.core.po.request;

import lombok.Data;

@Data
public class RequestUser {

    /**
     * Required when creating a new user (POST). Optional on update (PUT)
     */
    private String username;

    /**
     * Required when creating a new user (POST). Optional on update (PUT)
     */
    private String password;

    /**
     * Required when creating a new user (POST). Optional on update (PUT)
     */
    private String firstName;

    /**
     * Required when creating a new user (POST). Optional on update (PUT)
     */
    private String lastName;

    /**
     * Required when creating a new user (POST). Optional on update (PUT)
     */
    private String email;

    private String phone;

    private String title;

    private String location;

    /**
     * Required when creating a new user (POST). Optional on update (PUT)
     */
    private String licenseType;

}
