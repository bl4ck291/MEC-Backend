package com.sante.store.dtos;

import com.sante.store.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "password is mandatory")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotBlank(message = "address is mandatory")
    private String address;

    @NotBlank(message = "phone is mandatory")
    private String phone;

    @NotNull
    private boolean active;

    @NotNull
    private Role role = Role.CUSTOMER;
}
