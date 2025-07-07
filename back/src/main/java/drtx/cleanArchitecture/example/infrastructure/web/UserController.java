package drtx.cleanArchitecture.example.infrastructure.web;

import drtx.cleanArchitecture.example.application.dto.UserCreateDto;
import drtx.cleanArchitecture.example.application.dto.UserDto;
import drtx.cleanArchitecture.example.infrastructure.usecase.UserUseCaseImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Usuarios") // asi se hereda esa informacion a los metodos y solo se ñade operation a los necesarios
@RestController
@RequestMapping("/users")
// @CrossOrigin(origins = "http://localhost:5173/users")
@RequiredArgsConstructor
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserUseCaseImpl userUseCase;

    @Operation(summary = "Lista todos los usuarios",
            description = "Devuelve un arreglo con todos los usuarios registrados")
    @GetMapping
    public List<UserDto> getUsers(){
        return  userUseCase.findAllUsers();
    }

    @Operation(summary = "Obtiene un usuario por ID", description = "Retorna el usuario con todos sus datos")
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        log.info("GET /users/{}", id);
        var dto = userUseCase.getUserById(id);
        log.info("Respuesta: {}", dto);
        return dto;
    }

    @Operation(summary = "Crea un usuario",
            description = "Recibe un UserCreateDto y devuelve el usuario creado con su ID")
    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserCreateDto userDTO) { // de paso se valida q no sea nulo
        return userUseCase.createUser(userDTO);
    }

    @Operation(summary = "Actualiza un usuario",
            description = "Actualiza los datos de un usuario existente por ID")
    @PutMapping("/{idUser}")
    public UserDto updateUser(@PathVariable Long idUser, @Valid @RequestBody UserCreateDto userDto){
        return userUseCase.updateUser(idUser, userDto);
    }

    @Operation(summary = "Elimina un usuario",
            description = "Borra el usuario por ID y devuelve el registro eliminado")
    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable Long id){
        return userUseCase.deleteUser(id);
    }

}