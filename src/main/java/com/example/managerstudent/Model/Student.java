package com.example.managerstudent.Model;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private int studentId;
    private String studentName;
    private String gender;
    private String username;
    private String password;
    private Map<Course, Float> grades;

    public Student(int studentId, String studentName, String gender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.username = String.valueOf(studentId); // Tên đăng nhập là mã số sinh viên
        this.password = "123456"; // Mật khẩu mặc định
        this.grades = new HashMap<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
        this.username = String.valueOf(studentId); // Cập nhật lại tên đăng nhập khi thay đổi mã số sinh viên
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addGrade(Course course, float grade) {
        grades.put(course, grade);
    }

    public Map<Course, Float> getGrades() {
        return grades;
    }
}