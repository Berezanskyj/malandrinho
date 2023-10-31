module com.example.testform {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.java;



    opens com.example.testform to javafx.fxml;

    exports com.example.testform;
}