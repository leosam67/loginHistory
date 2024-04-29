package ru.leosam.task4.loginhistory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.leosam.task4.loginhistory.controller.Reader;
import ru.leosam.task4.loginhistory.controller.ReaderFromFile;
import ru.leosam.task4.loginhistory.services.LogEntryService;

@SpringBootApplication
public class LoginHistoryApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LoginHistoryApplication.class, args);
        Reader reader = ctx.getBean(ReaderFromFile.class);
        LogEntryService service = ctx.getBean(LogEntryService.class);
        reader.scanToService(service);
    }
}
