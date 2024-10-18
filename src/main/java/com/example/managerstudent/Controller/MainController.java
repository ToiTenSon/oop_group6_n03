package com.example.managerstudent.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.*;
import java.util.Optional;

public class MainController {
    @FXML
    private ImageView imageView;

    // Tạo biến để lưu ID sinh viên hiện tại
    private String currentStudentId;

    @FXML
    private void handleStudentLogin() {
        showLoginDialog("Đăng nhập sinh viên", "Quanlymon1.fxml", "student");
    }

    @FXML
    private void handleTeacherLogin() {
        showLoginDialog("Đăng nhập giảng viên", "Choices.fxml", "teacher");
    }

    @FXML
    private void handleCbqlLogin() {
        showLoginDialog("Đăng nhập CBQL", "CBQL.fxml", "cbql");
    }

    private void showLoginDialog(String title, String fxmlFile, String userType) {
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

            // Kiểm tra thông tin đăng nhập
            if (isLoginValid(username, password, userType)) {
                currentStudentId = username; // ID sinh viên là tên đăng nhập
                loadScene(fxmlFile, currentStudentId); // Gọi loadScene với studentId
            } else {
                showAlert("Thông báo", "Tên đăng nhập hoặc mật khẩu không đúng!");
            }
        }
    }

    // Kiểm tra thông tin đăng nhập
    private boolean isLoginValid(String username, String password, String userType) {
        String filePath = "";

        // Xác định file cần đọc dựa trên userType
        if (userType.equals("student")) {
            filePath = "src/main/resources/com/example/managerstudent/login.txt"; // Đăng nhập sinh viên
        } else if (userType.equals("teacher")) {
            filePath = "src/main/resources/com/example/managerstudent/giaovien.txt"; // Đăng nhập giảng viên
        } else if (userType.equals("cbql")) {
            filePath = "src/main/resources/com/example/managerstudent/quanly.txt"; // Đăng nhập CBQL
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Giả sử định dạng lưu là "Login: username, Password: password"
                if (line.equals("Login: " + username + ", Password: " + password)) {
                    return true; // Thông tin đăng nhập hợp lệ
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc từ file: " + e.getMessage());
        }
        return false; // Thông tin đăng nhập không hợp lệ
    }

    // Hiển thị thông báo lỗi
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadScene(String fxmlFile, String studentId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/" + fxmlFile));
            Parent root = loader.load();

            // Lấy MonhocController và truyền ID sinh viên nếu cần
            if (fxmlFile.equals("Quanlymon1.fxml")) {
                MonhocController monhocController = loader.getController();
                monhocController.setStudentId(studentId); // Truyền ID sinh viên
            }

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
