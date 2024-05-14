package TablesAndSorting;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class TableViewSetup {

    public static void setupTableView(TableView<ObservableList<String>> tableView, List<List<String>> data) {
        tableView.getColumns().clear();

        // Assuming the first row is headers
        List<String> headers = data.get(0);

        for (int i = 0; i < headers.size(); i++) {
            final int columnIndex = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(headers.get(i));
            column.setCellValueFactory(cellData ->
                    new ReadOnlyStringWrapper(cellData.getValue().get(columnIndex)));
            tableView.getColumns().add(column);
        }

        // Adding the data to the TableView
        ObservableList<ObservableList<String>> tableData = FXCollections.observableArrayList();
        for (int i = 1; i < data.size(); i++) { // Skipping the header row
            ObservableList<String> row = FXCollections.observableArrayList(data.get(i));
            tableData.add(row);
        }

        tableView.setItems(tableData);
    }
}
