package ru.leosam.task4.loginhistory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.leosam.task4.loginhistory.checkers.CheckerException;
import ru.leosam.task4.loginhistory.controller.CheckerExecutor;
import ru.leosam.task4.loginhistory.entity.*;

import java.text.*;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

@Service
public class LogEntryImpl implements LogEntryService {
    public static String DELIMITER = "\t";
    private static final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private CheckerExecutor checkers;
    private UserRepository userRep;
    private LoginRepository loginRep;
    private AnnotationConfigApplicationContext context;

    @Autowired
    public void setAppContext(@Autowired AnnotationConfigApplicationContext ctx) {
        context = ctx;
    }

    @Autowired
    public void setCheckers(CheckerExecutor chk) {
        checkers = chk;
    }

    @Autowired
    public void setUserRep(@Autowired UserRepository userRep) {
        this.userRep = userRep;
    }

    @Autowired
    public void setLoginRep(@Autowired LoginRepository loginRep) {
        this.loginRep = loginRep;
    }

    @Override
    public void addEntry(String line) {
        String EXCEED_DATA = "Exceed data: ";
        // Login\tSurname Name Patronymic\tLoginDate\tApplicationName
        if (line == null) return;
        if (line.isEmpty()) return;
        User user = context.getBean(User.class);
        Login login = context.getBean(Login.class);
        int entryPart = 0;
        try {
            for (String part : line.split(DELIMITER)) {
                switch (entryPart++) {
                    case 0: user.setUserId(part); break;
                    case 1: user.setUserName(part); break;
                    case 2: login.setAccessDate(parseDate(part)); break;
                    case 3: login.setApplication(part); break;
                    default: System.out.println(EXCEED_DATA + line); return;
                }
            }
        } catch (CheckerException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        String result = checkers.check(user, login);
        if (result != null) {
            System.out.println(result);
            return;
        }
        Optional<Integer> userId = userRep.findByUserId(user.getUserId());
        if (userId.isPresent()) user.setId(userId.get());
        else {
            userRep.insertUser(user.getUserId(), user.getUserName());
            userId = userRep.findByUserId(user.getUserId());
            if (userId.isPresent()) {
                System.out.println("Inserted user's user.id = " + userId.get());
                user.setId(userId.get());
            }
        }
        System.out.println("id of " + user.getUserId() + " is " + user.getId());
        login.setUserId(user.getId());
        loginRep.insertLogin(login.getAccessDate(), user.getId(), login.getApplication());
        // TODO: login.setId(loginRep);
        userRep.save(user);
        loginRep.save(login);
        System.out.println("Saved...");
        System.out.println(user);
        System.out.println(login);
        System.out.println("Users are:");
        for(User us : userRep.findAll()) {
            System.out.println(us);
        }
        System.out.println("Logins are:");
        for(Login log : loginRep.findAll().stream().sorted(new Comparator<Login>() {
            @Override
            public int compare(Login o1, Login o2) {
                return o1.getId() - o2.getId();
            }
        }).toList()) {
            System.out.println(log);
        }
    }

    private Date parseDate(String text) throws CheckerException {
        try {
            return fmt.parse(text);
        } catch (ParseException e) {
            throw new CheckerException("Illegal date: " + text + ", " + e.getMessage());
        }
    }
}
