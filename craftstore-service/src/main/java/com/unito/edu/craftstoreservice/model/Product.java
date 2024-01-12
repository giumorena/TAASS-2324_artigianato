package com.unito.edu.craftstoreservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private int id;
    private String description;
    private float price;

}
