package ru.leosam.task4.loginhistory.checkers;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.entity.User;

@Component
@UserChecker
public class CapitalsChecker implements UserCheckerInt {
    @Override
    public String checkData(User user) {
        user.setUserId(user.getUserId().toUpperCase());
        return null;
    }
}
