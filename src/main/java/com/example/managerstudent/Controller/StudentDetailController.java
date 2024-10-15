package com.example.managerstudent.Controller;

import com.example.managerstudent.Model.Course;
import com.example.managerstudent.Model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.Map;

public class StudentDetailController {
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label genderLabel;
    @FXML
    private ListView<String> gradeListView;
    @FXML
    private Button removeGradeButton;

    private Student student;

    @FXML
    private void initialize() {
        // Khi khởi tạo, vô hiệu hóa nút "Xóa Điểm" nếu không có điểm nào
        removeGradeButton.setDisable(true);
    }

    public void setStudentDetails(Student student) {
        this.student = student;
        idLabel.setText("ID: " + student.getStudentId());
        nameLabel.setText("Họ Tên: " + student.getStudentName());
        genderLabel.setText("Giới Tính: " + student.getGender());

        // Cập nhật danh sách điểm
        updateGradeListView();

        // Bật/ tắt nút "Xóa Điểm" dựa trên số lượng điểm
        removeGradeButton.setDisable(student.getGrades().isEmpty());
    }

    private void updateGradeListView() {
        gradeListView.getItems().clear();
        for (Map.Entry<Course, Float> entry : student.getGrades().entrySet()) {
            Course course = entry.getKey();
            Float grade = entry.getValue();
            gradeListView.getItems().add(course.getCourseName() + ": " + grade);
        }
    }

    @FXML
    private void handleRemoveGrade() {
        String selectedGrade = gradeListView.getSelectionModel().getSelectedItem();
        if (selectedGrade != null) {
            // Lấy tên môn học từ chuỗi đã chọn
            String courseName = selectedGrade.split(":")[0].trim();

            // Tìm khóa học trong danh sách điểm của sinh viên
            for (Map.Entry<Course, Float> entry : student.getGrades().entrySet()) {
                if (entry.getKey().getCourseName().equals(courseName)) {
                    // Gọi phương thức xóa điểm trong StudentManager
                    student.getGrades().remove(entry.getKey());
                    updateGradeListView();
                    break;
                }
            }
        } else {
            showAlert("Chưa chọn điểm", "Vui lòng chọn một điểm để xóa.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}