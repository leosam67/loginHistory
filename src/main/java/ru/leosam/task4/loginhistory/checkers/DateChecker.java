package ru.leosam.task4.loginhistory.checkers;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.LogTransformation;
import ru.leosam.task4.loginhistory.entity.Login;

@Component
@LogTransformation
public class DateChecker implements LoginCheckerInt {
    @Override
    public String checkData(Login login) throws CheckerException {
        String EMPTY_DATE = "Empty date";
        return login.getAccessDate() != null ? null : EMPTY_DATE;
    }
}
