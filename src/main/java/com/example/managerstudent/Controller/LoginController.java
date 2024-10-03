package com.example.managerstudent.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    public void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vui lòng điền đầy đủ thông tin!", ButtonType.OK);
            alert.showAndWait();
        } else {
            // Xử lý logic đăng nhập
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Đăng nhập thành công!", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
