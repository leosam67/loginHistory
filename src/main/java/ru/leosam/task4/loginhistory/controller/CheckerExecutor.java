package ru.leosam.task4.loginhistory.controller;

import ru.leosam.task4.loginhistory.entity.Login;
import ru.leosam.task4.loginhistory.entity.User;

public interface CheckerExecutor {
    String check(User user, Login login);
}
