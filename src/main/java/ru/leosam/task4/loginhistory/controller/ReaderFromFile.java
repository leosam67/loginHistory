package ru.leosam.task4.loginhistory.controller;

import org.springframework.stereotype.Component;

@Component
public interface ReaderFromFile {
    void setFileName(String name);
    void scanFile();
}
