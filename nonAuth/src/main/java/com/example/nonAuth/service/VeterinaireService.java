package com.example.nonAuth.service;



import com.example.nonAuth.Repository.VeterinaireRepo;
import com.example.nonAuth.modal.Veterinaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeterinaireService  {


    @Autowired
    VeterinaireRepo veterinaireRepo;

    public List<Veterinaire> getVeterinaire() {
        return veterinaireRepo.findAll();
    }


    public ResponseEntity<String> deleteVetrinaire(Long id) {
        Optional<Veterinaire> vet = veterinaireRepo.findById(id);
        if (vet.isPresent()) {
            Veterinaire veto=vet.get();
            veterinaireRepo.delete(veto);
            return ResponseEntity.ok("deleted successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found id: " + id);
        }
    }

    public String addVeterinaire(Veterinaire veterinaire) {

   veterinaireRepo.save(veterinaire);
   return "success";
    }

    public ResponseEntity<String> updateVeterinaire(Long id, Veterinaire veterinaire) {

        Optional<Veterinaire> vet = veterinaireRepo.findById(id);
        if (vet.isPresent()) {
           Veterinaire veto =vet.get();
            veto.setUsername(veto.getUsername());
            veto.setPhone(veto.getPhone());
            veto.setMail(veto.getMail());
            veto.setRegion(veto.getRegion());
            veto.setGender(veto.getGender());
            veto.setImageUrl(veto.getImageUrl());


            veterinaireRepo.save(veto);
            return ResponseEntity.ok("veterinaire updated successfully");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veterinaire not found with id: " + id);
        }
    }


}
