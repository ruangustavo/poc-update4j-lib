package com.example;

import javafx.stage.Stage;
import org.update4j.LaunchContext;
import org.update4j.service.Launcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BusinessApp implements Launcher {

    private static final Path LOCK_DIR = Paths.get(System.getProperty("user.home"), ".myapp");

    @Override
    public void run(LaunchContext launchContext) {
        try {
            Files.createDirectories(LOCK_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("POC da biblioteca Update4j");
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();
    }

    @Override
    public long version() {
        return 0;
    }
}
