package com.closetkeeper.dressy;

import static com.closetkeeper.dressy.my_items.itemNames;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.closetkeeper.dressy.dto.Item;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private int mResource;
    List<Item> Items;

    public void setItems(List<Item> Items){
        this.Items = Items;
    }



    public ItemAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.Items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       LayoutInflater layoutInflater = LayoutInflater.from(mContext);

       convertView = layoutInflater.inflate(mResource, parent, false);

       ImageView imageView = convertView.findViewById(R.id.image);

       TextView txtName = convertView.findViewById(R.id.txtName);

       imageView.setImageBitmap(getItem(position).getImage());

       txtName.setText(itemNames.get(position));


        return convertView;
    }
}
