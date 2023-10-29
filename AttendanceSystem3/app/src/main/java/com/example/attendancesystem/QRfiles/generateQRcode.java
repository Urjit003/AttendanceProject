package com.example.attendancesystem.QRfiles;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attendancesystem.R;
import com.example.attendancesystem.Student.studentlogin;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class generateQRcode extends AppCompatActivity {

    EditText etInput;
    Button btGenerate;
    ImageView ivOutput;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qrcode);

        etInput = findViewById(R.id.et_input);
        btGenerate = findViewById(R.id.bt_generate);
        ivOutput = findViewById(R.id.iv_output);
        btGenerate.setOnClickListener(v -> {
            try {
                makeQR_NEW(12);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }
        });

    }
    public void makeQR_NEW(int validityInMinutes) throws WriterException {
        studentlogin studentlogin ;
        String sText = etInput.getText().toString().trim();
        MultiFormatWriter writer = new MultiFormatWriter();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusMinutes(validityInMinutes);
        String expirationTimeStr = expirationTime.format(formatter);
        sText+=":"+expirationTimeStr;
        int currentTimeInMinutes = currentTime.getHour() * 60 + currentTime.getMinute();
        if (currentTimeInMinutes < (expirationTime.getHour() * 60 + expirationTime.getMinute())) {
            // Generate QR code only if current time is less than expiration time
            BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            ivOutput.setImageBitmap(bitmap);
            InputMethodManager manager = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE
            );
            manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(), 0);
        } else {
            // Display message indicating that the QR code has expired
            Toast.makeText(this, "QR code has expired", Toast.LENGTH_SHORT).show();
        }
        saveQRCodeToDatabase(sText, expirationTime);
        InputMethodManager manager = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE
        );
        manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(), 0);
    }

    private void generateQRCode() {
        String text = etInput.getText().toString().trim();

        if (text.isEmpty()) {
            Toast.makeText(this, "Please enter some text", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate QR Code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 400, 400);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

            // Save QR Code
            String path = saveImage(bitmap);
            Toast.makeText(this, "QR Code saved to " + path, Toast.LENGTH_SHORT).show();

            // Add to Firebase
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("qr_codes");
            String id = databaseReference.push().getKey();
            databaseReference.child(id).child("text").setValue(text);
            databaseReference.child(id).child("path").setValue(path);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    private String saveImage(Bitmap bitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageName = "IMG_" + timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(storageDir, imageName);

        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
            outputStream.flush();
            outputStream.close();
            return imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }


    private void saveQRCodeToDatabase(String qrCodeText, LocalDateTime expirationTime) {
    // Initialize the Firebase database
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("QR_data");

    // Create a new QR code object
    QRcode qrCode = new QRcode(qrCodeText, expirationTime);

    // Save the QR code object to the database
    myRef.child(qrCodeText).setValue(qrCode)
            .addOnSuccessListener(aVoid -> {
                // Display message indicating that the QR code has been saved
                Toast.makeText(generateQRcode.this, "QR code saved successfully", Toast.LENGTH_SHORT).show();

            })
            .addOnFailureListener(e -> {
                // Display message indicating that there was an error saving the QR code
                Toast.makeText(generateQRcode.this, "Failed to save QR code", Toast.LENGTH_SHORT).show();
            });
}


    public void makeQR_old(int validityInMinutes) {
        String sText = etInput.getText().toString().trim();
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            DateTimeFormatter formatter;
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime currentTime;
            currentTime = LocalDateTime.now();
            LocalDateTime expirationTime;
            expirationTime = currentTime.plusMinutes(validityInMinutes);
            String expirationTimeStr;
            expirationTimeStr = expirationTime.format(formatter);
            sText+=":"+expirationTimeStr;
            Calendar calendar = Calendar.getInstance();
            int currentTimeInMinutes = calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE);
            if (currentTimeInMinutes < (currentTime.toLocalTime().toSecondOfDay() / 60) + validityInMinutes) {
                // Generate QR code only if current time is less than expiration time
                BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE, 350, 350);
                BarcodeEncoder encoder = new BarcodeEncoder();
                Bitmap bitmap = encoder.createBitmap(matrix);
                ivOutput.setImageBitmap(bitmap);
                InputMethodManager manager = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE
                );
                manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(), 0);
            } else {
                // Display message indicating that the QR code has expired
                Toast.makeText(this, "QR code has expired", Toast.LENGTH_SHORT).show();
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}