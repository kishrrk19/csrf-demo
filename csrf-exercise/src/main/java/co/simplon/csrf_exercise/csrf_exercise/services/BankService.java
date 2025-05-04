package co.simplon.csrf_exercise.csrf_exercise.services;

import co.simplon.csrf_exercise.csrf_exercise.dtos.Login;
import co.simplon.csrf_exercise.csrf_exercise.entities.Account;
import co.simplon.csrf_exercise.csrf_exercise.entities.User;
import co.simplon.csrf_exercise.csrf_exercise.entities.UserSession;
import co.simplon.csrf_exercise.csrf_exercise.repositories.AccountJPARepository;
import co.simplon.csrf_exercise.csrf_exercise.repositories.UserJPARepository;
import co.simplon.csrf_exercise.csrf_exercise.repositories.UserSessionJPARepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BankService {

    private final AccountJPARepository accounts;
    private final UserJPARepository users;
    private final UserSessionJPARepository userSessions;

    public BankService(AccountJPARepository accounts, UserJPARepository users, UserSessionJPARepository userSessions){
        this.accounts = accounts;
        this.users = users;
        this.userSessions = userSessions;
    }


    public void login(@Valid Login inputs, HttpServletResponse response) {
        User user = users.findByUsername(inputs.username());
        if(user != null && user.getPassword().equals(inputs.password())){
            String sessionID = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("SESSION_ID", sessionID);
            cookie.setAttribute("SameSite", "None");
            response.addCookie(cookie);

            LocalDateTime dateTime = LocalDateTime.now();
            UserSession userSession = new UserSession();
            userSession.setSessionId(sessionID);
            userSession.setUser(user);
            userSession.setCreatedAt(dateTime);
            userSessions.save(userSession);

        }
    }

    public boolean checkSessionId(String sessionId) {
        return userSessions.existsBySessionId(sessionId);
    }

    public void transfer(String toAccountNumber, int amount, String sessionId) {

        Account toAccount = accounts.findByAccountNumber(toAccountNumber);
        UserSession fromUserSession = userSessions.findBySessionId(sessionId);
        Account fromAccount = accounts.findByUser(fromUserSession.getUser());


        toAccount.setBalance(toAccount.getBalance() + amount);
        fromAccount.setBalance(fromAccount.getBalance() - amount);

        accounts.save(toAccount);
        accounts.save(fromAccount);

    }
}
