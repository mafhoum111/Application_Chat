package com.example.mafhoumfz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Scene1Controller {
    @FXML
    private TextField usernameid;
    @FXML
    private PasswordField passwordid;
    @FXML
    protected void onLogin() throws IOException {

        if (usernameid.getText().equals("mafhoum") && passwordid.getText().equals("mafhoum")){
            Stage s= (Stage) usernameid.getScene().getWindow();

            FXMLLoader fx = new FXMLLoader(MinApp.class.getResource("Scene2.fxml"));
            Scene sc2 = new Scene(fx.load());
            s.setScene(sc2);

        }else{
            Alert alert= new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentification error");
            alert.setHeaderText("Username or Password not validated !");
            alert.setContentText("you can retry bu changing authentification information");
            alert.show();
        }



    }
}
