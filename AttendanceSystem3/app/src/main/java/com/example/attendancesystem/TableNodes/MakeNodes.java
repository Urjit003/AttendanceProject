    package com.example.attendancesystem.TableNodes;

    import com.example.attendancesystem.FacultyBean;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;

    import java.util.Date;
    import java.util.HashMap;
    import java.util.Map;

    public class MakeNodes {
        // Initialize Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Reference the Firebase nodes for each table
        DatabaseReference studentRef = database.getReference("Student");

        DatabaseReference courseRef = database.getReference("Course");
        DatabaseReference attendanceRef = database.getReference("Attendance");
        DatabaseReference qrCodeRef = database.getReference("QRCode");
        DatabaseReference facultyRef = database.getReference("Faculty");
        DatabaseReference timetableRef = database.getReference("Timetable");
       public void studentnode () {
            // Create a Student node
            Map<String, Object> studentMap = new HashMap<>();
            studentMap.put("student_id", 1);
            studentMap.put("name", "John Doe");
            studentMap.put("program", "Computer Science");
            studentRef.child("1").setValue(studentMap);
        }
       public void coursenode() {
           // Create a Course node
           Map<String, Object> courseMap = new HashMap<>();
           courseMap.put("course_code", "CS101");
           courseMap.put("name", "Introduction to Computer Science");
           courseMap.put("schedule", "MWF 10:00 AM - 11:00 AM");
           courseRef.child("CS101").setValue(courseMap);
       }
       public void attendancenode() {
           // Create an Attendance node
           Map<String, Object> attendanceMap = new HashMap<>();
           attendanceMap.put("attendance_id", 1);
           attendanceMap.put("course_code", "CS101");
           attendanceMap.put("student_id", 1);
           attendanceMap.put("date_time", new Date().toString());
           attendanceRef.child("1").setValue(attendanceMap);
       }
       public void QRcodenode() {
           // Create a QRCode node
           Map<String, Object> qrCodeMap = new HashMap<>();
           qrCodeMap.put("code", "ABCD1234");
           qrCodeMap.put("course_code", "CS101");
           qrCodeMap.put("schedule", "MWF 10:00 AM - 11:00 AM");
           qrCodeRef.child("ABCD1234").setValue(qrCodeMap);
       }
       public void Facultnode() {
           // Create a Faculty node
           Map<String, Object> facultyMap = new HashMap<>();
           facultyMap.put("faculty_id", 1);
           facultyMap.put("name", "Jane Smith");
           facultyMap.put("course_code", "CS101");
           facultyRef.child("1").setValue(facultyMap);
       }
       public void Timetablenode() {
           // Create a Timetable node
           Map<String, Object> timetableMap = new HashMap<>();
           timetableMap.put("course_code", "CS101");
           timetableMap.put("class_time", new Date().toString());
           timetableMap.put("location", "Room 101");
           timetableRef.child("CS101_" + new Date().getTime()).setValue(timetableMap);
       }
        public void useallnodes (){
           studentnode();
           coursenode();
           attendancenode();
           QRcodenode();
           Facultnode();
           Timetablenode();
        }

    }


