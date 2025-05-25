package drtx.cleanArchitecture.example.infrastructure.web;

import drtx.cleanArchitecture.example.application.dto.UserDTO;
import drtx.cleanArchitecture.example.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}") //loc:/users/5
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDTO createUser( @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/{idUser}") // localhost:8080/users/idUser + json
    public UserDTO updateUser(@PathVariable Long idUser, @RequestBody UserDTO userDto){
        return userService.updateUser(idUser, userDto);
    }
    @DeleteMapping("/{id}")
    public UserDTO deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}