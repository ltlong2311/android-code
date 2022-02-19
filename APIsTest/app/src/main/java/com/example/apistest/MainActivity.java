package com.example.apistest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
@SuppressLint("ViewHolder")

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] items = {"SingleTouch", "Key", "Test3", "Asset", "FlashingColor", "Draw", "RenderText", "Test8"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                Intent intent = new Intent(MainActivity.this, SingleTouchActivity.class);
                intent.putExtra("item", items[position]);
                startActivity(intent);
            }
            if (position == 1) {
                Intent intent = new Intent(MainActivity.this, KeyEventActivity.class);
                startActivity(intent);
            }
            if (position == 2) {
                Intent intent = new Intent(MainActivity.this, Test3.class);
                startActivity(intent);
            }
            if (position == 3) {
                Intent intent = new Intent(MainActivity.this, Test4.class);
                intent.putExtra("item", items[position]);
                startActivity(intent);
            }
            if (position == 4) {
                Intent intent = new Intent(MainActivity.this, Test5.class);
                intent.putExtra("item", items[position]);
                startActivity(intent);
            }
            if (position == 5) {
                Intent intent = new Intent(MainActivity.this, Test6.class);
                intent.putExtra("item", items[position]);
                startActivity(intent);
            }
            if (position == 6) {
                Intent intent = new Intent(MainActivity.this, Test7.class);
                startActivity(intent);
            }
            if (position == 7) {
                Intent intent = new Intent(MainActivity.this, Test8.class);
                startActivity(intent);
            }
        });

    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rItem[];

        MyAdapter(Context c, String item[]) {
            super(c, R.layout.item, R.id.textViewName, item);
            this.context = c;
            this.rItem = item;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item = layoutInflater.inflate(R.layout.item, parent, false);
            TextView itemName = item.findViewById(R.id.textViewName);
            itemName.setText(rItem[position]);
            return item;
        }
    }

}