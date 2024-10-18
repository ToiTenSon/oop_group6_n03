package com.example.managerstudent.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

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
    public void handleEditCourse() {
        try {
            // Tải giao diện Quanlymon.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/Quanlymon.fxml"));
            Parent root = loader.load();

            // Tạo một cảnh mới
            Stage stage = (Stage) editStudentButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            // Ghi lại thông báo lỗi
            System.err.println("Lỗi: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR, "Không thể mở giao diện chỉnh sửa môn học: " + e.getMessage(), ButtonType.OK);
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
    public void handleBack(ActionEvent event) {
        try {
            // Tải giao diện trước đó từ FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/interface1.fxml"));
            Parent previousRoot = loader.load();

            // Lấy Stage hiện tại
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

            // Thiết lập Scene mới cho Stage và hiển thị
            Scene scene = new Scene(previousRoot);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void handleRegisterCourse() {
        try {
            // Tải giao diện dangkyhoc.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/Quanlymon1.fxml"));
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
