package com.example.floranest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PlantsWithinType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plants_within_type);


        // Get plant type passed from PlantsTypes activity
        Intent intent = getIntent();
        String plantType = intent.getStringExtra("plantType");

        // Update title based on received plant type
        TextView titleTextView = findViewById(R.id.title);
        titleTextView.setText(plantType);

        // Update description with a greeting for the plant type
        TextView descriptionText = findViewById(R.id.description_text);
        descriptionText.setText("Learn more about " + plantType + "!");

        // Reference RecyclerView for displaying plant information
        RecyclerView plantList = findViewById(R.id.plant_list);

        // Prepare a list of Plant objects based on the plant type
        List<Plant> plants = new ArrayList<>();
        if (plantType.equals("Annual Flowers")) {
            plants.add(new Plant("Marigold", R.drawable.marigold, "Marigolds are vibrant annual flowers known for their cheerful colors and distinctive scent. They come in a variety of sizes, shapes, and colors, including yellow, orange, and red.", "- Plant marigold seeds directly in the garden after the last frost.\n" + "- Choose a sunny location with well-drained soil.\n" + "- Space plants 6-12 inches apart.\n" + "- Water regularly, especially during dry periods.", "- Deadhead spent blooms to encourage continuous flowering.\n" + "- Fertilize lightly with a balanced fertilizer once a month.\n" + "- Monitor for pests like aphids and whiteflies."));
            plants.add(new Plant("Zinnias", R.drawable.zinnias, "Zinnias are another popular annual flower with large, colorful blooms. They come in a wide range of colors and sizes, from small, daisy-like flowers to large, cactus-like blooms.", "- Plant zinnia seeds directly in the garden after the last frost.\n" +"- Choose a sunny location with well-drained soil.\n" + "- Space plants 6-12 inches apart.\n" + "- Water regularly, especially during dry periods.", "- Deadhead spent blooms to encourage continuous flowering.\n" + "- Fertilize lightly with a balanced fertilizer once a month.\n" + "- Monitor for pests like aphids and powdery mildew."));
            plants.add(new Plant("Petunia ", R.drawable.petunia, "Petunias are versatile flowering plants that come in a wide range of colors and flower shapes. They are popular for their long blooming period and ability to thrive in various conditions.", "- Plant petunia seeds or seedlings in well-drained soil.\n" + "- Choose a location with full sun or partial shade.\n" + "- Space plants 6-12 inches apart.\n" + "- Water regularly, especially during dry periods.", "- Deadhead spent blooms to encourage continuous flowering.\n" + "- Fertilize regularly with a balanced fertilizer.\n" + "- Monitor for pests like whiteflies and aphids."));
            plants.add(new Plant("Sunflower ", R.drawable.sunflower, "Sunflowers are tall, majestic plants known for their large, vibrant blooms. They are popular for their beauty and ability to attract pollinators.", "- Plant sunflower seeds directly in the garden after the last frost.\n" + "- Choose a sunny location with well-drained soil.\n" + "- Space plants 12-24 inches apart.\n" + "- Water regularly, especially during dry periods.", "- Stake tall sunflower varieties to prevent them from falling over.\n" + "- Deadhead spent blooms to encourage continuous flowering.\n" + "- Monitor for pests like aphids and birds."));
            plants.add(new Plant("Nasturtium", R.drawable.nasturtium, "Nasturtiums are colorful, vining plants with edible flowers and leaves. They are easy to grow and add a unique touch to gardens and containers.", "- Plant nasturtium seeds directly in the garden after the last frost.\n" + "- Choose a location with full sun or partial shade.\n" + "- Space plants 6-12 inches apart.\n" + "- Water regularly, especially during dry periods.", "- Deadhead spent blooms to encourage continuous flowering.\n" + "- Nasturtiums are self-seeding, so you may need to thin seedlings.\n" + "- Monitor for pests like aphids and slugs."));
            plants.add(new Plant("Pansy", R.drawable.pansy, "Pansies are cheerful, colorful flowers that bloom in early spring. They are popular for their ability to tolerate cold weather and their wide range of colors.", "- Plant pansy seedlings in well-drained soil.\n" + "- Choose a location with full sun or partial shade.\n" + "- Space plants 6-12 inches apart.\n" + "- Water regularly, especially during dry periods.", "- Deadhead spent blooms to encourage continuous flowering.\n" + "- Fertilize lightly with a balanced fertilizer once a month.\n" + "- Monitor for pests like aphids and slugs."));
        }
        else if (plantType.equals("Vegetable Plants")) {
            plants.add(new Plant("Lettuce", R.drawable.lettuce, "Lettuce is a cool-season leafy green vegetable that comes in a variety of types, including romaine, iceberg, and butterhead.", "- Plant lettuce seeds directly in the garden or in containers.\n" + "- Choose a location with partial shade.\n" + "- Space plants 6-12 inches apart.\n" + "- Water regularly, especially during dry periods", "- Harvest lettuce leaves as needed.\n" + "- Protect lettuce from pests like slugs and rabbits.\n" + "- Consider using row covers to protect lettuce from extreme weather conditions."));
        }
        else if (plantType.equals("Herbs")) {
            plants.add(new Plant("Basil", R.drawable.basil, "Basil is an aromatic herb commonly used in Italian cuisine. It comes in a variety of types, including sweet basil, lemon basil, and Thai basil.", "- Plant basil seeds directly in the garden or in containers.\n" + "- Choose a sunny location with well-drained soil.\n" + "- Space plants 6-12 inches apart.\n" + "- Water regularly, especially during dry periods.", "- Harvest basil leaves as needed.\n" + "- Pinch back basil plants to encourage bushier growth.\n" + "- Monitor for pests like aphids and whiteflies."));
        }
        else if (plantType.equals("Fruits Trees")) {
            plants.add(new Plant("Pear Trees", R.drawable.pear_trees, "Pear trees are deciduous fruit trees that produce delicious pears. They come in a variety of types, including Bartlett, Anjou, and Bosc.", "- Plant pear trees in well-drained soil.\n" + "- Choose a location with full sun.\n" + "- Space trees 15-20 feet apart.\n" + "- Water regularly, especially during dry periods.", "- Prune pear trees in late winter or early spring.\n" + "- Fertilize pear trees in early spring.\n" + "- Monitor for pests like pear psylla and codling moth."));
        }
        else if (plantType.equals("Other Plants")) {
            plants.add(new Plant("Tillandsia", R.drawable.tillandsia, "Tillandsias are epiphytic plants that do not require soil to grow. They absorb moisture and nutrients from the air.", "- Attach tillandsias to driftwood, rocks, or other surfaces using wire or fishing line.\n" + "- Place tillandsias in bright, indirect light.", "- Soak tillandsias in water for 10-20 minutes once a week.\n" + "- Allow tillandsias to dry completely between waterings.\n" + "- Mist tillandsias with water every few days."));
            plants.add(new Plant("Echeveria", R.drawable.echeveria, "Echeverias are succulent plants with thick, fleshy leaves. They come in a variety of shapes, sizes, and colors.", "- Plant echeverias in well-drained soil or a cactus mix.\n" + "- Choose a location with bright, indirect light.\n" + "- Space plants 6-12 inches apart.", "- Water echeverias sparingly, allowing the soil to dry out completely between waterings.\n" + "- Fertilize echeverias once a month during the growing season.\n" + "- Protect echeverias from frost."));
        }

        // Create and set adapter for RecyclerView to display plant data
        PlantAdapter adapter = new PlantAdapter(this, plants);
        plantList.setAdapter(adapter);
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