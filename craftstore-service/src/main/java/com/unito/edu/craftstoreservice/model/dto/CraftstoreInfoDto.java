package com.unito.edu.craftstoreservice.model.dto;

import com.unito.edu.craftstoreservice.model.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CraftstoreInfoDto {
    private int id;
    private String name;
    private String category;
    private String description;
    private List<Ownership> ownerList;
    private List<Contact> contactList;
    private List<Address> addressList;
}
