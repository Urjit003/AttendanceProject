package com.example.attendancesystem.bean;

import java.sql.Timestamp;

public class Timetable {
//    course_code VARCHAR(255),
//    class_time TIMESTAMP,
//    location VARCHAR(255),
//    PRIMARY KEY (course_code, class_time),
//    FOREIGN KEY (course_code) REFERENCES Course(course_code)
    private String course_code,location,datastring="";
    private Timestamp class_time = Timestamp.valueOf(datastring);

    public Timetable() {
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDatastring() {
        return datastring;
    }

    public void setDatastring(String datastring) {
        this.datastring = datastring;
    }

    public Timestamp getClass_time() {
        return class_time;
    }

    public void setClass_time(Timestamp class_time) {
        this.class_time = class_time;
    }
}
