package ru.leosam.task4.loginhistory.controller;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.checkers.LoginCheckerInt;
import ru.leosam.task4.loginhistory.checkers.UserCheckerInt;
import ru.leosam.task4.loginhistory.entity.Login;
import ru.leosam.task4.loginhistory.entity.User;

public interface CheckerExecutor {
    String check(User user, Login login);
/*
    void addUserChecker(UserCheckerInt chk);

    void addLoginChecker(LoginCheckerInt chk);
 */
}
