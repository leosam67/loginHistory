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
        System.out.println("+ CheckerExecutorImpl(" + userCheckers + ", " + loginCheckers);
    }
    /*
    @Override
    public void addUserChecker(UserCheckerInt chk) {
        System.out.println("+ Added checker: " + chk.getClass().getName());
        userCheckers.add(chk);
    }
    @Override
    public void addLoginChecker(LoginCheckerInt chk) {
        System.out.println("+ Added checker: " + chk.getClass().getName());
        loginCheckers.add(chk);
    }
    */
    @Override
    public String check(User user, Login login) {
        StringBuilder sb = new StringBuilder();
        for(UserCheckerInt chk : userCheckers) {
            System.out.println("+ Checking " + chk.getClass().getName());
            String result = chk.checkData(user);
            if(result != null) {
                if(!sb.isEmpty()) sb.append('\n');
                sb.append(formatDateErrorMessage(result, user, login));
            }
        }
        for(LoginCheckerInt chk : loginCheckers) {
            System.out.println("+ Checking " + chk.getClass().getName());
            String result = chk.checkData(login);
            if(result != null) {
                if(!sb.isEmpty()) sb.append('\n');
                sb.append(formatDateErrorMessage(result, user, login));
            }
        }
        return sb.toString();
    }
    private String formatDateErrorMessage(String msg, User user, Login login) {
        return System.out.format("%s for %s, named %s, logged to %s",
                msg,
                user.getUserId(),
                user.getUserName(),
                login.getApplication()).toString();
    }
}
