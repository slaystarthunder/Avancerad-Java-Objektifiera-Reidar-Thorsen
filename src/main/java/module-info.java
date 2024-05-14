module com.example.javafileparserproject2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.javafileparserproject2 to javafx.fxml;
    exports com.example.javafileparserproject2;
    exports FileParsers;
    opens FileParsers to javafx.fxml;
}