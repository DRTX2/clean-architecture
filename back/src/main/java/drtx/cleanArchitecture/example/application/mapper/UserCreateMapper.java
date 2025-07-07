package drtx.cleanArchitecture.example.application.mapper;


import drtx.cleanArchitecture.example.application.dto.UserCreateDto;
import drtx.cleanArchitecture.example.domain.model.User;

public interface UserCreateMapper {
    User toEntity(UserCreateDto dto);
    void updateEntityFromDto(UserCreateDto dto, User entity);
}
