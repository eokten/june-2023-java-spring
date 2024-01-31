package com.okten.springdemo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.okten.springdemo.entity.Product;
import com.okten.springdemo.util.View;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    @JsonView(View.Internal.class)
    private Long id;

    @JsonView(View.External.class)
    private String name;

    @JsonView(View.External.class)
    private Double price;

    @JsonView(View.External.class)
    private Integer quantity;
}
