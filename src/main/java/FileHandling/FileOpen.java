package FileHandling;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class FileOpen {

    public static File chooseFile(Stage stage, String... extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");

        // Set extension filter for select file types
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Files", extensions);
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        return fileChooser.showOpenDialog(stage);
    }
}
