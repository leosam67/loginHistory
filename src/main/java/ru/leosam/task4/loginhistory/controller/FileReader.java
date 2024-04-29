package ru.leosam.task4.loginhistory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.leosam.task4.loginhistory.services.LogEntryService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@Component
public class FileReader implements ReaderFromFile {
    private static final String COMMENT_TAG = "#";
    private String rootDir;

    public FileReader(@Value("${spring.application.pathinput}") String rootDir) {
        this.rootDir = rootDir;
    }

    @Override
    public void scanToService(@Autowired LogEntryService service) {
        if (rootDir == null) {
            System.out.println("No root directory is given in application.properties");
            return;
        }
        File dir = new File(rootDir);
        try {
            String workingDir = dir.getCanonicalPath();
            System.out.println("Scanning " + workingDir);
            File[] files = dir.listFiles();
            if (files == null) {
                throw new RuntimeException("Cannot access " + dir.getCanonicalPath());
            }
            for (File file : files) {
                try (Scanner sc = new Scanner(file)) {
                    System.out.println("Scanning " + file.getName());
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        System.out.println("+ LINE: " + line);
                        if (!line.startsWith(COMMENT_TAG))
                            service.addEntry(line);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
