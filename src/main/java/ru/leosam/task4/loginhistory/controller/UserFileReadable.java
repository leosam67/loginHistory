package ru.leosam.task4.loginhistory.controller;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.entity.User;

@Component
public interface UserFileReadable {
    User read();
}
