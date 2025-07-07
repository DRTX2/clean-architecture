package drtx.cleanArchitecture.example.infrastructure.web;

import drtx.cleanArchitecture.example.application.dto.UserCreateDto;
import drtx.cleanArchitecture.example.application.dto.UserDto;
import drtx.cleanArchitecture.example.infrastructure.usecase.UserUseCaseImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
// @CrossOrigin(origins = "http://localhost:5173/users")
@RequiredArgsConstructor
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserUseCaseImpl userUseCase;

    @GetMapping
    public List<UserDto> getUsers(){
        return  userUseCase.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        log.info("GET /users/{}", id);
        var dto = userUseCase.getUserById(id);
        log.info("Respuesta: {}", dto);
        return dto;
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserCreateDto userDTO) { // de paso se valida q no sea nulo
        return userUseCase.createUser(userDTO);
    }

    @PutMapping("/{idUser}")
    public UserDto updateUser(@PathVariable Long idUser, @Valid @RequestBody UserCreateDto userDto){
        return userUseCase.updateUser(idUser, userDto);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable Long id){
        return userUseCase.deleteUser(id);
    }

}