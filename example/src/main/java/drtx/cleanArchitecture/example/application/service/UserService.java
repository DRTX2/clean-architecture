package drtx.cleanArchitecture.example.application.service;

import drtx.cleanArchitecture.example.application.dto.UserDTO;
import drtx.cleanArchitecture.example.domain.exception.EmailAlreadyExistsException;
import drtx.cleanArchitecture.example.domain.exception.InvalidUserDataException;
import drtx.cleanArchitecture.example.domain.model.User;
import drtx.cleanArchitecture.example.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAllUsers(){
        return userRepository.findAll().stream().map(
                user -> new UserDTO(user.getId(), user.getName(), user.getEmail())
        ).toList();
    }

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        Objects.requireNonNull(userDTO, "El DTO de usuario no puede ser nulo");

        User user = new User(userDTO.getName(), userDTO.getEmail());
        if (!user.isValid()) {
            throw new InvalidUserDataException("Datos de usuario inválidos");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException( user.getEmail() );
        }

        try {
            User savedUser = userRepository.save(user);
            return new UserDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail());
        } catch (DataIntegrityViolationException ex) {
            if (ex.getCause() instanceof ConstraintViolationException) {
                throw new EmailAlreadyExistsException("El email ya está registrado (verificación concurrente)");
            }
            throw new ServiceException("Error al guardar el usuario", ex);
        } catch (Exception ex) {
            throw new ServiceException("Error inesperado al crear usuario", ex);
        }
    }

    public UserDTO deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no registrado"));

        userRepository.deleteById(id);
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public UserDTO updateUser(Long id, UserDTO user){
        User userToUpdate= userRepository.findById(id).orElseThrow(()->new RuntimeException("User doen't exists"));
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userRepository.save(userToUpdate);
        return new UserDTO(userToUpdate.getId(), userToUpdate.getName(), userToUpdate.getEmail());
    }
}
