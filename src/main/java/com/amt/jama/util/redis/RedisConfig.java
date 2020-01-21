package com.amt.jama.util.redis;

import lombok.Data;

@Data
public class RedisConfig {

    private String url = "127.0.0.1";

    private String password;

    private Integer port = 6379;

    private Integer timeout = 2000;

    private Integer maxTotal = 10;

    private Integer maxIdle = 2;

    private Integer maxWaitMillis = 10;

    private Boolean testOnBorrow = true;

    private Boolean testOnReturn = true;

    private Boolean active = true;


}
