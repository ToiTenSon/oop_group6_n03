package com.example.managerstudent.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private static final String FILE_PATH = "src/main/resources/com/example/managerstudent/students.txt"; // Đường dẫn tới file lưu trữ

    public StudentManager() {
        loadStudentsFromFile(); // Nạp dữ liệu từ file khi khởi động
    }

    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile(); // Lưu dữ liệu sau khi thêm sinh viên
    }

    public void removeStudent(int student_id) {
        students.removeIf(student -> student.getStudentId() == student_id);
        saveStudentsToFile(); // Lưu dữ liệu sau khi xóa sinh viên
    }
    // Lưu tất cả sinh viên vào file
    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.getStudentId() + "," + student.getStudentName() + "," + student.getGender());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý lỗi ghi file
        }
    }

    // Nạp tất cả sinh viên từ file
    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String gender = parts[2];
                students.add(new Student(id, name, gender));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Xử lý lỗi đọc file
        } catch (NumberFormatException e) {
            e.printStackTrace(); // Xử lý lỗi định dạng số
        }
    }
    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getStudentId() == updatedStudent.getStudentId()) {
                // Cập nhật thông tin của sinh viên
                student.setStudentName(updatedStudent.getStudentName());
                student.setGender(updatedStudent.getGender());
                saveStudentsToFile(); // Lưu dữ liệu sau khi cập nhật sinh viên
                break; // Thoát vòng lặp khi tìm thấy sinh viên cần cập nhật
            }
        }
    }


    // Getter cho danh sách sinh viên
    public List<Student> getStudents() {
        return students;
    }
}
