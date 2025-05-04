package co.simplon.csrf_exercise.csrf_exercise.repositories;

import co.simplon.csrf_exercise.csrf_exercise.entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<User, Long> {
    User findByUsername(@NotBlank String username);

}
