module com.example.mafhoumfz{
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.mafhoumfz to javafx.fxml;
    exports com.example.mafhoumfz;
}