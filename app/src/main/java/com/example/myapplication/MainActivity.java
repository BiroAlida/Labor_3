package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String username, password, email, birthdate;
    private Button btn_login;
    private CheckBox cb;
    private EditText etUserName, etPassword, etEmail;
    private TextView tv_birthDate;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Boolean saveLogin;
    String toastText = "Login Successful";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pick_date = findViewById(R.id.button_pickDate);
        tv_birthDate = findViewById(R.id.textView_birthDate);


        pick_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tv_birthDate.setText(year + "." + (month + 1) + "." + day);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();

            }

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        });

        // Shared preferences a username, email, password es birthdate megorzesere kilepes utan


        btn_login = (Button) findViewById(R.id.button_logIn);
        btn_login.setOnClickListener(this);
        etUserName = findViewById(R.id.editText);
        etPassword = findViewById(R.id.editText2);
        etEmail = findViewById(R.id.editText3);


        cb = (CheckBox) findViewById(R.id.checkBox);
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        saveLogin = sharedPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            etUserName.setText(sharedPreferences.getString("username", ""));
            etEmail.setText(sharedPreferences.getString("email", ""));
            etPassword.setText(sharedPreferences.getString("password", ""));
            tv_birthDate.setText(sharedPreferences.getString("birthdate", ""));
            cb.setChecked(true);
        }

    }

    @Override
    public void onClick(View v) {

        if (v == btn_login) {

            Toast.makeText(getApplicationContext(),toastText,Toast.LENGTH_LONG).show();
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(etUserName.getWindowToken(), 0);

            username = etUserName.getText().toString();
            password = etPassword.getText().toString();
            email = etEmail.getText().toString();
            birthdate = tv_birthDate.getText().toString();

            if (cb.isChecked()) {
                editor.putBoolean("saveLogin", true);
                editor.putString("username", username);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.putString("birthdate", birthdate);

                editor.commit();
            } else {
                editor.clear();
                editor.commit();
            }

            doSomethingElse();
        }

    }

    public void doSomethingElse() {
        startActivity(new Intent(MainActivity.this, HobbyActivity.class));
        //HobbyActivity.this.finish();
        finish();
    }
}

