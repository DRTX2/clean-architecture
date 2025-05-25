package drtx.cleanArchitecture.example.domain.repository;

import drtx.cleanArchitecture.example.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    User save(User user);// create/update
    void deleteById(Long id);
    boolean existsByEmail(String email);
}
