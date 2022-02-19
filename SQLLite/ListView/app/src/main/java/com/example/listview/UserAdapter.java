package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    Context context;
    ArrayList<User> arrayList;

    public UserAdapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row, null);

        TextView txtName = convertView.findViewById(R.id.textViewName);
        ImageView image = convertView.findViewById(R.id.image);

        User user = arrayList.get(position);
        txtName.setText(user.getName());

        image.setImageResource(user.getImg());

        return convertView;
    }
}
