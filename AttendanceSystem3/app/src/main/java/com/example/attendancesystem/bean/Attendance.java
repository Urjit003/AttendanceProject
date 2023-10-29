package com.example.attendancesystem.bean;

import java.sql.Timestamp;

public class Attendance {
//    attendance_id INT PRIMARY KEY,
//    course_code VARCHAR(255),
//    student_id INT,
//    date_time TIMESTAMP,
//    FOREIGN KEY (course_code) REFERENCES Course(course_code),
//    FOREIGN KEY (student_id) REFERENCES Student(student_id)
    private Integer attendance_id,student_id;
    private String course_code,datastring;
    private Timestamp date_time = Timestamp.valueOf(datastring);

    public Attendance() {
    }

    public Integer getAttendance_id() {
        return attendance_id;
    }

    public void setAttendance_id(Integer attendance_id) {
        this.attendance_id = attendance_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getDatastring() {
        return datastring;
    }

    public void setDatastring(String datastring) {
        this.datastring = datastring;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }
}

