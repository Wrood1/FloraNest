package com.example.floranest;

//plant's name, image resource ID, description, planting tips and care tips.

public class Plant {
    private String name;
    private int imageResId;
    private String description;
    private String plantingTips;
    private String careTips;

    public Plant(String name, int imageResId, String description, String plantingTips, String careTips) {
        this.name = name;
        this.imageResId = imageResId;
        this.description = description;
        this.plantingTips = plantingTips;
        this.careTips = careTips;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlantingTips() {
        return plantingTips;
    }

    public void setPlantingTips(String plantingTips) {
        this.plantingTips = plantingTips;
    }

    public String getCareTips() {
        return careTips;
    }

    public void setCareTips(String careTips) {
        this.careTips = careTips;
    }

}
