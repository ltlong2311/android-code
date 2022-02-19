package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    Button button_back;
    ImageView avatar;
    TextView txtName, txtAddress, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        avatar = findViewById(R.id.avatar);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String address = intent.getStringExtra("address");
        int img = intent.getIntExtra("image",0);

        txtName.setText(name);
        txtPhone.setText(phone);
        txtAddress.setText(address);
        avatar.setImageResource(img);



        button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}