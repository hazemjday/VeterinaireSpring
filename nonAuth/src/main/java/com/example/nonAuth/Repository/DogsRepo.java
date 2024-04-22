package com.example.nonAuth.Repository;



import com.example.nonAuth.modal.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogsRepo extends JpaRepository<Dog,Long> {

    Optional<Dog> findByEspId(String id);
}
