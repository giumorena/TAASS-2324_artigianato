package com.unito.edu.craftstoreservice.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "contact_detail", nullable = false)
    private String contactDetail;

    @Column(name = "description")
    private String description;
}
