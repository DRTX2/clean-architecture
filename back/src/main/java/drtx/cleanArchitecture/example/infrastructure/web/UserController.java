package drtx.cleanArchitecture.example.infrastructure.web;

import drtx.cleanArchitecture.example.application.dto.UserCreateDto;
import drtx.cleanArchitecture.example.application.dto.UserDto;
import drtx.cleanArchitecture.example.infrastructure.usecase.UserUseCaseImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
// @CrossOrigin(origins = "http://localhost:5173/users")
@RequiredArgsConstructor
public class UserController {
    private final UserUseCaseImpl userService;

    @GetMapping
    public List<UserDto> getUsers(){
        return  userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(@Valid @RequestBody UserCreateDto userDTO) { // de paso se valida q no sea nulo
        return userService.createUser(userDTO);
    }

    @PutMapping("/{idUser}")
    public UserDto updateUser(@PathVariable Long idUser, @Valid @RequestBody UserCreateDto userDto){
        return userService.updateUser(idUser, userDto);
    }

    @DeleteMapping("/{id}")
    public UserDto deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}