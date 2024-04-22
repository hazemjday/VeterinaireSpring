package com.example.nonAuth.service;



import com.example.nonAuth.Repository.ProprietaireRepo;
import com.example.nonAuth.Repository.VaccinationRepo;
import com.example.nonAuth.Repository.VeterinaireRepo;
import com.example.nonAuth.modal.Proprietaire;
import com.example.nonAuth.modal.Vaccination;
import com.example.nonAuth.modal.Veterinaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VaccinationService {
    @Autowired
    VaccinationRepo vaccinationRepo;
    @Autowired
    ProprietaireRepo proprietaireRepo;
    @Autowired
    VeterinaireRepo veterinaireRepo;
    public List<Vaccination> getVaccination() {
        return vaccinationRepo.findAll();
    }

    public String addVacination(Vaccination vaccination) {
   vaccinationRepo.save(vaccination);
   return "sccess";
    }

    public ResponseEntity<String> deleteVaccination(Long id) {
        Optional<Vaccination> vacc = vaccinationRepo.findById(id);
        if (vacc.isPresent()) {
          Vaccination vacci =vacc.get();
            for (Proprietaire e:vacci.getProprietaire()){
                proprietaireRepo.delete(e);
            }
            for(Veterinaire v: vacci.getVeterinaire()){
        veterinaireRepo.delete(v);
            }
            vaccinationRepo.delete(vacci);
            return ResponseEntity.ok("deleted");
        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found id: " + id);}
    }




    public String addProprietaire(Long idVacc, Long idProp) {
        Proprietaire proprietaire = proprietaireRepo.findById(idProp).get();
        Vaccination vaccination = vaccinationRepo.findById(idVacc).get();
        List<Proprietaire> proprietaires = new ArrayList<>(vaccination.getProprietaire()); // Creating a new mutable set
        proprietaires.add(proprietaire);
        vaccination.setProprietaire(proprietaires);
        vaccinationRepo.save(vaccination);
   return("success");
    }

    public String addVeerinaire(Long vetId, Long vaccinId) {
        List<Veterinaire> vaccinList = new ArrayList<>();
        Veterinaire veterinaire= veterinaireRepo.findById(vetId).get();
        Vaccination vaccination = vaccinationRepo.findById(vaccinId).get();
        vaccinList =  vaccination.getVeterinaire();
        vaccinList.add(veterinaire);
        vaccination.setVeterinaire(vaccinList);
        vaccinationRepo.save(vaccination);
        return "success";
    }
}
