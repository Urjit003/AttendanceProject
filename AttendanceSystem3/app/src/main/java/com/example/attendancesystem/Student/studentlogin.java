package com.example.attendancesystem.Student;

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
import com.example.attendancesystem.bean.Student;
import com.example.attendancesystem.bean.newApplicationContext;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentlogin extends AppCompatActivity {

    EditText username,password;
    private Button admin;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
        admin = findViewById(R.id.admin);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivitystudentactivity();
            }
        });
    }

    public void openactivitystudentactivity() {

        String user_name = username.getText().toString();
        Intent i= new Intent(studentlogin.this,studentwork.class);
        i.putExtra("USERNAME",user_name);

        String pass_word = password.getText().toString();

        if (TextUtils.isEmpty(user_name))
        {
            username.setError("Invalid User Name");
        }
        else if(TextUtils.isEmpty(pass_word))
        {
            password.setError("enter password");
        }
        else
        {
            mDatabase = FirebaseDatabase.getInstance().getReference("student_allinfo");
            mDatabase.orderByChild("student_username").equalTo(user_name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
//                            StudentBean studentBean = studentSnapshot.getValue(StudentBean.class);
                            Student student = studentSnapshot.getValue(Student.class);
                            if (student.getStudent_password().equals(pass_word)) {
                                // store user details in application context
                                newApplicationContext.getInstance().setStudent(student);
                                Intent intent = new Intent(studentlogin.this, studentwork.class);
                                intent.putExtra("username", user_name);
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
