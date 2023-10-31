package com.example.testform;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView logoImageView;
    @FXML
    private ImageView novoImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File novoFile = new File("images/novo.png");
        Image novoImage = new Image(novoFile.toURI().toString());
        novoImageView.setImage(novoImage);


        File logoFile = new File("images/logo.png");
        Image logoImage = new Image(logoFile.toURI().toString());
        logoImageView.setImage(logoImage);
    }

    public void loginButtonOnAction(ActionEvent event){


        if(!usernameTextField.getText().isBlank() && !passwordTextField.getText().isBlank()) {
            validateLogin();
        } else {
            loginMessageLabel.setText("Preencha todos os campos.");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnetion();

        String verifyLogin = "SELECT count(1) FROM jogador WHERE usuario = '" + usernameTextField.getText() + "' AND senha = '" + passwordTextField.getText() + "'";

        try {

            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Login realizado com sucesso!");
                } else {
                    loginMessageLabel.setText("Login inválido. Tente novamente.");
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}