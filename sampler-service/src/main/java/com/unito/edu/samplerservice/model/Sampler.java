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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "craftstore_id", nullable = false)
    private int craftstoreId;

    @OneToMany (fetch=FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="sampler_id")
    private List<Product> productList;
}
