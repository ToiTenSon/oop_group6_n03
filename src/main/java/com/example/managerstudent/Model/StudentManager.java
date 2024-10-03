package com.example.managerstudent.Model;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
    }

    public void removeStudent(int student_id) {
    }

    public void updateStudent(Student student) {
    }

    public void addScore(Student student, Course course, float grade) {
    }

    public Student getStudent(int id) {
        return null;
    }

    public Course getCourseAvg(int minAvg) {
        return null;
    }

    public List<Student> displayRegisteredCourses() {
        return null;
    }

    public float getAverageGrade() {
        return 0.0f;
    }

    public void requestAddScore(List<Student> students) {
    }

    public boolean isValidScore() {
        return false;
    }
}