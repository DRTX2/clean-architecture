package drtx.cleanArchitecture.example.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//contrato entre capa de app y presentacion
public class UserDTO {

    // buena practica si y solo si es una app complicada o grande,
    // esto da seguridad(datos sensibles excluidos y validaciones encerrando fallas),
    // flexibilidad(añadir props calculadas),
    // tambien optimizacion debido a que si se usa un ORM relaciones son costosas, ej @OneToMany -> 1...*
    // y mantenibildiad(- acoplamiento si cambia entidad/representacion), en pequeños no haria falta
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    private String email;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
