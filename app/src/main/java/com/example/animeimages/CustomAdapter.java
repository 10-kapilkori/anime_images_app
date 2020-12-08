package com.example.animeimages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private static final String TAG = "CustomAdapter";

    final LayoutInflater inflater;
    final int resource;
    List<Photo> imageList;

    public CustomAdapter(@NonNull Context context, int resource, List<Photo> imageList) {
        super(context, resource);
        this.inflater = LayoutInflater.from(context);
        this.resource = resource;
        this.imageList = imageList;
    }

    @Override
    public int getCount() {
//        Log.i(TAG, "getCount: " + imageList.size());
        return imageList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        ImageView imageView = view.findViewById(R.id.imageView);

        Photo image = imageList.get(position);

        Picasso.with(getContext())
                .load(image.getPhoto())
                .error(R.drawable.placeholder)
                .into(imageView);

//        Log.i(TAG, "getView: " + imageList.get(position));

        return view;
    }
}