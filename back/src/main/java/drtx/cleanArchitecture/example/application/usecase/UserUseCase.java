package drtx.cleanArchitecture.example.application.usecase;

import drtx.cleanArchitecture.example.application.dto.UserCreateDto;
import drtx.cleanArchitecture.example.application.dto.UserDto;

import java.util.List;

public interface UserUseCase {
    List<UserDto> findAllUsers();
    UserDto getUserById(Long id);
    UserDto createUser(UserCreateDto userDto);
    UserDto updateUser(Long id, UserCreateDto userDto);
    UserDto deleteUser(Long id);
}
