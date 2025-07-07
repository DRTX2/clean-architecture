package drtx.cleanArchitecture.example.infrastructure.persistence;

import drtx.cleanArchitecture.example.domain.model.User;
import drtx.cleanArchitecture.example.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserPersistenceRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public List<User> findAll(){
        return userJpaRepository.findAll().
                stream().
                map(UserPersistenceEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(UserPersistenceEntity::toDomain);
    }

    @Override
    @Transactional
    public User save(User user) {
        UserPersistenceEntity entity = new UserPersistenceEntity();
        if (user.getId() != null)
            entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());

        return userJpaRepository.save(entity).toDomain();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

}
