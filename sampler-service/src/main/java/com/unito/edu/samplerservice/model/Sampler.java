package com.unito.edu.samplerservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sampler")
public class Sampler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "craftstore_id", nullable = false)
    private int craftstoreId;

    /* Unidirectional One-To-Many relationship.
     *  A sampler can contain multiple products,
     *  while a product is contained in only one sampler.
     *  A foreign key sampler_id will be added to Product table */
    @OneToMany (fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="sampler_id")
    private List<Product> productList;
}
