package com.example.magnumrahul;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<User> {

    ArrayList<User> user;
    Context context;
    int resource;

    public CustomListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> objects) {
        super(context, resource, objects);
        this.user = user;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.custom_list_layout,null,true);
        }
        User user = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewUser);
        Picasso.get().load(user.getImage()).into(imageView);

        TextView textView = (TextView) convertView.findViewById(R.id.userName);
        textView.setText(user.getUserName());

        return convertView;
    }
}
