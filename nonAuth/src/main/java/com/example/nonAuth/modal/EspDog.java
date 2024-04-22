package com.example.nonAuth.modal;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EspDog {

    private String imageUrl;
    private String race;
    private LocalDate birthdate;
    private String sexe;
    private String nameProp;
    private Long phoneProp;
    private String mailProp;
    private Boolean vaccinated;
}
