package com.example.healthcare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartLabActivity extends AppCompatActivity {
    ArrayList list;
    HashMap<String,String>item;
    SimpleAdapter sa;
    TextView tvTotal,textviewdate,textviewtime ;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
     private Button datebutton,timebutton,btnCheckout,btnBack;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_lab);





        timebutton = findViewById(R.id.buttonCartSelectTime);
        btnCheckout = findViewById(R.id.buttonCartGoToCart);

        btnBack= findViewById(R.id.buttonCartBack);
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        Database db =new Database(getApplicationContext(),"healthcare",null,1);
        float totalAmount =0;
        ArrayList dbData = db.getCartData(username,"lab");
        Toast.makeText(this, ""+dbData, Toast.LENGTH_SHORT).show() ;




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartLabActivity.this,LabTestActivity.class));
            }
        });

    datebutton = findViewById(R.id.buttonCartSelectDate);
    datebutton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog d = new DatePickerDialog(CartLabActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year1, int month1, int day1) {
                    textviewdate=findViewById(R.id.textViewCartSelectDate);
                    textviewdate.setText(day1 + "/" + (month1 + 1) + "/" + year1);
                }
            },
                    year, month, day);

            d.show();
        }

    });

        initTimePicker();
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });

    }



    private void initTimePicker()
    {
        TimePickerDialog.OnTimeSetListener timeSetListener= (view, i, i1) -> {
            textviewtime=findViewById(R.id.textViewCartSelectTime);
            textviewtime.setText(i+":"+i1);

        };
        Calendar cal =Calendar.getInstance();
        int hrs =cal.get(Calendar.HOUR);
        int mins=cal.get(Calendar.MINUTE);
        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }





}