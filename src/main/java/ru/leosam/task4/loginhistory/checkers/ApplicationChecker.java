package ru.leosam.task4.loginhistory.checkers;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.LogTransformation;
import ru.leosam.task4.loginhistory.entity.Login;

import java.util.HashSet;
import java.util.Set;

@Component
@LogTransformation
public class ApplicationChecker implements LoginCheckerInt {
    final public static String OTHER_PREFIX = "other: ";
    final private Set<String> appKeywords = new HashSet<>();

    {
        appKeywords.add("web");
        appKeywords.add("mobile");
    }

    @Override
    public String checkData(Login login) throws CheckerException {
        String NO_APPLICATION = "No application";
        String app = login.getApplication();
        if(app == null)
            return NO_APPLICATION;
        if (app.isEmpty()) return null;
        for (String kwd : appKeywords) {
            if (app.equalsIgnoreCase(kwd)) {
                login.setApplication(kwd);
                return null;
            }
        }
        login.setApplication(OTHER_PREFIX + app);
        return null;
    }
}
