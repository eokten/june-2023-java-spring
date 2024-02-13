package com.okten.springdemo.dto;

import lombok.Data;

@Data
public class JwtRefreshRequest {

    private String refreshToken;
}
