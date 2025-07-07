package drtx.cleanArchitecture.example.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDto(
        @NotBlank(message = "El campo nombre no puede estar vacío")
        String name,

        @NotBlank(message = "El campo email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        String password
) {
}
