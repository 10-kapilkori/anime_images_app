package com.example.animeimages;

public class Photo {
    private static final String TAG = "Photo";

    String Photo;

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    @Override
    public String toString() {
        return  "\n" + "Photo= " + Photo;
    }
}
