
package ru.leosam.task4.loginhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.leosam.task4.loginhistory.checkers.CheckerException;
import ru.leosam.task4.loginhistory.controller.CheckerExecutor;
import ru.leosam.task4.loginhistory.entity.Login;
import ru.leosam.task4.loginhistory.entity.User;

import java.text.*;
import java.util.Date;

@Service
public class LogEntryImpl implements LogEntryService {
    public static String DELIMITER = "\t";
    private static final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private CheckerExecutor checkers;
    private AnnotationConfigApplicationContext context;
    @Autowired
    public void setAppContext(@Autowired AnnotationConfigApplicationContext ctx) {
        context = ctx;
    }
    @Autowired
    public void setCheckers(CheckerExecutor chk) {
        System.out.println("+ Set checkers to " + chk.getClass().getName());
        checkers = chk;
    }
    @Override
    public void addEntry(String line) {
        // Login\tSurname Name Patronymic\tLoginDate\tApplicationName
        if(line == null) return;
        if(line.isEmpty()) return;
        User user = context.getBean(User.class);
        Login login = context.getBean(Login.class);
        int entryPart = 0;
        for(String part : line.split(DELIMITER)) {
            switch(entryPart++) {
                case 0: user.setUserId(part); break;
                case 1: user.setUserName(part); break;
                case 2: login.setAccessDate(parseDate(part)); break;
                case 3: login.setApplication(part); break;
                default: throw new CheckerException("Exceed data: " + line);
            }
        }
        // TODO: Save user/login
        System.out.println("+ Created entry:\nUSER = " + user + "\nLOGIN = " + login);
        checkers.check(user, login);
        System.out.println("+ Corrected entry:\nUSER = " + user + "\nLOGIN = " + login);
    }

    private Date parseDate(String text) throws CheckerException {
        try {
            return fmt.parse(text);
        } catch (ParseException e) {
            throw new CheckerException("Illegal date: " + text + ", " + e.getMessage());
        }
    }
}
