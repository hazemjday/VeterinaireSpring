package com.example.nonAuth.controller;


import com.example.nonAuth.modal.Mail;
import com.example.nonAuth.service.EmailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Mail")
public class MailControler {
    @Autowired
    EmailSenderServiceImpl emailSenderService;


    //get al the dogs that exists
    @PostMapping("/send")
    public String getDogs(@RequestBody Mail mail){
        for (String e: mail.getEmails()){
            emailSenderService.sendEmail(mail.getFrom(), e, mail.getSubject(), mail.getMessage());
        }
        return "succss";
    }
}
