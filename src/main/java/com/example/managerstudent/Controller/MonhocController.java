package com.example.managerstudent.Controller;

import com.example.managerstudent.Model.Course;
import com.example.managerstudent.Model.CourseManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MonhocController {

    @FXML
    private TableView<Course> courseTable;
    @FXML
    private TableColumn<Course, String> idColumn;
    @FXML
    private TableColumn<Course, String> nameColumn;
    @FXML
    private TableColumn<Course, Integer> creditsColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField creditsField;

    private CourseManager courseManager;
    private ObservableList<Course> courseList;

    @FXML
    private void initialize() {
        try {
            // Khởi tạo CourseManager và courseList
            courseManager = new CourseManager();
            courseList = FXCollections.observableArrayList(courseManager.getListCourse());

            // Khởi tạo các cột của bảng
            idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseId()));
            nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourseName()));
            creditsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCredits()).asObject());
            courseTable.setItems(courseList);

            // Xử lý khi chọn một môn học trong bảng
            courseTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    idField.setText(newValue.getCourseId());
                    nameField.setText(newValue.getCourseName());
                    creditsField.setText(String.valueOf(newValue.getCredits()));
                }
            });
        } catch (Exception e) {
            System.out.println("Lỗi trong phương thức initialize: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddCourse() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int credits = Integer.parseInt(creditsField.getText());

            Course newCourse = new Course(id, name, credits);
            courseManager.addCourse(newCourse);
            courseList.add(newCourse);

            clearFields();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm môn học: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRemoveCourse() {
        try {
            Course selectedCourse = courseTable.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                courseManager.removeCourse(selectedCourse.getCourseId());
                courseList.remove(selectedCourse);
                showAlert("Thông báo", "Đã xóa môn học: " + selectedCourse.getCourseName());
            } else {
                showAlert("Chưa chọn môn học", "Vui lòng chọn một môn học để xóa.");
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi xóa môn học: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.clear();
        nameField.clear();
        creditsField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
