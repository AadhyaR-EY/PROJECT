package com.programmingtechie.product_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

// to define product as a mongodb document
@Document(value="product")
@AllArgsConstructor
@NoArgsConstructor
@Builder // flexible way to create instances of the class
@Data // generate common boilerplate code(getter/setter etc)
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
