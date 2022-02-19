package com.example.listview;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<User> userArrayList;
    Database database;
    UserAdapter adapter;
    FloatingActionButton buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userArrayList = new ArrayList<>();
        listView = findViewById(R.id.listView);
        buttonAdd = findViewById(R.id.add_contact);
        ActionBar actionBar = getSupportActionBar();
        adapter = new UserAdapter(MainActivity.this, userArrayList);
        listView.setAdapter(adapter);

        database = new Database(this, "danhba.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS User(ID INTEGER PRIMARY KEY AUTOINCREMENT, TenUser VARCHAR(200), SDT VARCHAR(20))");
//        database.QueryData("INSERT INTO User VALUES(null, 'Nguyen Van B', '0964896655')");
//        database.QueryData("INSERT INTO User VALUES(null, 'Nguyen Van C', '0598370255')");
//        database.QueryData("INSERT INTO User VALUES(null, 'Nguyen Van D', '0964859490')");
//        database.QueryData("INSERT INTO User VALUES(null, 'Le Van E', '0898379503')");

        getUser();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                intent.putExtra("name", userArrayList.get(position).getName());
                intent.putExtra("address", userArrayList.get(position).getAddress());
                intent.putExtra("phone", userArrayList.get(position).getPhone());
                intent.putExtra("image", userArrayList.get(position).getImg());
                startActivity(intent);
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddUser.class);
//                startActivity(intent);
                DialogAdd();

            }
        });


    }

    private void getUser() {
        Cursor dataUser = database.GetData("SELECT * FROM User");
        while (dataUser.moveToNext()){
            int id = dataUser.getInt(0);
            String ten = dataUser.getString(1);
            String sdt = dataUser.getString(2);
//            Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
            userArrayList.add(new User(ten, "Hà Nội", sdt,R.drawable.user));
        }
        adapter.notifyDataSetChanged();
    }

    private void DialogAdd() {
          Dialog dialog = new Dialog(this);
          dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
          dialog.setContentView(R.layout.add_contact);
          dialog.show();

          EditText editName = dialog.findViewById(R.id.edtName);
          EditText editPhone = dialog.findViewById(R.id.edtPhone);
          Button btnAdd = dialog.findViewById(R.id.btnAddContact);
//          Button btnCannel = dialog.findViewById(R.id.btnCannel);
//          btnCannel.setOnClickListener(new View.OnClickListener() {
//              @Override
//              public void onClick(View v) {
//                  dialog.dismiss();
//              }
//          });

          btnAdd.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String ten = editName.getText().toString().trim();
                  String sdt = editPhone.getText().toString().trim();
                  if(ten.isEmpty() || sdt.isEmpty()){
                      Toast.makeText(MainActivity.this,"Vui long dien day du thong tin", Toast.LENGTH_SHORT).show();
                  } else {
                      database.QueryData("INSERT INTO User VALUES(null, '"+ ten +"', '"+ sdt +"')");
                      Toast.makeText(MainActivity.this,"Thêm thành công!", Toast.LENGTH_SHORT).show();
                      dialog.dismiss();
                      getUser();
                  }
              }
          });

    }

    public void hideKeyBoard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



}