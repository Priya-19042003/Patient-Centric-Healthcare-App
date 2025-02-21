package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

     TextView tv;
     EditText fullname,address,contact,fees;
     TextView date,time;
     private TimePickerDialog timePickerDialog;
     Button   appointment,selectdate,back,selecttime;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        tv = findViewById(R.id.textViewAppTitle);
        fullname = findViewById(R.id.editTextAppFullName);
        address = findViewById(R.id.editTextAppAddress);
        contact = findViewById(R.id.editTextAppContactnum);
        fees = findViewById(R.id.editTextAppConsultantFees);
        date = findViewById(R.id.textViewDate);
        time=findViewById(R.id.textViewAppTime);
        appointment = findViewById(R.id.buttonBookAppointment);
        selectdate = findViewById(R.id.buttonAppDate);
        selecttime=findViewById(R.id.buttonApptime);
        back = findViewById(R.id.buttonBABack);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class));

            }
        });

        initTimePicker();
        selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });
        fullname.setKeyListener(null);
        address.setKeyListener(null);
        contact.setKeyListener(null);
        fees.setKeyListener(null);
        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String ed1 = it.getStringExtra("text2");
        String ed2 = it.getStringExtra("text3");
        String ed3 = it.getStringExtra("text4");
        String ed4 = it.getStringExtra("text5");


        tv.setText(title);
        fullname.setText(ed1);
        address.setText(ed2);
        contact.setText(ed3);
        fees.setText(ed4);



        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(BookAppointmentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year1, int month1, int day1) {

                        date.setText(day1 + "/" + (month1 + 1) + "/" + year1);
                    }
                },
                        year, month, day);

                d.show();
            }

        });

    }

         private void initTimePicker()
    {
             TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                 @Override
                 public void onTimeSet(TimePicker view, int i, int i1) {
                     time.setText(i+":"+i1);

                 }
             };
             Calendar cal =Calendar.getInstance();
             int hrs =cal.get(Calendar.HOUR);
             int mins=cal.get(Calendar.MINUTE);
             int style= AlertDialog.THEME_HOLO_DARK;
             timePickerDialog = new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
        }



}

