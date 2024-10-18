package com.example.managerstudent.Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RegistrationManager {
    private Map<Student, Set<Course>> registrations = new HashMap<>();

    // Phương thức để chọn một môn học từ danh sách các môn học (giả sử lựa chọn đầu tiên)
    public String selectCourse(List<String> courses) {
        if (courses != null && !courses.isEmpty()) {
            return courses.get(0); // Chọn môn học đầu tiên (có thể điều chỉnh logic tùy ý)
        }
        return null;
    }

    // Hiển thị danh sách tất cả các môn học có sẵn (giả sử đã được lưu trong danh sách)
    public List<Course> displayCourses() {
        // Logic để lấy danh sách môn học từ một nguồn dữ liệu (ví dụ: file hoặc database)
        // Tạm thời trả về một danh sách giả định
        return Arrays.asList(
                new Course("C001", "Math", 3),
                new Course("C002", "Physics", 2),
                new Course("C003", "Chemistry", 4)
        );
    }

    // Yêu cầu đăng ký môn học (trả về danh sách các môn học mà sinh viên muốn đăng ký)
    public List<Course> requestRegistration() {
        // Giả sử logic để yêu cầu đăng ký sẽ trả về một danh sách các môn học đã chọn
        return displayCourses().subList(0, 2); // Trả về 2 môn đầu tiên để làm ví dụ
    }

    // Đăng ký một môn học cho sinh viên
    public void registerCourse(Student student, Course course) {
        if (student == null || course == null) {
            return; // Kiểm tra đầu vào hợp lệ
        }

        // Lấy danh sách môn học đã đăng ký của sinh viên
        Set<Course> registeredCourses = registrations.getOrDefault(student, new HashSet<>());

        // Thêm môn học mới vào danh sách đã đăng ký
        registeredCourses.add(course);
        registrations.put(student, registeredCourses);
    }

    // Kiểm tra xem sinh viên đã đăng ký môn học này hay chưa
    public boolean isRegistered(Student student, Course course) {
        Set<Course> registeredCourses = registrations.get(student);
        return registeredCourses != null && registeredCourses.contains(course);
    }

    // Hủy đăng ký một môn học cho sinh viên
    public void unregisterCourse(Student student, Course course) {
        Set<Course> registeredCourses = registrations.get(student);
        if (registeredCourses != null) {
            registeredCourses.remove(course);
        }
    }

    // Lấy danh sách các môn học đã đăng ký của sinh viên
    public Set<Course> getRegisteredCourses(Student student) {
        return registrations.getOrDefault(student, new HashSet<>());
    }

    // Lưu điểm số của sinh viên vào một file hoặc cơ sở dữ liệu (có thể điều chỉnh)
    public void saveScore(Student student, Map<Course, Double> scores) {
    }
}
