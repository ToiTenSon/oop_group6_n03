package com.example.managerstudent.Model;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private int studentId;
    private String studentName;
    private String gender;
    private Map<Course, Float> grades;

    public Student(int studentId, String studentName, String gender) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.gender = gender;
        this.grades = new HashMap<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public void addGrade(Course course, float grade) {
        grades.put(course, grade);
    }

    public Map<Course, Float> getGrades() {
        return grades;
    }
}