module com.example.recc {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;
    requires java.desktop;


    opens com.example.recc to javafx.fxml;
    exports com.example.recc;
}