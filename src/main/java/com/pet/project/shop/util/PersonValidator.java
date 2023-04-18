package com.pet.project.shop.util;

import com.pet.project.shop.models.Person;
import com.pet.project.shop.services.PersonDetailsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class PersonValidator implements Validator {

    private final PersonDetailsServices personDetailsServices;


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String email = (String) target;
        try {
            personDetailsServices.loadUserByUsername(email);
        }catch (UsernameNotFoundException ignored){
            return;
        }

        errors.rejectValue("email", "","Человек с таким email существует");
    }
}
