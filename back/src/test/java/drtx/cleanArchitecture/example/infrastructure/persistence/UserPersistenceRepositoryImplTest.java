package drtx.cleanArchitecture.example.infrastructure.persistence;

import drtx.cleanArchitecture.example.domain.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UserPersistenceRepositoryImplTest {

    @Autowired
    private CrudRepository<User, Long> userRepository; // Usa CrudRepository directamente

    @Test
    public void testSaveAndFindUser() {
        // Arrange
        User user = new User();
        user.setName("John");
        user.setEmail("john@example.com");

        // Act
        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("John", foundUser.get().getName());
        assertEquals("john@example.com", foundUser.get().getEmail());
    }

}
