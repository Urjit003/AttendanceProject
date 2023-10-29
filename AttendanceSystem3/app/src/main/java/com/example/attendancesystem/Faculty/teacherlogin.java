package com.example.attendancesystem.Faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendancesystem.R;
import com.example.attendancesystem.bean.Faculty;
import com.example.attendancesystem.bean.newApplicationContext;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class teacherlogin extends AppCompatActivity {

    EditText username, password;
    private Button admin;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherlogin);
        admin = findViewById(R.id.admin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivityteacheractivity();
            }
        });
    }

    public void openactivityteacheractivity() {

        String user_name = username.getText().toString();
        String pass_word = password.getText().toString();

        if (TextUtils.isEmpty(user_name)) {
            username.setError("Invalid User Name");
        } else if (TextUtils.isEmpty(pass_word)) {
            password.setError("enter password");
        } else {
            mDatabase = FirebaseDatabase.getInstance().getReference("faculty_allinfo");
            mDatabase.orderByChild("faculty_username").equalTo(user_name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot facultySnapshot : snapshot.getChildren()) {
                            Faculty faculty = facultySnapshot.getValue(Faculty.class);
                            if (faculty.getFaculty_password().equals(pass_word)) {
                                // store user details in application context
                                newApplicationContext.getInstance().setFaculty(faculty);
                                Intent intent = new Intent(teacherlogin.this, teacherwork.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
