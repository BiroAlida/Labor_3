package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HobbyActivity extends AppCompatActivity {

    DatabaseHelper hobbyDB;

    Button addHobby, viewHobby;
    EditText et_hobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        hobbyDB = new DatabaseHelper(this);

        addHobby = findViewById(R.id.btn_addHobby);
        viewHobby = findViewById(R.id.btn_viewHobbies);
        et_hobby = findViewById(R.id.editText4);

        AddData();

        viewHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HobbyActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void AddData()
    {
        addHobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hobby_name = et_hobby.getText().toString();

                boolean insertData = hobbyDB.addData(hobby_name);

                if(insertData == true)
                {
                    Toast.makeText(HobbyActivity.this, "Data successfully added", Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(HobbyActivity.this, "Not succeeded", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
