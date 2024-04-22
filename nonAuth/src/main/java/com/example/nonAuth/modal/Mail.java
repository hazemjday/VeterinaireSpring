package com.example.nonAuth.modal;

import lombok.Data;

import java.util.List;

@Data
public class Mail {
    private String from;
    private String subject;
    private String message;
    private List<String> emails;

}
