package com.example.listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button button_detail;
    ArrayList<User> userArrayList;
//    String Name[] = {"Lê Thành Long", "Nguyễn Văn A", "Nguyễn Minh B", "Hồ Văn C"};
//    String Address[] = {"Hà Nội", "Hà Nội", "Bắc Ninh", "Thái Nguyên"};
//    String Phone[] = {"0393260255", "1493202394", "944283424", "423391313"};
//    int images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3,R.drawable.image4};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userArrayList = new ArrayList<>();
        userArrayList.add(new User("Lê Thành Long","Hà Nội","0393260255",R.drawable.image2));
        userArrayList.add(new User("Lê Văn A","Huế","0315646442",R.drawable.image_1));
        userArrayList.add(new User("Trần Đức B","Quảng Nam","0423423424",R.drawable.image_2));
        userArrayList.add(new User("Nguyễn Trung C","Đà Nẵng","01231443",R.drawable.image_3));

        listView = findViewById(R.id.listView);

//        Adapter adapter = new Adapter(this, Name, Address, Phone, images);
//        listView.setAdapter(useAdapter);

        UserAdapter customAdapter = new UserAdapter(MainActivity.this, userArrayList);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                intent.putParcelableArrayListExtra("name", Name);
//                intent.putParcelableArrayListExtra("address", Address);
//                intent.putParcelableArrayListExtra("phone", Phone);
//                intent.putParcelableArrayListExtra("", images);
//                if (position == 0) {
//                    Toast.makeText(MainActivity.this, "test",Toast.LENGTH_SHORT).show();
//                }
                intent.putExtra("name", userArrayList.get(position).getName());
                intent.putExtra("address", userArrayList.get(position).getAddress());
                intent.putExtra("phone", userArrayList.get(position).getPhone());
                intent.putExtra("image", userArrayList.get(position).getImg());
                startActivity(intent);
            }
        });

//        button_detail = findViewById(R.id.button_detail);
//        button_detail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });


    }

//    class Adapter extends ArrayAdapter<String> {
//        Context context;
//        String rName[];
//        String rAddress[];
//        String rPhone[];
//        int rImages[];
//
//        Adapter (Context c, String name[], String address[], String phone[], int imgs[]){
//            super(c, R.layout.row, R.id.textViewName, name);
//            this.context = c;
//            this.rName = name;
//            this.rAddress = address;
//            this.rPhone = phone;
//            this.rImages = imgs;
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row = layoutInflater.inflate(R.layout.row, parent,false);
//            ImageView images = row.findViewById(R.id.image);
//            TextView name = row.findViewById(R.id.textViewName);
//
//            images.setImageResource(rImages[position]);
//            name.setText(rName[position]);
//
//            return row;
//        }
//    }

}