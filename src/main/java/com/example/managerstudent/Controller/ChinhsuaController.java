package com.example.managerstudent.Controller;

import com.example.managerstudent.Model.Student;
import com.example.managerstudent.Model.StudentManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ChinhsuaController {
    @FXML
    private TableView<Student> studentTable; // Bảng hiển thị sinh viên
    @FXML
    private TableColumn<Student, Integer> idColumn; // Cột ID
    @FXML
    private TableColumn<Student, String> nameColumn; // Cột tên
    @FXML
    private TableColumn<Student, String> genderColumn; // Cột giới tính
    @FXML
    private TextField idField; // Trường nhập ID
    @FXML
    private TextField nameField; // Trường nhập tên
    @FXML
    private ComboBox<String> genderComboBox; // ComboBox cho giới tính

    private StudentManager studentManager;
    private ObservableList<Student> studentList; // Danh sách sinh viên cho bảng

    public ChinhsuaController() {
        studentManager = new StudentManager();
        studentList = FXCollections.observableArrayList(studentManager.getStudents());
    }

    @FXML
    private void initialize() {
        // Thiết lập các cột trong bảng
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStudentId()).asObject());
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStudentName()));
        genderColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));

        studentTable.setItems(studentList); // Đặt danh sách sinh viên cho bảng

        // Thiết lập các giá trị cho ComboBox
        genderComboBox.getItems().addAll("Nam", "Nữ");

        // Cập nhật trường nhập liệu khi chọn sinh viên
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

        // Tạo sinh viên mới
        Student newStudent = new Student(id, name, gender);

        // Thêm sinh viên vào danh sách
        studentManager.addStudent(newStudent);
        studentList.add(newStudent); // Cập nhật danh sách sinh viên trên giao diện

        // Xóa dữ liệu đã nhập
        idField.clear();
        nameField.clear();
        genderComboBox.setValue(null);
    }

    @FXML
    private void handleRemoveStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            studentManager.removeStudent(selectedStudent.getStudentId());
            studentList.remove(selectedStudent); // Cập nhật danh sách sinh viên trên giao diện
        } else {
            showAlert("Chưa chọn sinh viên", "Vui lòng chọn một sinh viên để xóa.");
        }
    }

    @FXML
    private void handleUpdateStudent() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            // Lấy thông tin từ các trường nhập liệu
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            String gender = genderComboBox.getValue();

            // Cập nhật thông tin cho sinh viên đã chọn
            selectedStudent.setStudentId(id);
            selectedStudent.setStudentName(name);
            selectedStudent.setGender(gender);

            // Gọi phương thức updateStudent trong StudentManager
            studentManager.updateStudent(selectedStudent);
            studentTable.refresh(); // Cập nhật bảng để hiển thị thông tin mới

            // Xóa dữ liệu đã nhập
            idField.clear();
            nameField.clear();
            genderComboBox.setValue(null);
        } else {
            showAlert("Chưa chọn sinh viên", "Vui lòng chọn một sinh viên để cập nhật.");
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
