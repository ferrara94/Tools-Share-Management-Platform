package com.ferrara.tool.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotBlank(message = "Firstname is mandatory")
    private String firstName;
    @NotBlank(message = "Lastname is mandatory")
    private String lastName;
    @Email(message = "Email doesn't follow the format email@example.com")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Size(min=8, message="Password should be at least 8 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;

}
