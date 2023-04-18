package com.pet.project.shop.services;

import com.pet.project.shop.auth.AuthenticationRequest;
import com.pet.project.shop.auth.RegisterRequest;
import com.pet.project.shop.exception.PersonNotFoundException;
import com.pet.project.shop.models.Person;
import com.pet.project.shop.models.Role;
import com.pet.project.shop.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
@RequiredArgsConstructor
public class RegistrationService {

    private final PersonRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final PersonDetailsServices personDetailsServices;

    public String register(RegisterRequest request) {
        Person user = Person.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        return "ok";
    }

    public UserDetails authenticate(AuthenticationRequest request) throws PersonNotFoundException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        return personDetailsServices.loadUserByUsername(request.getEmail());
    }
}
