package ru.leosam.task4.loginhistory.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.services.LogEntryService;

import java.io.IOException;

@Component
@Qualifier("file")
public interface ReaderFromFile extends Reader {
    void scanToService(LogEntryService service);
}
