package com.pet.project.shop.repositories;

import com.pet.project.shop.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmail(String email);
}
