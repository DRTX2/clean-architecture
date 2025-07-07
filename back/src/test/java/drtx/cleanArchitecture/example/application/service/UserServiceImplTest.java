package drtx.cleanArchitecture.example.application.service;

import drtx.cleanArchitecture.example.application.dto.UserCreateDto;
import drtx.cleanArchitecture.example.application.dto.UserDto;
import drtx.cleanArchitecture.example.application.mapper.UserCreateMapper;
import drtx.cleanArchitecture.example.application.mapper.UserMapper;
import drtx.cleanArchitecture.example.domain.exception.EmailAlreadyExistsException;
import drtx.cleanArchitecture.example.domain.model.User;
import drtx.cleanArchitecture.example.domain.repository.UserRepository;
import drtx.cleanArchitecture.example.infrastructure.usecase.UserUseCaseImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

// JUnit → define y ejecuta pruebas.
// Mockito → simula el entorno para probar partes específicas.



@ExtendWith(MockitoExtension.class)
// Se utiliza para habilitar las funcionalidades de Mockito dentro de JUnit 5. Permite que las anotaciones como @Mock
// y @InjectMocks funcionen correctamente en los tests.

public class UserServiceImplTest {

    /*
     * Con @Mock, se crean mocks de los componentes dependientes del servicio. Crea un objeto simulado (mock) de una clase o interfaz.
     * Se usa para probar componentes de forma aislada, simulando el comportamiento de sus dependencias.
     *
     *
     * ¿Cómo se inyectan las dependencias?
     * Con @InjectMocks, Mockito inyecta los mocks en el objeto real (UserServiceImpl) mediante constructor,
     * setters o reflexión. Se basa en los mocks definidos con @Mock.
     *
     * */

    @Mock
    UserRepository repo;

    @Mock
    UserMapper userMapper;

    @Mock
    UserCreateMapper createMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    UserUseCaseImpl useCase;

    /*
     * Estructura de un test:
     *
     * Given - When - Then (Dado - Cuando - Entonces):
     * Given: preparar datos, mocks y estado inicial.
     * When: ejecutar el método a probar.
     * Then: verificar el resultado o comportamiento esperado (asserts, verify, etc).
     * */

    @Test
        // nombreDelMetodo_condición_efectoEsperado, por legabilidad y explicar proposito del test
    void createUser_whenEmailExists_throwsEmailAlreadyExistsException() {
        // dado
        var dto = new UserCreateDto("Ana", "ana@ej.com", "pwd");
        var user = new User(null, "Ana", "ana@ej.com", "pwd");
        // estos son varios comportamientos esperados que se simularan
        when(createMapper.toEntity(dto))
                // Define el comportamiento simulado de un mock. Le dices a Mockito:
                //"Cuando se llame a este método con estos parámetros, haz esto..."
                .thenReturn(user);
        // Especifica el valor que debe devolver el mock cuando se llama al método con esos parámetros.

        when(passwordEncoder.encode("pwd"))
                .thenReturn("hash");

        when(repo.existsByEmail("ana@ej.com"))
                .thenReturn(true);

        // cuando/entonces, Se está verificando el comportamiento esperado ante un caso específico:

        assertThrows(EmailAlreadyExistsException.class,
                () -> useCase.createUser(dto));
        // Espero que al ejecutar useCase.createUser(dto) se lance una excepción del tipo EmailAlreadyExistsException.
        // Si no se lanza, la prueba falla

        verify(repo, never()).save(any());
        // Verifica que nunca se haya llamado al método save(...) del mock userRepository, con cualquier argumento (any()).
        // comprueba comportamientos y no solo resultados. Por ejemplo, si no se debería guardar el usuario cuando el email ya existe.
    }

    @Test
    void createUser_success_returnsDto() {
        // dado
        var dto = new UserCreateDto("Luis", "luis@ej.com", "pwd");
        var user = new User(null, "Luis", "luis@ej.com", "pwd");
        var saved = new User(1L, "Luis", "luis@ej.com", "hash");
        var expectedDto = new UserDto(1L, "Luis", "luis@ej.com");

        when(createMapper.toEntity(dto))
                .thenReturn(user);
        when(passwordEncoder.encode("pwd"))
                .thenReturn("hash");
        when(repo.existsByEmail("luis@ej.com"))
                .thenReturn(false);
        when(repo.save(user))
                .thenReturn(saved);
        when(userMapper.toDto(saved))
                .thenReturn(expectedDto);

        // cuando

        UserDto result = useCase.createUser(dto);

        // entonces

        assertEquals(expectedDto, result);
        verify(repo).save(user);
        verify(userMapper).toDto(saved);
    }

    @Test
    void createUser_whenDbConstraintViolation_throwsEmailAlreadyExistsException() {
        // dado
        var dto = new UserCreateDto("Juan", "juan@ej.com", "pwd");
        var user = new User(null, "Juan", "juan@ej.com", "pwd");
        when(createMapper.toEntity(dto)).thenReturn(user);
        when(passwordEncoder.encode("pwd")).thenReturn("hash");
        when(repo.existsByEmail("juan@ej.com")).thenReturn(false);

        // forzar excepcion en bd

        ConstraintViolationException hibernateEx = new ConstraintViolationException("msg", null, "email");
        DataIntegrityViolationException wrapped = new DataIntegrityViolationException("integrity violation", hibernateEx);

        when(repo.save(user)).thenThrow(wrapped);

        assertThrows(EmailAlreadyExistsException.class, () -> useCase.createUser(dto));
    }
}