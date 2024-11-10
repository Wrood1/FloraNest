package com.example.floranest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity {
    private EditText nameInput,emailInput,passwordInput;
    private Spinner citySpinner;
    private FirebaseDatabase db;
    private DatabaseReference databaseUsers;
    private FirebaseAuth Auth; // Initialize Firebase Authentication



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize Firebase Authentication
        Auth = FirebaseAuth.getInstance();

        // Initialize Firebase Database reference
        db = FirebaseDatabase.getInstance();
        databaseUsers = db.getReference("Users"); // Reference to the "Users" node in your Firebase Database


        // Initialize UI elements
         nameInput = (EditText)findViewById(R.id.name_input);
         emailInput = (EditText)findViewById(R.id.email_input);
         passwordInput = (EditText)findViewById(R.id.password_input);
         citySpinner = (Spinner)findViewById(R.id.city_spinner);
    }

    public void onClickloginLink(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onClickSignup (View view) {

        String name = nameInput.getText().toString();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String city = citySpinner.getSelectedItem().toString().trim();

        // Input validation
        if (name.isEmpty()) {
            Toast.makeText(SignupActivity.this, "Name is required", Toast.LENGTH_SHORT).show();
            return; // Exit if name is empty
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(SignupActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
            return; // Exit if email is empty
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(SignupActivity.this, "Please provide valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(SignupActivity.this, "Password is required", Toast.LENGTH_SHORT).show();
            return; // Exit if password is empty
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(SignupActivity.this, "Password is required and must be longer than 6 characters", Toast.LENGTH_SHORT).show();
            return; // Exit if password is empty
        }

        // Check for password length
        if (password.length() <= 6) {
            Toast.makeText(SignupActivity.this, "Password must be more than 6 characters", Toast.LENGTH_SHORT).show();
            return; // Exit if password is not long enough
        }
        // Check if the user has selected a valid city
        if (city.equals("Select your city")) { // Replace with your default option
            Toast.makeText(SignupActivity.this, "Please select a valid city", Toast.LENGTH_SHORT).show();
            return; // Exit if no valid city is selected
        }


        Auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(name, email, password, city);
                    FirebaseDatabase.getInstance().getReference("Users").child(
                            FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "User has been registered!", Toast.LENGTH_LONG).show();
                                // Redirect to the main page
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                finish();
                            } else {
                                String errorMessage;
                                if (task.getException() != null) {
                                    errorMessage = task.getException().getMessage();
                                } else {
                                    errorMessage = "Registration failed. Please try again.";
                                }

                                // Display more user-friendly messages based on common Firebase exceptions
                                if (errorMessage.contains("email address is already in use")) {
                                    errorMessage = "This email is already registered. Please log in.";
                                }

                                Toast.makeText(getApplicationContext(), "FAILED: " + errorMessage, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    String errorMessage;
                    if (task.getException() != null) {
                        errorMessage = task.getException().getMessage();
                    } else {
                        errorMessage = "Registration failed. Please try again.";
                    }

                    // Display more user-friendly messages based on common Firebase exceptions
                    if (errorMessage.contains("The email address is badly formatted")) {
                        errorMessage = "Please enter a valid email address.";
                    } else if (errorMessage.contains("The password is invalid")) {
                        errorMessage = "Password must be at least 6 characters long.";
                    }

                    Toast.makeText(getApplicationContext(), "FAILED: " + errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}