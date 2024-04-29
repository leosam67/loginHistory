package ru.leosam.task4.loginhistory;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.leosam.task4.loginhistory.services.LogEntryService;

@SpringBootTest
class LoginHistoryApplicationTests {
    @Test
    void checkMainProcess(ApplicationContext ctx) {
        LogEntryService srv = ctx.getBean(LogEntryService.class);
        srv.addEntry("LeoSam\tSemirenko leonid nikolaevich\t2024-04-24 12:30:05\tphone");
        srv.addEntry("LeoSam\tSemirenko leonid nikolaevich\t2024-04-24 16:30:25\tweb");
    }
}
