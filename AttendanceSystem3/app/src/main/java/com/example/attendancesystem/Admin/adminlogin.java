package com.example.attendancesystem.Admin;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.attendancesystem.MenuActivity;
import com.example.attendancesystem.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class adminlogin extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText username, password;
    Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.activity_adminlogin);

        admin = findViewById(R.id.admin);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        admin.setOnClickListener(v -> {
            String user_name = username.getText().toString();
            String pass_word = password.getText().toString();

            if (TextUtils.isEmpty(user_name)) {
                username.setError("Invalid User Name");
                return;
            } else if (TextUtils.isEmpty(pass_word)) {
                password.setError("Enter password");
                return;
            }

            mAuth.signInWithEmailAndPassword(user_name, pass_word)
                    .addOnCompleteListener(adminlogin.this, task -> {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(adminlogin.this, MenuActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
    //    private void firebaseAuthwithGoogle(GoogleSignInAccount account) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            Toast.makeText(adminlogin.this, "Signed in with" + user.getEmail(), Toast.LENGTH_SHORT).show();
//                            UpdateUI (mAuth);
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(adminlogin.this, "Sign in with google failed", Toast.LENGTH_SHORT).show();
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                            UpdateUI(mAuth);
//                        }
//                    }
//                    private void UpdateUI(FirebaseAuth mAuth) {
//                        Intent intent = new Intent(adminlogin.this,MenuActivity.class);
//                        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//
//                });
//    }
}