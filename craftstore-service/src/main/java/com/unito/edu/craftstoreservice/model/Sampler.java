package com.unito.edu.craftstoreservice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sampler {

    private int id;
    private int craftstoreId;
    private List<Product> productList;
}
