package drtx.cleanArchitecture.example.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public record UserDto(
        Long id,
        @NotBlank(message = "El nombre no puede estar vacío")
        String name,
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        String email
) implements Serializable {}