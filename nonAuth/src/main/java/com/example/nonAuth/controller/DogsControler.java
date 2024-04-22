package com.example.nonAuth.controller;


import com.example.nonAuth.modal.Dog;
import com.example.nonAuth.modal.EspDog;
import com.example.nonAuth.modal.Proprietaire;
import com.example.nonAuth.service.DogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("dogs")
public class DogsControler {
@Autowired
DogsService dogsService;

//get al the dogs that exists
    @GetMapping("/all")
    public List<Dog> getDogs(){
        return dogsService.getDogs();
    }

    @GetMapping("/getById/{id}")
    public Optional<Dog> getDogById(@PathVariable Long id){
        return dogsService.getDogById(id);
    }

    @GetMapping("/getPropByid/{id}")
    public Optional<Proprietaire> getProprietaire(@PathVariable Long id){
        return dogsService.getProprietaire(id);
    }
    //get dogs information for esp
    @GetMapping("/geEsp/{id}")
    public Optional<EspDog> getDogEsp(@PathVariable String id){
        return dogsService.getDogEsp(id);
    }



    //delete a dog by his id

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteDog(@PathVariable Long id) {
        return dogsService.deleteDog(id);
    }

//add a dog
    @PostMapping("/add")
    public String addDog(@RequestBody Dog dog){
        return dogsService.addDog(dog);
    }


    //update nformations about a dog
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateDog(@PathVariable Long id, @RequestBody Dog dog) {
        return  dogsService.updateDog(id, dog);}


    //get the age of te dog starting from the birthday
    @GetMapping("dogAge/{id}")
    public String calculateAge(@PathVariable Long id) {
        return dogsService.getage(id);
    }
    // when he click on a button vaccin this will automatically update the date of the vaccination of the dog to this day
@PutMapping("dogVaccination/{idDog}")
public  String setDogVaccination(@PathVariable Long idDog,@RequestBody LocalDate latestDate){
       return dogsService.setDogVaccination(idDog, latestDate);
}

//if the dog is vaccinated it will return true
    //this is to check the dog with the esp controller
    @GetMapping("checkDogEsp/{idDog}")
    public Boolean dogVaccinatedEsp(@PathVariable String idDog){
        return dogsService.dogVaccinatedEsp(idDog);
    }

  //  this is to check normal dogs
    @GetMapping("checkDog/{idDog}")
    public Boolean dogVaccinated(@PathVariable Long idDog){
        return dogsService.dogVaccinated(idDog);
    }

    // add an espid for special dogs
    @PutMapping("addEsp/{idDog}/{espId}")
    public String addEspId(@PathVariable Long idDog, @PathVariable String  espId){
        return dogsService.addEspId(idDog, espId);
    }
}
