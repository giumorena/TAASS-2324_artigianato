package com.unito.edu.craftstoreservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "craftstore")
public class Craftstore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "sampler_id")
    private int samplerId;

    /* Unidirectional One-To-Many relationship.
    *  A craftstore may have many ownerships (and then owners),
    *  while an ownership (not owner!) refers only to one craftstore.
    *  A foreign key craftstore_id will be added to Ownership table */
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="craftstore_id")
    private List<Ownership> ownerList;

    /* Unidirectional One-To-Many relationship.
     *  A craftstore may have many contacts,
     *  while a contact refers only to one craftstore.
     *  A foreign key craftstore_id will be added to Contact table */
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="craftstore_id")
    private List<Contact> contactList;

    /* Unidirectional One-To-Many relationship.
     *  A craftstore may have many addresses,
     *  while an address refers only to one craftstore.
     *  A foreign key craftstore_id will be added to Address table */
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="craftstore_id")
    private List<Address> addressList;

    /* Unidirectional One-To-Many relationship.
     *  A craftstore may have received multiple comments,
     *  while a comment may refer to only one craftstore.
     *  A foreign key craftstore_id will be added to Comment table */
    @OneToMany (fetch=FetchType.EAGER,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="craftstore_id")
    private List<Comment> commentList;
}
