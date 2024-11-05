package com.example.floranest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    // Declare UI elements
    private EditText emailInput, passwordInput;
    // Firebase database reference
    private FirebaseDatabase db;
    private DatabaseReference databaseUsers;
    private FirebaseAuth Auth; // Initialize Firebase Authentication

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Authentication
        Auth = FirebaseAuth.getInstance();

        // Initialize Firebase Database reference
        db = FirebaseDatabase.getInstance();
        databaseUsers = db.getReference("Users"); // Reference to the "Users" node in your Firebase Database

        // Initialize UI elements
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
    }

    // Redirect to Signup screen
    public void onClickSignupLink(View view) {
        startActivity(new Intent(this, SignupActivity.class));
    }

    // Login button click handler
    public void onClickLogin(View view) {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Input validation

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(MainActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
            return; // Exit if email is empty
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(MainActivity.this, "Please provide valid Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(MainActivity.this, "Password is required and must be longer than 6 characters", Toast.LENGTH_SHORT).show();
            return; // Exit if password is empty
        }

        // Check for password length
        if (password.length() <= 6) {
            Toast.makeText(MainActivity.this, "Password must be more than 6 characters", Toast.LENGTH_SHORT).show();
            return; // Exit if password is not long enough
        }

        Auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Display a success message
                    Toast.makeText(MainActivity.this, "Login successful!", Toast.LENGTH_LONG).show();

                    // Redirect to the HomeActivity
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                } else {
                    // Get the exception and display an appropriate message
                    String errorMessage;
                    if (task.getException() != null) {
                        errorMessage = task.getException().getMessage();
                    } else {
                        errorMessage = "Login failed. Please try again.";
                    }

                    // Display more user-friendly messages based on common Firebase exceptions
                    if (errorMessage.contains("no user record")) {
                        errorMessage = "No account found with this email. Please sign up.";
                    } else if (errorMessage.contains("password is invalid")) {
                        errorMessage = "Incorrect password. Please try again.";
                    } else if (errorMessage.contains("too many attempts")) {
                        errorMessage = "Too many failed login attempts. Please try again later.";
                    }

                    Toast.makeText(getApplicationContext(), "FAILED: " + errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}