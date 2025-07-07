package drtx.cleanArchitecture.example.infrastructure.mapper;

import drtx.cleanArchitecture.example.application.mapper.UserMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperImpl extends UserMapper {
}
