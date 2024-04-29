package ru.leosam.task4.loginhistory.checkers;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.LogTransformation;
import ru.leosam.task4.loginhistory.entity.User;

@Component
@LogTransformation
public class CapitalsChecker implements UserCheckerInt {
    @Override
    public String checkData(User user) {
        String NO_USERID = "No userId";
        if(user.getUserId() == null)
            return NO_USERID;
        user.setUserId(user.getUserId().toUpperCase());
        return null;
    }
}
