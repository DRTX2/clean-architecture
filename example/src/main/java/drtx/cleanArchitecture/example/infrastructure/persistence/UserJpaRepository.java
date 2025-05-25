package drtx.cleanArchitecture.example.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserPersistenceEntity, Long> {
    boolean existsByEmail(String email);
}
