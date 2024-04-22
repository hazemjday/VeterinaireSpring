package com.example.nonAuth.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Veterinaire {

    @Id
    private Long id;
    private String username;
    private Long phone;
    private String mail;
    private String region;
    private String gender;
    private String imageUrl;



}


