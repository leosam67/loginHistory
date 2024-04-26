package ru.leosam.task4.loginhistory;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.checkers.*;
import ru.leosam.task4.loginhistory.entity.Login;
import ru.leosam.task4.loginhistory.entity.User;

import java.util.Date;

@Component
public class UnitTests {
    @Autowired
    private Login login;

    @Autowired
    private User user;
    @BeforeEach
    public void initLogEntry() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(UnitTests.class);
        user = ctx.getBean(User.class);
        login = ctx.getBean(Login.class);
    }
    @Test
    @DisplayName("checkAllCheckers")
    public void checkAllCheckers() {
        user.setUserId("leosam");
        user.setUserName(" leonid  semirenko ");
        login.setAccessDate(new Date());
        login.setApplication("web");
        (new CapsChecker()).checkData(user);
        Assertions.assertEquals(user.getUserName(), "Leonid Semirenko");
        (new CapitalsChecker()).checkData(user);
        Assertions.assertEquals("LEOSAM", user.getUserId());
        (new ApplicationChecker()).checkData(login);
        Assertions.assertEquals("web", login.getApplication());
        login.setApplication("phone");
        (new ApplicationChecker()).checkData(login);
        Assertions.assertEquals(ApplicationChecker.OTHER_PREFIX + "phone", login.getApplication());
        login.setAccessDate(null);
        Assertions.assertThrows(CheckerException.class, () -> (new DateChecker()).checkData(login));
        System.out.println(user.getId());
    }
}
