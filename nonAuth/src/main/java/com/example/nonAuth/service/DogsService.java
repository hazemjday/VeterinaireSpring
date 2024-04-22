package com.example.nonAuth.service;



import com.example.nonAuth.Repository.DogsRepo;
import com.example.nonAuth.modal.Dog;
import com.example.nonAuth.modal.EspDog;
import com.example.nonAuth.modal.Proprietaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class DogsService {

    @Autowired
    DogsRepo dogsRepo;

    public List<Dog> getDogs() {
        return dogsRepo.findAll();
    }

    public String addDog(Dog dog) {
        dogsRepo.save(dog);
        return "success";
    }

    public ResponseEntity<String> deleteDog(Long id) {
        Optional<Dog> dog = dogsRepo.findById(id);
        if (dog.isPresent()) {
            Dog doggie = dog.get();
            dogsRepo.delete(doggie);
            return ResponseEntity.ok("deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found id: " + id);
        }

    }

    public ResponseEntity<String> updateDog(Long id, Dog dog) {
        Optional<Dog> doggie = dogsRepo.findById(id);
        if (doggie.isPresent()) {
            Dog doggo = doggie.get();
            doggo.setImageUrl(dog.getImageUrl());
            doggo.setRace(dog.getRace());
            doggo.setBirthdate(dog.getBirthdate());
            doggo.setWeight(dog.getWeight());
            doggo.setConfinement(dog.getConfinement());
            doggo.setUtilisation(dog.getUtilisation());
            doggo.setSexe(dog.getSexe());
            doggo.setCouleur(dog.getCouleur());
            doggo.setOrigine(dog.getOrigine());
            doggo.setEspId(dog.getEspId());
            dogsRepo.save(doggo);
            return ResponseEntity.ok("dog updated successfully");

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veterinaire not found with id: " + id);
        }
    }

    public String getage(Long id) {
        Optional<Dog> dog = dogsRepo.findById(id);
        Dog doggie = dog.get();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(doggie.getBirthdate(), currentDate);
        return (period.getYears() + " ans et " + period.getMonths() + " mois");
    }

    public String setDogVaccination(Long id, LocalDate latestDate) {
        Optional<Dog> dog = dogsRepo.findById(id);
        Dog doggie = dog.get();
        doggie.setLatestVaccinationDate(latestDate);
        dogsRepo.save(doggie);
        return "success";

    }

    public Boolean dogVaccinatedEsp(String id) {
        Optional<Dog> dog = dogsRepo.findByEspId(id);
        Dog doggie = dog.get();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(doggie.getLatestVaccinationDate(), currentDate);
        if (period.getYears() >= 1) {
            return false;
        } else {
            return true;
        }}

    public Boolean dogVaccinated(Long id) {
        Optional<Dog> dog = dogsRepo.findById(id);
        Dog doggie = dog.get();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(doggie.getLatestVaccinationDate(), currentDate);
        if (period.getYears() >= 1) {
            return false;
        } else {
            return true;
        }
    }



    public Optional<Dog> getDogById(Long id) {
        return dogsRepo.findById(id);
    }

    public Optional<Proprietaire> getProprietaire(Long id) {
        Optional<Dog> doggie = dogsRepo.findById(id);
        if (doggie.isPresent()) {
            Dog doggo = doggie.get();
            return Optional.ofNullable(doggo.getProprietaire());
        } else {
            return Optional.empty();
        }
    }


    public Optional<EspDog> getDogEsp(String id) {
        Optional<Dog> doggie = dogsRepo.findByEspId(id);
        EspDog myDog=new EspDog();
        if (doggie.isPresent()) {
            Dog doggo = doggie.get();
          myDog.setImageUrl(doggo.getImageUrl());
          myDog.setRace(doggo.getRace());
          myDog.setSexe(doggo.getSexe());
          myDog.setBirthdate(doggo.getBirthdate());
          Proprietaire proprietaire=doggo.getProprietaire();
          myDog.setNameProp(proprietaire.getName());
          myDog.setMailProp(proprietaire.getMail());
          myDog.setPhoneProp(proprietaire.getPhone());
          boolean vaccin = dogVaccinatedEsp(id);
          myDog.setVaccinated(vaccin);
            return Optional.ofNullable(myDog);
        } else {
            return Optional.empty();
        }
    }

    public String addEspId(Long idDog, String espId) {
        Optional<Dog> doggie = dogsRepo.findById(idDog);
        if (doggie.isPresent()) {
            Dog doggo = doggie.get();

            doggo.setEspId(espId);
            dogsRepo.save(doggo);
            return "dog espId added  successfully";

        }
        else {
            return "dog doesnt exist";
        }

    }
}