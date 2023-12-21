package com.example;

import javafx.application.Application;
import javafx.stage.Stage;
import org.update4j.Configuration;
import org.update4j.service.Delegate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BootstrapApp extends Application implements Delegate {
    @Override
    public void start(Stage stage) throws Exception {
        Configuration config = null;

        // Lê as configurações do arquivo XML remoto
        URL CONFIG_URL = new URL("http://docs.update4j.org/demo/business/config.xml");
        try (Reader in = new InputStreamReader(CONFIG_URL.openStream(), StandardCharsets.UTF_8)) {
            config = Configuration.read(in);
        } catch (IOException e) {
            System.err.println("Não foi possível carregar o arquivo de configuração");
        }

        assert config != null;
        BootstrapHandler bootstrapHandler = new BootstrapHandler(config);

        stage.setTitle("POC da biblioteca Update4j");
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();
    }

    @Override
    public void main(List<String> list) throws Throwable {
        launch();
    }

    public static void main(String[] args) {
        launch();
    }
}
