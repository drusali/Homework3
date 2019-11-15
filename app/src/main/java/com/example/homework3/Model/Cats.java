package com.example.homework3.Model;

import android.provider.MediaStore;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

public class Cats {
    private String id;
    private String name;
    private String wikipedia_url;
    private String temperament;
    private String origin;
    private String description;
    private String life_span;
    private String dog_friendly;
    private Weight weight;

    public Weight getWeight() {
        return weight;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWikipedia_url() {
        return wikipedia_url;
    }


    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDescription() {
        return description;
    }

    public String getLife_span() {
        return life_span;
    }

    public String getDog_friendly() {
        return dog_friendly;
    }

    public class Weight {
        String metric;

        public String getMetric() {
            return metric;
        }

    }
}