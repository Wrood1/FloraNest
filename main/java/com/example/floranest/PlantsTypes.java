package com.example.floranest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class PlantsTypes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants_types);

        // Reference ImageButtons by their IDs in the layout
        ImageButton plantTypeAnnualFlower = findViewById(R.id.plant_type_annual_flowers);
        ImageButton plantTypeVegetable = findViewById(R.id.plant_type_vegetable_plants);
        ImageButton plantTypeHerb = findViewById(R.id.plant_type_herbs);
        ImageButton plantTypeFruit = findViewById(R.id.plant_type_fruit_trees);
        ImageButton plantTypeOther = findViewById(R.id.plant_type_other_plants);

        // Set click listeners for each ImageButton
        plantTypeAnnualFlower.setOnClickListener(view -> launchPlantsWithnTypeActivity("Annual Flowers"));
        plantTypeVegetable.setOnClickListener(view -> launchPlantsWithnTypeActivity("Vegetable Plants"));
        plantTypeHerb.setOnClickListener(view -> launchPlantsWithnTypeActivity("Herbs"));
        plantTypeFruit.setOnClickListener(view -> launchPlantsWithnTypeActivity("Fruits Trees"));
        plantTypeOther.setOnClickListener(view -> launchPlantsWithnTypeActivity("Other Plants"));

    }

    // Launch PlantsWithinType activity with the selected plant type
    private void launchPlantsWithnTypeActivity(String plantType) {
        Intent intent = new Intent(this, PlantsWithinType.class);
        intent.putExtra("plantType", plantType);
        startActivity(intent);
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
}