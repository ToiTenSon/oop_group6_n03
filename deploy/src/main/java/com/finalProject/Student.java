package com.finalProject;

import java.util.Map;
import java.util.HashMap;

public class Student {
    private int studentId;
    private String studentName;
    private String gender;
    private Map<Course, Float> courses;

    public Student(int studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courses = new HashMap<>();
    }
    
}
