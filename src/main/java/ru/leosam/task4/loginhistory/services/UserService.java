package ru.leosam.task4.loginhistory.services;

import org.springframework.stereotype.Service;
import ru.leosam.task4.loginhistory.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(String userId, String userName);
    List<User> getUsers();
    Optional<User> findUserByUserId(String userId);
}
