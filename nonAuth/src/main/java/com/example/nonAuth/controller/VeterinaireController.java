package com.example.nonAuth.controller;





import com.example.nonAuth.modal.Veterinaire;
import com.example.nonAuth.service.VeterinaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("veterinaire")

public class VeterinaireController {
    @Autowired
    VeterinaireService veterinaireService;


    @GetMapping("/all")
    public List<Veterinaire> getveteinaires(){
        return veterinaireService.getVeterinaire();
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteVaccinatin(@PathVariable Long id) {
        return veterinaireService.deleteVetrinaire(id);
    }


    @PostMapping("/add")
    public String addVeterinaire(@RequestBody Veterinaire veterinaire){
        return veterinaireService.addVeterinaire(veterinaire);
    }


    @PutMapping("update/{id}")
    public ResponseEntity<String> updateProprietaire(@PathVariable Long id, @RequestBody Veterinaire veterinaire ) {
        return  veterinaireService.updateVeterinaire(id, veterinaire);

    }


    //add the login coordinates to the user



}
