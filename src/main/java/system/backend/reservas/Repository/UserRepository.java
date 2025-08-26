package system.backend.reservas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import system.backend.reservas.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNome(String nome);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
