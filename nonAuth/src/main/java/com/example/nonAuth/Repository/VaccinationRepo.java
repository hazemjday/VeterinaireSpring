package com.example.nonAuth.Repository;



import com.example.nonAuth.modal.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRepo extends JpaRepository<Vaccination, Long> {

}
