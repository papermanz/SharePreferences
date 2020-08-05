package com.minhhieu.sharepreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnSave;
    private Button btnRead;
    private Button btnRemoveById;
    private Button btnRemoveAll;
    private final String NAME = "my_name";
    private final String AGE = "my_age";
    private final String IS_SINGLE = "is_single";
    private final String WEIGHT = "my_weight";
    private final String SHARE_DATA = "Dev Quèn";
    private final String TAG = getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnRead = (Button) findViewById(R.id.btn_read);
        btnRemoveById = (Button) findViewById(R.id.btn_remove_by_key);
        btnRemoveAll = (Button) findViewById(R.id.btn_removeall);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        btnRemoveById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeByKey(NAME);
                getData();
            }
        });
        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAll();

            }
        });
    }
    public void addData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_DATA, Context.MODE_PRIVATE); // Mode_Private khai báo cho thấy chỉ ứng dụng này truy xuất đến dữ liệu sharepreferences
        SharedPreferences.Editor editor = sharedPreferences.edit(); // dùng sharepre mở file và dùng editor put dữ liệu vào
        editor.putString(NAME, "Minh Hiếu");
        editor.putInt(AGE, 22);
        editor.putBoolean(IS_SINGLE, true);
        editor.putLong(WEIGHT, 60);
        //file lưu data/data/projectname/pre
        editor.apply(); // Lưu lại trạng thái thêm dữ liệu
        //editor.commit(); apply là cùng lúc thêm, sửa, comit tuần tự hoạt động từ trên xuống dưới
        Toast.makeText(MainActivity.this,"Save Successfully",Toast.LENGTH_SHORT).show();
    }
    public void getData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_DATA,Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(NAME, "NONAME");
        int age = sharedPreferences.getInt(AGE, 0);
        boolean isSingel = sharedPreferences.getBoolean(IS_SINGLE, false);
        long weight = sharedPreferences.getLong(WEIGHT, 0);

        Log.d(TAG, "\n"+"DEV QUÈN: " +"\n" + "Name: "+ name + "\n" + "Age: "+ age + "\n" + "Single: " + isSingel + "\n" + "Weight: " + weight);
    }
    public void removeByKey(String key){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();

    }
    public void removeAll(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARE_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
}