package drtx.cleanArchitecture.example.infrastructure.usecase;

import drtx.cleanArchitecture.example.application.dto.UserCreateDto;
import drtx.cleanArchitecture.example.application.dto.UserDto;
import drtx.cleanArchitecture.example.application.mapper.UserCreateMapper;
import drtx.cleanArchitecture.example.application.mapper.UserMapper;
import drtx.cleanArchitecture.example.application.usecase.UserUseCase;
import drtx.cleanArchitecture.example.domain.exception.EmailAlreadyExistsException;
import drtx.cleanArchitecture.example.domain.exception.InvalidUserDataException;
import drtx.cleanArchitecture.example.domain.model.User;
import drtx.cleanArchitecture.example.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service                               // Solo en infraestructure
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserCreateMapper userCreateMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public UserDto createUser(UserCreateDto userDTO) {
        Objects.requireNonNull(userDTO, "User data cannot be null");

        User user = userCreateMapper.toEntity(userDTO);
        String hashed = passwordEncoder.encode(userDTO.password());
        user.setPassword(hashed);

        if (!user.isValid()) throw new InvalidUserDataException("User data is invalid");
        if (userRepository.existsByEmail(user.getEmail())) throw new EmailAlreadyExistsException(user.getEmail());

        try {
            User saved = userRepository.save(user);
            return userMapper.toDto(saved);
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new EmailAlreadyExistsException("Email already exists: " + user.getEmail());
            }
            throw new ServiceException("Error saving user", ex);
        } catch (Exception ex) {
            throw new ServiceException("Unexpected error creating user", ex);
        }
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserCreateDto userCreateDTO) {
        User toUpdate = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User does not exist"));

        userCreateMapper.updateEntityFromDto(userCreateDTO, toUpdate);
        String hashed = passwordEncoder.encode(userCreateDTO.password());
        toUpdate.setPassword(hashed);

        User updated = userRepository.save(toUpdate);
        return userMapper.toDto(updated);
    }

    @Override
    @Transactional
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not registered"));
        userRepository.deleteById(id);
        return userMapper.toDto(user);
    }
}