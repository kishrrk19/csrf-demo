package co.simplon.csrf_exercise.csrf_exercise.repositories;

import co.simplon.csrf_exercise.csrf_exercise.entities.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionJPARepository extends JpaRepository<UserSession, Long> {
    UserSession findBySessionId(String sessionId);
    Boolean existsBySessionId(String sessionId);

}
