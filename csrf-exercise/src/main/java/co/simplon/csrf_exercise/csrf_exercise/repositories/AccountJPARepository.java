package co.simplon.csrf_exercise.csrf_exercise.repositories;

import co.simplon.csrf_exercise.csrf_exercise.entities.Account;
import co.simplon.csrf_exercise.csrf_exercise.entities.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountJPARepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(@NotBlank String s);

    Account findByUser(User user);
}
