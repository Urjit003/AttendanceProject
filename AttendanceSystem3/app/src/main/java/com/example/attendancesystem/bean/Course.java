package com.example.attendancesystem.bean;

public class Course {
//    course_code VARCHAR(255) PRIMARY KEY,
//    name VARCHAR(255),
//    schedule VARCHAR(255)
    private String course_code,name,schedule;

    public Course() {
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
