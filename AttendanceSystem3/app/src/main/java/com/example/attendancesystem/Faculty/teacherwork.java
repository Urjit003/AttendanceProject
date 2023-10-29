package com.example.attendancesystem.Faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.attendancesystem.QRfiles.generateQRcode;
import com.example.attendancesystem.R;
import com.example.attendancesystem.newmainactivity;

public class teacherwork extends AppCompatActivity {
    private Button btn3;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherwork);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(view -> interconnectivity_class());
        btn2 = findViewById(R.id.btnqrcode);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(teacherwork.this, generateQRcode.class);
                startActivity(intent);
            }
        });

    }


    public  void interconnectivity_class()
    {
        Intent intent = new Intent(this, newmainactivity.class);
        startActivity(intent);
    }
}