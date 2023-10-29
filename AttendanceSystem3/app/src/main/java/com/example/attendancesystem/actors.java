package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.attendancesystem.Admin.adminlogin;
import com.example.attendancesystem.Faculty.teacherlogin;
import com.example.attendancesystem.Student.studentlogin;

public class actors extends AppCompatActivity {

    private Button admin;
    private Button teacher;
    private Button student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actors);

        admin = findViewById(R.id.admin);
        admin.setOnClickListener(view -> {
            Intent intent = new Intent(actors.this, adminlogin.class);
            startActivity(intent);
        });

        teacher = findViewById(R.id.teacher);
        teacher.setOnClickListener(view -> {
            Intent intent = new Intent(actors.this, teacherlogin.class);
            startActivity(intent);
        });

        student = findViewById(R.id.student);
        student.setOnClickListener(view -> {
            Intent intent = new Intent(actors.this, studentlogin.class);
            startActivity(intent);
        });
    }

}