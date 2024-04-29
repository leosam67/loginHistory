package ru.leosam.task4.loginhistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.checkers.*;
import ru.leosam.task4.loginhistory.entity.Login;
import ru.leosam.task4.loginhistory.entity.User;

import java.util.HashSet;
import java.util.Set;

@Component
public class CheckerExecutorImpl implements CheckerExecutor {
    private final Set<UserCheckerInt> userCheckers;
    private final Set<LoginCheckerInt> loginCheckers;
    public CheckerExecutorImpl(@Autowired Set<UserCheckerInt> userCheckers, @Autowired Set<LoginCheckerInt> loginCheckers) {
        this.userCheckers = userCheckers;
        this.loginCheckers = loginCheckers;
    }
    @Override
    public String check(User user, Login login) {
        StringBuilder sb = new StringBuilder();
        for(UserCheckerInt chk : userCheckers) {
            String result = chk.checkData(user);
            if(result != null) {
                if(!sb.isEmpty()) sb.append('\n');
                sb.append(result);
            }
        }
        for(LoginCheckerInt chk : loginCheckers) {
            String result = chk.checkData(login);
            if(result != null) {
                if(!sb.isEmpty()) sb.append(", ");
                sb.append(result);
            }
        }
        if(!sb.isEmpty()) {
            sb.append(formatDateErrorMessage(user, login));
            return sb.toString();
        } else return null;
    }
    private String formatDateErrorMessage(User user, Login login) {
        return String.format(" for %s, named %s, logged to %s",
                user.getUserId(),
                user.getUserName(),
                login.getApplication());
    }
}
