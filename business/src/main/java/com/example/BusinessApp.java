package com.example;

import javafx.stage.Stage;
import org.update4j.LaunchContext;
import org.update4j.inject.InjectTarget;
import org.update4j.service.Launcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BusinessApp implements Launcher {

    private static final Path LOCK_DIR = Paths.get(System.getProperty("user.home"), ".myapp");

    @InjectTarget
    private Stage primaryStage;

    private static Stage stage;

    public static void main(String[] args) {
        System.out.println("[main] Hello, world!");
    }

    @Override
    public void run(LaunchContext launchContext) {
        try {
            Files.createDirectories(LOCK_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage = primaryStage;
        stage.show();
    }

    @Override
    public long version() {
        return Launcher.super.version();
    }
}
