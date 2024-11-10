package com.example.floranest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

// displays the details of a specific plant.
// * It retrieves plant information passed from the PlantAdapter through an Intent.
// * The details are then displayed on the UI elements.
public class PlantDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        // Retrieve plant details from the Intent
        String plantName = getIntent().getStringExtra("PLANT_NAME");
        int plantImageResId = getIntent().getIntExtra("PLANT_IMAGE", 0); // Default value if not found
        String plantDescription = getIntent().getStringExtra("PLANT_DESCRIPTION");
        String plantingTips = getIntent().getStringExtra("PLANTING_TIPS");
        String careTips = getIntent().getStringExtra("CARE_TIPS");

        // Update UI elements with retrieved plant details
        TextView plantNameTextView = findViewById(R.id.plant_name);
        ImageView plantImageView = findViewById(R.id.plant_image);
        TextView plantDescriptionTextView = findViewById(R.id.plant_description); // (Optional)
        TextView plantingTipsTextView = findViewById(R.id.planting_tips); // (Optional)
        TextView careTipsTextView = findViewById(R.id.care_tips); // (Optional)

        plantNameTextView.setText(plantName);
        plantImageView.setImageResource(plantImageResId);
        plantDescriptionTextView.setText(plantDescription);
        plantingTipsTextView.setText(plantingTips);
        careTipsTextView.setText(careTips);
    }


    public void onClickHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void onClickProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void onClickPlantTypes(View view) {
        startActivity(new Intent(this, PlantsTypes.class));
    }

    public void onClickPlantsWithinType(View view) {
        startActivity(new Intent(this, PlantsWithinType.class));
    }

}