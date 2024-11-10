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

public class HomeActivity extends AppCompatActivity {

    // Declare UI elements
    private TextView greetingText;

    // Firebase references
    private DatabaseReference dbReference;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize UI elements
        greetingText = findViewById(R.id.greeting_text);

        // Initialize Firebase authentication and database reference
        auth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReference("Users");

        // Get the current user's ID
        String userId = auth.getCurrentUser().getUid();

        // Fetch user's name from Firebase and display it
        dbReference.child(userId).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Retrieve user's name from the database
                String userName = dataSnapshot.getValue(String.class);

                // Check if userName is not null
                if (userName != null) {
                    // Set greeting message with the user's name
                    greetingText.setText("Hello " + userName + ",");
                } else {
                    // Display a default message if userName is null
                    greetingText.setText("Hello User,");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
                Toast.makeText(HomeActivity.this, "Error loading data: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Redirect to Profile screen
    public void onClickProfile(View view) {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    // Redirect to Plant Types screen
    public void onClickPlantTypes(View view) {
        Intent intent = new Intent(this,PlantsTypes.class);
        startActivity(intent);
    }
}
