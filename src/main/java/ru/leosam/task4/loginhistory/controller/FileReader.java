package ru.leosam.task4.loginhistory.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Component
@Qualifier("file")
public class FileReader implements ReaderFromFile {
    @Getter
    private String fileName;

    public FileReader(@Value("${spring.application.pathinput") String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void setFileName(String name) {
        fileName = name;
    }

    @Override
    public void scanFile() {
        try(Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // TODO: Create an entity
                // TODO: Check that entity
                // TODO: Save the entity
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
