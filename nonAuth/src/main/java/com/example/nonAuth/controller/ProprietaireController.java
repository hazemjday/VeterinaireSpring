package com.example.nonAuth.controller;


import com.example.nonAuth.modal.Proprietaire;
import com.example.nonAuth.service.ProprietaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("proprietaire")
public class ProprietaireController {
    @Autowired
    ProprietaireService proprietaireService;
    @GetMapping("/all")
    public List<Proprietaire> getOwner(){
        return proprietaireService.getOwner();
    }


    @PostMapping("/add")
    public String addOwner(@RequestBody Proprietaire proprietaire){
        return proprietaireService.addOwner(proprietaire);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteVeterinaire(@PathVariable Long id) {
        return proprietaireService.deleteProprietaire(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateProprietaire(@PathVariable Long id, @RequestBody Proprietaire proprietaire) {
        return  proprietaireService.updatePrprietaire(id, proprietaire);
    }
}
