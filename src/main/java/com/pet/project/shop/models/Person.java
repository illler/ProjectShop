package com.pet.project.shop.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @Email(message = "Это не похоже на почту")
    @NotEmpty(message = "Электронная почта не должна быть пустой")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Пока не придумал что то внятное")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
