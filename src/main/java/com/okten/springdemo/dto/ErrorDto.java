package com.okten.springdemo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {

    private Long timestamp;

    private String details;
}
