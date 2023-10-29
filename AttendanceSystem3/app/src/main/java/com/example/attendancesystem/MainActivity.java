package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.attendancesystem.TableNodes.MakeNodes;

public class MainActivity extends BaseActivity {
    private Button startbtn;
    private MakeNodes makeNodes ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeNodes= new MakeNodes();

//        makeNodes.useallnodes();

//        makeNodes.attendancenode();
//        makeNodes.coursenode();
//        makeNodes.Facultnode();
//        makeNodes.QRcodenode();
//        makeNodes.studentnode();
//        makeNodes.Timetablenode();

        startbtn = findViewById(R.id.startbtn);
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openactivityactors();
                Toast.makeText(MainActivity.this, "Welcome...! Select Your Actor", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void  openactivityactors()
    {
        Intent intent = new Intent(this, actors.class);
        startActivity(intent);
    }
}