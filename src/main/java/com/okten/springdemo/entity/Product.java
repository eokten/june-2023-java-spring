package com.okten.springdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "name can not be blank")
    private String name;

    private Double price;

    @Min(value = 0, message = "quantity can not be lower than 0")
    @Max(value = 100, message = "quantity can not be higher than 100")
    private Integer quantity;
}
