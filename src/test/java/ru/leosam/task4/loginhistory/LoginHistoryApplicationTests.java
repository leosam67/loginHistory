package ru.leosam.task4.loginhistory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.controller.CheckerExecutor;
import ru.leosam.task4.loginhistory.entity.Login;
import ru.leosam.task4.loginhistory.entity.User;
import ru.leosam.task4.loginhistory.services.LogEntryImpl;
import ru.leosam.task4.loginhistory.services.LogEntryService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@Component
class LoginHistoryApplicationTests {
    @Autowired
    private Login login;

    @Autowired
    private User user;
    @Autowired
    CheckerExecutor checkerExecutor;

    @Test
    public void contextLoads() throws Exception {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(LoginHistoryApplicationTests.class);
        user = ctx.getBean(User.class);
        login = ctx.getBean(Login.class);
        assertThat(user).isNotNull();
        assertThat(login).isNotNull();
        user.setUserId("LeoSam");
        user.setUserName("Leonid Semirenko");

        System.out.println(user);
        System.out.println(login);
    }

    @Test
    void readUsers() {
        StandardServiceRegistry standardRegistry
                = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        MetadataSources sources = new MetadataSources(standardRegistry);
        sources.addAnnotatedClass(User.class);
        Metadata metadata = sources.buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        Session session = sessionFactory.openSession();
        User us = session.get(User.class, 1);
        System.out.println("User: " + us);
    }

    @Test
    void saveUser() {
        StandardServiceRegistry standardRegistry
                = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        MetadataSources sources = new MetadataSources(standardRegistry);
        sources.addAnnotatedClass(User.class);
        Metadata metadata = sources.buildMetadata();
        SessionFactory sessionFactory = metadata.buildSessionFactory();
        Session session = sessionFactory.openSession();
        User us = new User(2, "UserId", "UserName");
        System.out.println("User: " + us);
        session.persist(us);
    }

    @Test
    void checkMainProcess() {
        StandardServiceRegistry standardRegistry
                = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        MetadataSources sources = new MetadataSources(standardRegistry);
        sources.addAnnotatedClass(LogEntryService.class);
        LogEntryService srv = new LogEntryImpl();
        srv.addEntry("LeoSam\tSemirenko leonid nikolaevich\t2024-04-24 12:30:05\tphone");
    }
}
