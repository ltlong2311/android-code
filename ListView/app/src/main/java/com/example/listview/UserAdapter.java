package com.example.listview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    Context context;
    ArrayList<User> arrayList;

    public UserAdapter(Context context, ArrayList<User> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    private class ViewHolder{
       Button btn_detail;
       TextView txtName;
       ImageView image;
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
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row, null);

            holder.txtName = (TextView) convertView.findViewById(R.id.textViewName);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.btn_detail = convertView.findViewById(R.id.button_detail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = arrayList.get(position);
        holder.txtName.setText(user.getName());

        holder.image.setImageResource(user.getImg());

       holder.btn_detail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               context.startActivity(new Intent(context, DetailActivity.class));
               Intent intent = new Intent(context, DetailActivity.class);

               User user = arrayList.get(position);
               intent.putExtra("name", user.getName());
               intent.putExtra("address", user.getAddress());
               intent.putExtra("phone", user.getPhone());
               intent.putExtra("image", user.getImg());
               context.startActivity(intent);
           }
       });





        return convertView;
    }
}
