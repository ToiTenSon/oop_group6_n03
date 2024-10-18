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
        loadRegisteredCourses(); // Tải môn học đã đăng ký khi ID được thiết lập
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

    // Phương thức để tải và hiển thị môn học đã đăng ký
    private void loadRegisteredCourses() {
        try {
            ObservableList<Course> registeredCourses = FXCollections.observableArrayList();

            // Đọc từ file registered_courses.txt
            try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/managerstudent/registered_courses.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
                    if (parts.length >= 4) {
                        String idFromFile = parts[0];
                        String courseId = parts[2];
                        String courseName = parts[3];

                        // Chỉ thêm môn học nếu ID sinh viên khớp
                        if (this.studentId.equals(idFromFile)) {
                            registeredCourses.add(new Course(courseId, courseName, 3)); // Giả định 3 là số tín chỉ mặc định
                        }
                    }
                }
            }

            // Hiển thị thông tin trong bảng
            courseTable.setItems(registeredCourses);

            // Hiển thị thông báo nếu không có môn học nào
            if (registeredCourses.isEmpty()) {
                showAlert("Thông Báo", "Không có môn học nào đã đăng ký cho sinh viên này.");
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file đăng ký: " + e.getMessage());
            showAlert("Lỗi", "Không thể đọc file đăng ký môn học.");
        }
    }

    @FXML
    private void handleShowRegisteredCourses() {
        loadRegisteredCourses(); // Tải và hiển thị môn học đã đăng ký
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
