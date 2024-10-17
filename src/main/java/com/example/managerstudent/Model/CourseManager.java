package com.example.managerstudent.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CourseManager {
    private List<Course> courses = new ArrayList<>();
    private final String filePath = "src/main/resources/com/example/managerstudent/monhoc.txt"; // Đường dẫn tới file lưu trữ

    // Constructor
    public CourseManager() {
        loadCourses(); // Tải dữ liệu từ file khi khởi tạo
    }

    // Thêm môn học vào danh sách
    public void addCourse(Course course) {
        courses.add(course);
        saveCourses(); // Lưu dữ liệu vào file sau khi thêm
    }

    // Xóa môn học dựa trên ID
    public void removeCourse(String courseId) {
        courses.removeIf(course -> course.getCourseId().equals(courseId));
        saveCourses(); // Lưu dữ liệu vào file sau khi xóa
    }

    // Cập nhật thông tin môn học
    public void updateCourse(Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(course.getCourseId())) {
                courses.set(i, course);
                saveCourses(); // Lưu dữ liệu vào file sau khi cập nhật
                break;
            }
        }
    }

    // Lấy danh sách môn học
    public List<Course> getListCourse() {
        return courses;
    }

    // Phương thức lưu danh sách môn học vào file
    private void saveCourses() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Course course : courses) {
                writer.write(course.getCourseId() + "," + course.getCourseName() + "," + course.getCredits());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu môn học: " + e.getMessage());
        }
    }

    // Phương thức đọc danh sách môn học từ file
    private void loadCourses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String id = data[0];
                    String name = data[1];
                    int credits = Integer.parseInt(data[2]);
                    courses.add(new Course(id, name, credits));
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc môn học: " + e.getMessage());
        }
    }
}
