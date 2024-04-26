package ru.leosam.task4.loginhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.entity.User;
import ru.leosam.task4.loginhistory.entity.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void addUser(String userId, String userName) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public Optional<User> findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
//        return userRepository.findByUserId(userId).orElseThrow(()->new NoSuchElementException("No USER with id = '" + userId + "'"));
    }
}
