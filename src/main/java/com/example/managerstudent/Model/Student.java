package com.example.managerstudent.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Student {
    private int student_id;
    private String student_name;
    private String gender;
    private Map<Course, Optional<Float>> courses = new HashMap<>();

    public Student(int student_id, String student_name, String gender) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.gender = gender;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public String getStudentName() {
        return student_name;
    }

    public void setStudentName(String student_name) {
        this.student_name = student_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<Course, Optional<Float>> getCourses() {
        return courses;
    }

    public void setCourses(Map<Course, Optional<Float>> courses) {
        this.courses = courses;
    }
}