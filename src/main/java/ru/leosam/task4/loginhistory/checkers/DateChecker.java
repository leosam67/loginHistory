package ru.leosam.task4.loginhistory.checkers;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.entity.Login;

@Component
@LoginChecker
public class DateChecker implements LoginCheckerInt {
    private final String EMPTY_DATE = "Empty date";
    @Override
    public String checkData(Login login) throws CheckerException {
        return login.getAccessDate() != null ? null : EMPTY_DATE;
    }
}
