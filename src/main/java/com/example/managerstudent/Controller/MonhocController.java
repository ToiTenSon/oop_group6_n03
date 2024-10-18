package com.example.managerstudent.Controller;

import com.example.managerstudent.Model.Course;
import com.example.managerstudent.Model.CourseManager;
import com.example.managerstudent.Model.StudentManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;

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
    private String studentId; // Biến để lưu ID sinh viên

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

    // Phương thức để lấy ID sinh viên từ MainController
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @FXML
    private void handleAddCourse() {
        try {
            String courseId = idField.getText();
            String courseName = nameField.getText();
            int credits = Integer.parseInt(creditsField.getText());

            Course newCourse = new Course(courseId, courseName, credits);
            courseManager.addCourse(newCourse);
            courseList.add(newCourse);

            // Lưu thông tin đăng ký môn học
            saveRegisteredCourse(studentId, courseId, courseName);

            clearFields();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm môn học: " + e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    private void handleShowRegisteredCourses() {
        try {
            StringBuilder registeredCoursesInfo = new StringBuilder();

            // Đọc từ file registered_courses.txt
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/managerstudent/registered_courses.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
                    if (parts.length >= 4) {
                        String studentId = parts[0];
                        String studentName = parts[1];
                        String courseId = parts[2];
                        String courseName = parts[3];

                        // Thêm thông tin vào StringBuilder
                        registeredCoursesInfo.append("ID Sinh Viên: ").append(studentId)
                                .append(", Tên Sinh Viên: ").append(studentName)
                                .append(", Môn Học: ").append(courseId)
                                .append(" - ").append(courseName).append("\n");
                    }
                }
            }

            // Hiển thị thông tin
            if (registeredCoursesInfo.length() > 0) {
                showAlert("Thông Tin Môn Học Đã Đăng Ký", registeredCoursesInfo.toString());
            } else {
                showAlert("Thông Báo", "Không có môn học nào đã đăng ký.");
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file đăng ký: " + e.getMessage());
            showAlert("Lỗi", "Không thể đọc file đăng ký môn học.");
        }
    }

    @FXML
    private void handleAddCoursee() {
        try {
            String courseId = idField.getText();
            String courseName = nameField.getText();
            int credits = Integer.parseInt(creditsField.getText());

            // Lưu thông tin đăng ký môn học
            saveRegisteredCourse(studentId, courseId, courseName);

            clearFields();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm môn học: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveRegisteredCourse(String studentId, String courseId, String courseName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/com/example/managerstudent/registered_courses.txt", true))) {
            String studentName = getStudentNameById(studentId); // Lấy tên sinh viên từ ID
            writer.write(studentId + "," + studentName + "," + courseId + "," + courseName);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu thông tin đăng ký: " + e.getMessage());
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


    private String getStudentNameById(String studentId) {
        // Đây là nơi gọi đến StudentManager để lấy tên sinh viên theo ID
        StudentManager studentManager = new StudentManager(); // Tạo mới StudentManager
        return studentManager.getStudentNameById(studentId); // Gọi phương thức lấy tên sinh viên
    }
}
