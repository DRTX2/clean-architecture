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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<UserDto>> getUsers() {
        var users = userUseCase.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Obtiene un usuario por ID", description = "Retorna el usuario con todos sus datos")
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        log.info("GET /users/{}", id);
        var dto = userUseCase.getUserById(id);
        log.info("Respuesta: {}", dto);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Crea un usuario",
            description = "Recibe un UserCreateDto y devuelve el usuario creado con su ID")
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserCreateDto userDTO) {
        var createdUser = userUseCase.createUser(userDTO);
        return ResponseEntity.status(201).body(createdUser);
    }

    @Operation(summary = "Actualiza un usuario",
            description = "Actualiza los datos de un usuario existente por ID")
    @PutMapping("/{idUser}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long idUser, @Valid @RequestBody UserCreateDto userDto) {
        var updatedUser = userUseCase.updateUser(idUser, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Elimina un usuario",
            description = "Borra el usuario por ID y devuelve el registro eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        var deletedUser = userUseCase.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }

    @Operation(summary = "Limpia la caché de usuarios")
    @CacheEvict(value = "users", allEntries = true)
    @GetMapping("/cache/clear")
    public ResponseEntity<String> clearUsersCache() {
        return ResponseEntity.ok("Cache de usuarios eliminada");
    }
}