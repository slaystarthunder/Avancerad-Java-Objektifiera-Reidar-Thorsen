package com.example.javafileparserproject2;

import FileHandling.FileOpen;
import FileParsers.CSVParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import TablesAndSorting.TableViewSetup;

import java.io.File;
import java.util.List;
import javafx.stage.Stage;
import FileParsers.CSVParser;
import FileParsers.JSONParser;

public class HelloController {
    public Button openButton;
    public TableView UserInputData;

    @FXML
    protected void onOpenButtonClick() {
        Stage stage = (Stage) openButton.getScene().getWindow(); // Get the stage from the button's scene
        File selectedFile = FileOpen.chooseFile(stage, "*.csv", "*.json");

        if (selectedFile != null) {
            String fileName = selectedFile.getAbsolutePath();
            List<List<String>> data = null;

            if (fileName.endsWith(".csv")) {
                CSVParser csvParser = new CSVParser();
                data = csvParser.parseCSV(fileName);
            } else if (fileName.endsWith(".json")) {
                JSONParser jsonParser = new JSONParser();
                data = jsonParser.parseJSON(fileName);
            }

            if (data != null && !data.isEmpty()) {
                TableViewSetup.setupTableView(UserInputData, data);
            }
        }
    }
}
