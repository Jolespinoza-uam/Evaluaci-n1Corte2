package org.example.papeleriacampus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("productos-view.fxml"));
        stage.setScene(new Scene(loader.load(), 1050, 730));
        stage.setTitle("Papelería Campus - Catálogo de productos");
        stage.setMinWidth(900);
        stage.setMinHeight(680);
        stage.show();
    }
}
