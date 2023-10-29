package com.example.attendancesystem.bean;

public class QRcode {
//    code VARCHAR(255) PRIMARY KEY,
//    course_code VARCHAR(255),
//    schedule VARCHAR(255),
//    FOREIGN KEY (course_code) REFERENCES Course(course_code)

    private static String code,course_code,schedule;

    public QRcode() {
    }

    public static String getCode() {
        return code;
    }

    public static void setCode(String code) {
        QRcode.code = code;
    }

    public static String getCourse_code() {
        return course_code;
    }

    public static void setCourse_code(String course_code) {
        QRcode.course_code = course_code;
    }

    public static String getSchedule() {
        return schedule;
    }

    public static void setSchedule(String schedule) {
        QRcode.schedule = schedule;
    }
}
