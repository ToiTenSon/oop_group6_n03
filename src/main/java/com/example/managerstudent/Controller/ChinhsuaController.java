package com.example.managerstudent.Controller;

import com.example.managerstudent.Model.Course;
import com.example.managerstudent.Model.Student;
import com.example.managerstudent.Model.StudentManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ChinhsuaController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> genderColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField courseIdField;
    @FXML
    private TextField courseNameField;
    @FXML
    private TextField gradeField;

    private StudentManager studentManager;
    private ObservableList<Student> studentList;

    public ChinhsuaController() {
        studentManager = new StudentManager();
        studentList = FXCollections.observableArrayList(studentManager.getStudents());
    }

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStudentId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
        studentTable.setItems(studentList);

        genderComboBox.getItems().addAll("Nam", "Nữ");

        studentTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                idField.setText(String.valueOf(newValue.getStudentId()));
                nameField.setText(newValue.getStudentName());
                genderComboBox.setValue(newValue.getGender());
            }
        });
    }

    @FXML
    private void handleAddStudent() {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        String gender = genderComboBox.getValue();

        Student newStudent = new Student(id, name, gender);
        studentManager.addStudent(newStudent);
        studentList.add(newStudent);

        // Thông báo thông tin đăng nhập
        showAlert("Thông tin đăng nhập", "Tên đăng nhập: " + newStudent.getUsername() + "\nMật khẩu: " + newStudent.getPassword());

        idField.clear();
        nameField.clear();
        genderComboBox.setValue(null);
    }

    @FXML
    private void handleRemoveStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentManager.removeStudent(selectedStudent.getStudentId());
            studentList.remove(selectedStudent);
            showAlert("Thông báo", "Đã xóa sinh viên: " + selectedStudent.getStudentName());
        } else {
            showAlert("Chưa chọn sinh viên", "Vui lòng chọn một sinh viên để xóa.");
        }
    }

    @FXML
    private void handleUpdateStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String gender = genderComboBox.getValue();

            selectedStudent.setStudentId(id);
            selectedStudent.setStudentName(name);
            selectedStudent.setGender(gender);

            studentManager.updateStudent(selectedStudent);
            studentTable.refresh();

            idField.clear();
            nameField.clear();
            genderComboBox.setValue(null);
        } else {
            showAlert("Chưa chọn sinh viên", "Vui lòng chọn một sinh viên để cập nhật.");
        }
    }

    @FXML
    private void handleUpdateStudentGrade() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            String courseId = courseIdField.getText();
            String courseName = courseNameField.getText();

            // Kiểm tra xem ID môn học có tồn tại không
            if (!studentManager.isCourseExists(courseId)) {
                showAlert("Lỗi", "ID môn học không hợp lệ. Vui lòng kiểm tra lại.");
                return; // Dừng thực hiện nếu ID không hợp lệ
            }

            float grade;
            try {
                grade = Float.parseFloat(gradeField.getText());
            } catch (NumberFormatException e) {
                showAlert("Lỗi", "Điểm phải là một số hợp lệ.");
                return;
            }

            Course course = new Course(courseId, courseName, 3);
            studentManager.updateStudentGrade(selectedStudent.getStudentId(), course, grade);

            saveStudentDetailsToFile(selectedStudent, course.getCourseName(), grade);
            handleDetailStudent();
            studentTable.refresh();

            courseIdField.clear();
            courseNameField.clear();
            gradeField.clear();
        } else {
            showAlert("Chưa chọn sinh viên", "Vui lòng chọn một sinh viên để cập nhật điểm.");
        }
    }

    private void saveStudentDetailsToFile(Student student, String course, float grade) {
        String filePath = "src/main/resources/com/example/managerstudent/students.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String data = String.format("ID: %d, Họ Tên: %s, Giới Tính: %s, Tên đăng nhập: %s, Môn Học: %s, Điểm: %.2f%n",
                    student.getStudentId(), student.getStudentName(), student.getGender(),
                    student.getUsername(), course, grade);
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể lưu thông tin sinh viên vào tệp.");
        }
    }

    @FXML
    private void handleDetailStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/managerstudent/StudentDetail.fxml"));
                Parent root = loader.load();

                StudentDetailController detailController = loader.getController();
                detailController.setStudentDetails(selectedStudent);

                Stage stage = new Stage();
                stage.setTitle("Chi tiết sinh viên");
                stage.setScene(new Scene(root, 400, 300));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Chưa chọn sinh viên", "Vui lòng chọn một sinh viên để xem chi tiết.");
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