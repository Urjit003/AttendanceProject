package com.example.attendancesystem.bean;

import android.app.Application;

import java.util.ArrayList;

public class newApplicationContext  extends Application {
    private ArrayList<Attendance> attendance;
    private Faculty faculty;
    private QRcode qRcode;
    private Student student;
    private Timetable timetable;
    private Course course;

    public QRcode getqRcode() {
        return qRcode;
    }

    public void setqRcode(QRcode qRcode) {
        this.qRcode = qRcode;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    private static newApplicationContext instance;

    public static synchronized newApplicationContext getInstance() {
        if (instance == null) {
            instance = new newApplicationContext();
        }
        return instance;
    }

    public ArrayList<Attendance> getAttendance() {
        return attendance;
    }

    public void setAttendance(ArrayList<Attendance> attendance) {
        this.attendance = attendance;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public QRcode getQRcode() {
        return qRcode;
    }

    public void setQRcode(QRcode qRcode) {
        this.qRcode = qRcode;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public void setTimetable(Timetable timetable) {
        this.timetable = timetable;
    }


    public static void setInstance(newApplicationContext instance) {
        newApplicationContext.instance = instance;
    }
}
