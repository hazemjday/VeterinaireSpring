package com.example.nonAuth.controller;





import com.example.nonAuth.modal.Vaccination;
import com.example.nonAuth.service.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vaccination")
public class VaccinationController {
    @Autowired
    VaccinationService vaccinationService;
    @GetMapping("all")
    public List<Vaccination> getDogs(){
        return vaccinationService.getVaccination();
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteVaccinatin(@PathVariable Long id) {
        return vaccinationService.deleteVaccination(id);
    }

//the post mapping will add at the same time his authentification to the website and his roles
    @PostMapping("add")
    public String vaccination(@RequestBody Vaccination vaccination){
        return vaccinationService.addVacination(vaccination);
    }



    @PutMapping("update/{idVacc}/{idProp}")
    public String addProprietaire(@PathVariable Long idVacc, @PathVariable Long idProp ){
return vaccinationService.addProprietaire(idVacc, idProp);
    }

    @PutMapping("vet/{vetId}/{vaccinId}")
    public String addVeterinaire(
            @PathVariable Long vetId,
            @PathVariable Long vaccinId
    ){
        return vaccinationService.addVeerinaire(vetId, vaccinId);
    }
}
