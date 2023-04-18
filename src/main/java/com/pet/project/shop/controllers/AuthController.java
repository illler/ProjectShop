package com.pet.project.shop.controllers;

import com.pet.project.shop.auth.AuthenticationRequest;
import com.pet.project.shop.auth.RegisterRequest;
import com.pet.project.shop.services.RegistrationService;
import com.pet.project.shop.util.PersonValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService service;

    private final PersonValidator personValidator;


    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request,
            BindingResult bindingResult){
        personValidator.validate(request.getEmail(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserDetails> authenticate(
            @RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));

    }
}
