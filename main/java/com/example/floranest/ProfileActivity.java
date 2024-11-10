package com.example.floranest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    // Declare UI elements
    private TextView usernameText, emailText,passwordText, cityText;

    // Firebase references
    private DatabaseReference dbReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize Firebase authentication and database reference
        auth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference("Users");

        // Check if the user is logged in
        if (auth.getCurrentUser() == null) {
            // User is not logged in, redirect to MainActivity
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            finish(); // Finish current activity
            return; // Exit onCreate
        }

        // Initialize UI elements
        usernameText = (TextView) findViewById(R.id.username_text);
        emailText = (TextView) findViewById(R.id.email_text);
        passwordText =(TextView) findViewById(R.id.password_text);
        cityText = (TextView) findViewById(R.id.city_text);

        // Load user profile data from Firebase
        loadUserProfile();
    }

    private void loadUserProfile() {
        String userId = auth.getCurrentUser().getUid();

        dbReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Fetch user information
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    usernameText.setText(user.getUserName());
                    emailText.setText("Email: " + user.getUserEmail());
                    passwordText.setText("Password: " + user.getUserPassword()); // Consider not showing the password
                    cityText.setText("City: " + user.getUserCity());
                } else {
                    Toast.makeText(ProfileActivity.this, "User data not available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(ProfileActivity.this, "Error loading profile: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Logout functionality
    public void onLogoutClick(View view) {
        Toast.makeText(ProfileActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
    }

    public void onClickHome(View view) {
        Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}
