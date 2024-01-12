package com.unito.edu.craftstoreservice.model.dto;

import com.unito.edu.craftstoreservice.model.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CraftstoreMaxDto {

    private int id;
    private String name;
    private String category;
    private String description;
    private Sampler sampler;
    private List<Ownership> ownerList;
    private List<Contact> contactList;
    private List<Address> addressList;
    private List<Comment> commentList;

}
