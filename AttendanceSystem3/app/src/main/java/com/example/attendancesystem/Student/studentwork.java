package com.example.attendancesystem.Student;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.attendancesystem.MainActivity;
import com.example.attendancesystem.R;
import com.example.attendancesystem.bean.Attendance;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class studentwork extends AppCompatActivity {
    Button btnscanqr;
    String username;
    Attendance attendance;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    studentlogin studentlogin;
//    studentlogin studentlogin = new studentlogin();

    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentwork);
        btnscanqr = findViewById(R.id.btnscanner);
        username = getIntent().getStringExtra("username");
        btnscanqr.setOnClickListener(v -> ScanCode_NEW());
    }
    private void ScanCode() {
        String scannedData = barLaunch5.toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("scans");

        String scanId = ref.push().getKey();
        attendance.setStudent_id(Integer.valueOf(UUID.randomUUID().toString()));

        DatabaseReference scanRef = ref.child(scanId);
                scanRef.setValue(scanId).addOnSuccessListener(unused -> {
                    Toast.makeText(studentwork.this, "Attendance Done", Toast.LENGTH_SHORT).show();
                    ref.child("P");
                }).addOnFailureListener(e -> {
                    Toast.makeText(studentwork.this, "Attendance Couldn't be done ", Toast.LENGTH_SHORT).show();
                    ref.child("A");

                });

        ScanOptions scanOptions = new ScanOptions();
        barLaunch5.launch(scanOptions);
        scanOptions.setPrompt("Volume up to flash");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(true);
    }
    private void ScanCode_NEW() {
        String scannedData = barLaunch6.toString();
        DatabaseReference ref = database.getReference("scans");
        String scanId = ref.push().getKey();
        DatabaseReference scanRef = ref.child(scanId);
        scanRef.setValue(scanId).addOnSuccessListener(unused -> {
            Toast.makeText(studentwork.this, "Attendance Done", Toast.LENGTH_SHORT).show();
            scanRef.child("P").setValue(username);
        }).addOnFailureListener(e -> {
            Toast.makeText(studentwork.this, "Attendance Couldn't be done ", Toast.LENGTH_SHORT).show();
            scanRef.child("A").setValue(username);
        });

        ScanOptions scanOptions = new ScanOptions();
        barLaunch6.launch(scanOptions);
        scanOptions.setPrompt("Volume up to flash");
        scanOptions.setBeepEnabled(true);
        scanOptions.setOrientationLocked(true);
    }

    ActivityResultLauncher<ScanOptions> barLaunch5 = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            try {
                String[] parts = result.toString().split(";");
                LocalDateTime expirationTime;
                expirationTime = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime currentTime;
                currentTime = LocalDateTime.now();
                if (currentTime.isAfter(expirationTime)) {
                    Toast.makeText(this, "QR code has expired", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseReference scansRef = FirebaseDatabase.getInstance().getReference(String.valueOf(studentlogin.username));
                String scanId = scansRef.push().getKey();
                DatabaseReference scanRef = scansRef.child(scanId);
                scanRef.child("username").setValue(studentlogin.username);
                scanRef.child("qr_code").setValue(parts[0]);
                scanRef.child("timestamp").setValue(ServerValue.TIMESTAMP);
                scanRef.child("scanned").setValue(true);
                scanRef.child("P").setValue(studentlogin.username);

                Toast.makeText(this, "QR code scanned successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                // handle exception
            }
        }
    });
    ActivityResultLauncher<ScanOptions> barLaunch6 = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null) {
            try {
                String[] parts = result.toString().split(";");
                LocalDateTime expirationTime;
                expirationTime = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDateTime currentTime;
                currentTime = LocalDateTime.now();
                if (currentTime.isAfter(expirationTime)) {
                    Toast.makeText(this, "QR code has expired", Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseReference scansRef = FirebaseDatabase.getInstance().getReference("scans");
                String scanId = scansRef.push().getKey();
                DatabaseReference scanRef = scansRef.child(scanId);
                scanRef.child("username").setValue(username);
                scanRef.child("qr_code").setValue(parts[0]);
                scanRef.child("timestamp").setValue(ServerValue.TIMESTAMP);
                scanRef.child("scanned").setValue(true);
                scanRef.child("P").setValue(username);

                Toast.makeText(this, "QR code scanned successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                // handle exception
            }
        }
    });


}