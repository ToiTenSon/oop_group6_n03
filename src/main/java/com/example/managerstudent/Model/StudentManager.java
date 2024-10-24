package com.example.managerstudent.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private static final String FILE_PATH = "src/main/resources/com/example/managerstudent/students.txt";

    public StudentManager() {
        loadStudentsFromFile();
    }

    // Thêm sinh viên vào danh sách và lưu vào file
    public void addStudent(Student student) {
        students.add(student);
        saveStudentsToFile(); // Lưu thông tin sinh viên vào students.txt
    }

    // Xóa sinh viên dựa trên ID và lưu vào file
    public void removeStudent(int studentId) {
        students.removeIf(student -> student.getStudentId() == studentId);
        saveStudentsToFile();
    }

    // Cập nhật thông tin sinh viên
    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (student.getStudentId() == updatedStudent.getStudentId()) {
                student.setStudentName(updatedStudent.getStudentName());
                student.setGender(updatedStudent.getGender());
                saveStudentsToFile();
                break;
            }
        }
    }

    // Cập nhật điểm của sinh viên
    public void updateStudentGrade(int studentId, Course course, float grade) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                student.addGrade(course, grade);
                saveStudentsToFile();
                break;
            }
        }
    }

    // Lưu dữ liệu sinh viên vào file
    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.getStudentId() + "," + student.getStudentName() + "," + student.getGender());
                writer.newLine();
                for (Course course : student.getGrades().keySet()) {
                    writer.write(student.getStudentId() + "," + course.getCourseId() + "," + course.getCourseName() + "," + student.getGrades().get(course));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Nạp dữ liệu sinh viên từ file
    private void loadStudentsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String gender = parts[2];
                    students.add(new Student(id, name, gender));
                } else if (parts.length == 4) {
                    int studentId = Integer.parseInt(parts[0]);
                    String courseId = parts[1];
                    String courseName = parts[2];
                    float grade = Float.parseFloat(parts[3]);
                    Course course = new Course(courseId, courseName, 3);
                    for (Student student : students) {
                        if (student.getStudentId() == studentId) {
                            student.addGrade(course, grade);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getStudentNameById(String studentId) {
        for (Student student : students) {
            if (String.valueOf(student.getStudentId()).equals(studentId)) {
                return student.getStudentName();
            }
        }
        return null; // Trả về null nếu không tìm thấy sinh viên
    }


    public boolean isCourseExists(String courseId) {
        // Đọc thông tin môn học từ file monhoc.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/example/managerstudent/monhoc.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Kiểm tra xem ID có khớp không
                String[] parts = line.split(","); // Tách chuỗi theo dấu phẩy
                if (parts.length > 0 && parts[0].trim().equals(courseId)) {
                    return true; // Nếu tìm thấy ID môn học
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Nếu không tìm thấy
    }

    // Lấy danh sách sinh viên
    public List<Student> getStudents() {
        return students;
    }
}