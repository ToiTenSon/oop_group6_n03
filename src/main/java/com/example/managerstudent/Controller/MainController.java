package com.example.managerstudent.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class MainController {
    @FXML
    private ImageView imageView;

    @FXML
    private void handleStudentLogin() {
        showLoginDialog("Đăng nhập sinh viên", "Choice.fxml");
    }

    @FXML
    private void handleTeacherLogin() {
        showLoginDialog("Đăng nhập giảng viên", "Choices.fxml");
    }

    private void showLoginDialog(String title, String fxmlFile) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(title);

        GridPane grid = new GridPane();
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        grid.addRow(0, new Label("Tên đăng nhập:"), usernameField);
        grid.addRow(1, new Label("Mật khẩu:"), passwordField);

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(grid);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            writeLoginToFile(username, password);
            loadScene(fxmlFile);
        }
    }

    private void writeLoginToFile(String username, String password) {
        String filePath = "src/main/resources/com/example/managerstudent/login.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Login: " + username + ", Password: " + password);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Lỗi khi ghi vào file!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/" + fxmlFile));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) imageView.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Lỗi khi chuyển giao diện!", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
