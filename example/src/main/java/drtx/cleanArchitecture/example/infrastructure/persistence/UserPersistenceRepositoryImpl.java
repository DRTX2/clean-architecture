package drtx.cleanArchitecture.example.infrastructure.persistence;

import drtx.cleanArchitecture.example.domain.model.User;
import drtx.cleanArchitecture.example.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPersistenceRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserPersistenceRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(UserPersistenceEntity::toDomain);
    }

    @Override
    public User save(User user) {
        UserPersistenceEntity entity = new UserPersistenceEntity(user);
        return userJpaRepository.save(entity).toDomain();
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

}
