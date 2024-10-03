package com.example.managerstudent.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class MainController {
    @FXML
    private ImageView imageView; // Hình ảnh nền

    @FXML
    private void handleStudentLogin() {
        showLoginDialog("Đăng nhập sinh viên");
    }

    @FXML
    private void handleTeacherLogin() {
        showLoginDialog("Đăng nhập giảng viên");
    }

    private void showLoginDialog(String title) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle(title);

        // Tạo GridPane để chứa các trường nhập liệu
        GridPane grid = new GridPane();
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        grid.addRow(0, new javafx.scene.control.Label("Tên đăng nhập:"), usernameField);
        grid.addRow(1, new javafx.scene.control.Label("Mật khẩu:"), passwordField);

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(grid);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Hiển thị dialog và xử lý phản hồi
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            // Ghi thông tin đăng nhập vào file
            writeLoginToFile(username, password);
        }
    }

    // Phương thức ghi dữ liệu vào file login.txt
    private void writeLoginToFile(String username, String password) {
        String filePath = "src/main/resources/com/example/managerstudent/login.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Login: " + username + ", Password: " + password);
            writer.newLine();
            System.out.println("Dữ liệu đã được ghi vào file login.txt");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }
}
