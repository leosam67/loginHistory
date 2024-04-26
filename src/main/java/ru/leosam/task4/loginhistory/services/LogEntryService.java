package ru.leosam.task4.loginhistory.services;

import org.springframework.stereotype.Service;

public interface LogEntryService {
    void addEntry(String line);
}
