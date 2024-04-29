package ru.leosam.task4.loginhistory.checkers;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.LogTransformation;
import ru.leosam.task4.loginhistory.entity.User;

@Component
@LogTransformation
public class CapsChecker implements UserCheckerInt {
    @Override
    public String checkData(User user) {
        String EMPTY_USERNAME = "Empty user name";
        String userName = user.getUserName();
        if(userName == null)
            return EMPTY_USERNAME;
        StringBuilder sb = new StringBuilder();
        for (String part : userName.split("\s")) {
            if (part.isEmpty()) continue;
            if (!sb.isEmpty()) sb.append(' ');
            sb.append(part.substring(0, 1).toUpperCase())
                    .append(part.substring(1).toLowerCase());
        }
        user.setUserName(sb.toString());
        return null;
    }
}
