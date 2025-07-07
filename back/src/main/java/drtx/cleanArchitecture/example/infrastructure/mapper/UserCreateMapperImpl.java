package drtx.cleanArchitecture.example.infrastructure.mapper;

import drtx.cleanArchitecture.example.application.dto.UserCreateDto;
import drtx.cleanArchitecture.example.application.mapper.UserCreateMapper;
import drtx.cleanArchitecture.example.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserCreateMapperImpl extends UserCreateMapper {
    void updateEntityFromDto(UserCreateDto dto, @MappingTarget User entity);
}
