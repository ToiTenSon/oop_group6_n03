package com.example.managerstudent.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class ChoicesController {

    @FXML
    private Button registerButton; // Nút Đăng ký môn (nếu có)
    @FXML
    private Button editStudentButton; // Nút Chỉnh sửa thông tin sinh viên
    @FXML
    private Button inputScoreButton; // Nút Nhập điểm sinh viên

    @FXML
    public void handleEditStudent() {
        try {
            // Tải giao diện chinhsua.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/chinhsua.fxml"));
            Parent root = loader.load();

            // Tạo một cảnh mới
            Stage stage = (Stage) editStudentButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Không thể mở giao diện chỉnh sửa sinh viên!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void handleInputScore() {
        try {
            // Tải giao diện nhapdiem.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/nhapdiem.fxml"));
            Parent root = loader.load();

            // Tạo một cảnh mới
            Stage stage = (Stage) inputScoreButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Không thể mở giao diện nhập điểm sinh viên!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void handleRegisterCourse() {
        try {
            // Tải giao diện dangkyhoc.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/dangkyhoc.fxml"));
            Parent root = loader.load();

            // Tạo một cảnh mới
            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Không thể mở giao diện đăng ký học!", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
