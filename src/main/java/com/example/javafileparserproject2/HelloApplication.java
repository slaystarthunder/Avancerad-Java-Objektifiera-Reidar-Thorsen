package com.example.javafileparserproject2;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        stage.setTitle("");
        stage.setScene(scene);

        stage.getIcons().add(new Image("file:src/main/files/file_icon.png"));

        // Custom placeholder text
        Label customPlaceholder = new Label("Please open a file to display data");

        // Set the custom placeholder
        TableView<ObservableList<String>> tableView = (TableView<ObservableList<String>>) scene.lookup("#UserInputData");
        tableView.setPlaceholder(customPlaceholder);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
