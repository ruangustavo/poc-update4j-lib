package com.example;

import javafx.concurrent.Task;
import org.update4j.Archive;
import org.update4j.Configuration;
import org.update4j.UpdateOptions;
import org.update4j.inject.Injectable;
import org.update4j.service.UpdateHandler;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BootstrapHandler implements UpdateHandler, Injectable {

    public BootstrapHandler(Configuration config) {
        Task<Void> makeUpdate = new Task<>() {

            // estudar mais esse código
            @Override
            protected Void call() throws Exception {
                Path compressedAppPath = Paths.get("business-update.zip");
                UpdateOptions.ArchiveUpdateOptions archiveUpdateOptions = UpdateOptions.archive(compressedAppPath);

                if (config.update(archiveUpdateOptions.updateHandler(BootstrapHandler.this)).getException() == null) {
                    Archive.read(compressedAppPath).install();
                }

                return null;
            }
        };

        new Thread(makeUpdate).start();
        config.launch(this);
    }

    @Override
    public void failed(Throwable t) {
        if (t instanceof Exception) {
            System.out.println("Update aborted");
        } else {
            System.out.println("Failed: " + t.getClass().getSimpleName() + ": " + t.getMessage());
        }
    }
}
