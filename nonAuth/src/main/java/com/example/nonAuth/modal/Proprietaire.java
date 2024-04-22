package com.example.nonAuth.modal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Proprietaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long phone;
    private String mail;


   @OneToMany( mappedBy = "proprietaire")
   @JsonManagedReference
   private List<Dog> dog = new ArrayList<>();

   //a ne pas ajouter dans le request
    private Integer dogsNumber =dog.size();

    public void setDogsNumber(){
        this.dogsNumber = this.dog.size();
    }
}
