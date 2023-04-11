package com.pet.project.shop.services;

import com.pet.project.shop.models.Person;
import com.pet.project.shop.models.Role;
import com.pet.project.shop.repositories.PersonRepository;
import com.pet.project.shop.security.PersonDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonDetailsServices implements UserDetailsService {

    private final PersonRepository personRepository;

    public List<Person> personList(){
        return personRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByEmail(username);
        if (person.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new PersonDetails(person.get());
    }


    @Transactional
    public void update(Person person){
        person.setRole(Role.ADMIN);
        personRepository.save(person);
    }
}
