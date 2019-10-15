package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {


    private static final String TAG = "ListDataActivity";
    DatabaseHelper mDatabaseHelper;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        lv = findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
        
        populateListView();
    }

    private void populateListView() {

        Log.d(TAG, "populateListView: Displaying data in the ListView" );
        Cursor data = mDatabaseHelper.showData();
        ArrayList<String> listData = new ArrayList();

        while(data.moveToNext()){
            listData.add(data.getString(0)); // get string at column 0 (ami a nev)
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        lv.setAdapter(adapter);
    }
}
