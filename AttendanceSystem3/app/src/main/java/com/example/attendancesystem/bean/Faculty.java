package com.example.attendancesystem.bean;

public class Faculty {
//    faculty_id INT PRIMARY KEY,
//    name VARCHAR(255),
//    course_code VARCHAR(255),
//    FOREIGN KEY (course_code) REFERENCES Course(course_code)

    private Integer faculty_id;
    private String name,course_code,password;

    public Faculty() {
    }

    public Integer getFaculty_id() {
        return faculty_id;
    }

    public void setFaculty_id(Integer faculty_id) {
        this.faculty_id = faculty_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getFaculty_password() {
        return password;
    }

    public void setFaculty_password(String password) {
        this.password = password;
    }
}
