package drtx.cleanArchitecture.example.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para crear un usuario")
public record UserCreateDto(
        @Schema(description = "Nombre del usuario", example = "Juan Pérez")
        @NotBlank(message = "El campo nombre no puede estar vacío")
        String name,

        @Schema(description = "Correo electrónico del usuario", example = "juan.perez@example.com")
        @NotBlank(message = "El campo email no puede estar vacío")
        @Email(message = "El email debe ser válido")
        String email,

        @Schema(description = "Contraseña del usuario", example = "miContraseña123")
        @NotBlank(message = "La contraseña no puede estar vacía")
        String password
) {
}
