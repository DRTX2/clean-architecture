package drtx.cleanArchitecture.example.application.mapper;

import drtx.cleanArchitecture.example.application.dto.UserDto;
import drtx.cleanArchitecture.example.domain.model.User;

public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDTO);
}
