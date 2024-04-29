package ru.leosam.task4.loginhistory.controller;

import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.services.LogEntryService;

import java.io.IOException;

@Component
public interface Reader {
    void scanToService(LogEntryService service);
}
