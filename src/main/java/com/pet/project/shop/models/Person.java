package com.pet.project.shop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "поле не должно быть пустым")
    private String firstname;

    @NotEmpty(message = "поле не должно быть пустым")
    private String lastname;

    @Email(message = "Это не похоже на почту")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Пока не придумал что то внятное")
    private String password;

    private String purchaseHistory;

    @Enumerated(EnumType.STRING)
    private Role role;
}
