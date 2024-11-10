package com.example.floranest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// adapter class is responsible for displaying a list of plants in a RecyclerView.
// It takes a list of `Plant` objects and inflates a custom layout (plant_item_layout)

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {

    //A list of Plant objects to be displayed.
    private final List<Plant> plants;
    //The context of the activity where this adapter is used.
    private final Context context;

    public PlantAdapter(Context context, List<Plant> plants) {
        this.context = context;
        this.plants = plants;
    }

    //Creates a new ViewHolder instance by inflating the plant_item_layout.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_item_layout, parent, false);
        return new ViewHolder(view);
    }

    //Binds the data from a Plant object to the corresponding ViewHolder.
    //     * Sets the image resource and text for the plant name.
    //     * Also sets an onClickListener for the entire item view that starts a new activity (PlantDetail)
    //     * and passes the plant details as extras.
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plant plant = plants.get(position);
        holder.plantImage.setImageResource(plant.getImageResId());
        holder.plantName.setText(plant.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlantDetail.class);
            intent.putExtra("PLANT_NAME", plant.getName());
            intent.putExtra("PLANT_IMAGE", plant.getImageResId());
            intent.putExtra("PLANT_DESCRIPTION", plant.getDescription());
            intent.putExtra("PLANTING_TIPS", plant.getPlantingTips());
            intent.putExtra("CARE_TIPS", plant.getCareTips());
            context.startActivity(intent);
        });
    }

    //Returns the total number of items in the list.
    @Override
    public int getItemCount() {
        return plants.size();
    }

    // inner class represents a ViewHolder object which holds the references to the views in the plant_item_layout.
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView plantImage;
        TextView plantName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plantImage = itemView.findViewById(R.id.plant_image);
            plantName = itemView.findViewById(R.id.plant_name);
        }

    }

}