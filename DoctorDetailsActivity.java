package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.String;



public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1=
            {
                    {"Doctor Name: Priya","Hospital Address: Chennai","Exp: 5yrs", "Mob num :9842457891","600"},
                    {"Doctor Name: Neha","Hospital Address: Bangalore","Exp: 5yrs", "Mob num :7342457819","700"},
                    {"Doctor Name: Hums","Hospital Address: Hyderabad","Exp: 6yrs", "Mob num :894208895","800"},
                    {"Doctor Name: Suba","Hospital Address: Vellore","Exp: 7yrs", "Mob num :7842157291","500"},
                    {"Doctor Name: Soniya","Hospital Address: Chennai","Exp: 3yrs", "Mob num :6342457891","600"},
            };
    private String[][] doctor_details2=
            {
                    {"Doctor Name: keerthana","Hospital Address: Pune","Exp: 5yrs", "Mob num :6432457891","300"},
                    {"Doctor Name: Nandhini","Hospital Address: Mangalore","Exp: 5yrs", "Mob num :8342457819","900"},
                    {"Doctor Name: Reshma","Hospital Address: Telungana","Exp: 6yrs", "Mob num :824208895","400"},
                    {"Doctor Name: Gomathi","Hospital Address: Andhra","Exp: 7yrs", "Mob num :9242157291","200"},
                    {"Doctor Name: Kirubha","Hospital Address: Chennai","Exp: 3yrs", "Mob num :6342457891","700"},
            };
    private String[][] doctor_details3=
            {
                    {"Doctor Name: Rithika" ,"Hospital Address: Chennai","Exp: 5yrs", "Mob num :9842457891","600"},
                    {"Doctor Name: Rajan","Hospital Address: Bangalore","Exp: 5yrs", "Mob num :7342457819","700"},
                    {"Doctor Name: Nithya","Hospital Address: Hyderabad","Exp: 6yrs", "Mob num :894208895","800"},
                    {"Doctor Name: Jothi","Hospital Address: Vellore","Exp: 7yrs", "Mob num :7842157291","500"},
                    {"Doctor Name: Gopi","Hospital Address: Chennai","Exp: 3yrs", "Mob num :6342457891","600"},
            };
    private String[][] doctor_details4=
            {
                    {"Doctor Name: Jimin","Hospital Address: Chennai","Exp: 5yrs", "Mob num :9842457891","600"},
                    {"Doctor Name: Lalith","Hospital Address: Bangalore","Exp: 5yrs", "Mob num :7342457819","700"},
                    {"Doctor Name: Hoesok","Hospital Address: Hyderabad","Exp: 6yrs", "Mob num :894208895","800"},
                    {"Doctor Name: Divya","Hospital Address: Vellore","Exp: 7yrs", "Mob num :7842157291","500"},
                    {"Doctor Name: Haishvi","Hospital Address: Chennai","Exp: 3yrs", "Mob num :6342457891","600"},
            };
    private String[][] doctor_details5=
            {
                    {"Doctor Name: Taehyung", "Hospital Address: Pune", "Exp: 5yrs", "Mob num :9842457891", "600"},
                    {"Doctor Name: Jungkook", "Hospital Address: Mumbai", "Exp: 5yrs", "Mob num :7342457819", "700"},
                    {"Doctor Name: Yoongi", "Hospital Address: Gujarat", "Exp: 6yrs", "Mob num :894208895", "800"},
                    {"Doctor Name: Namjoon", "Hospital Address: Delhi", "Exp: 7yrs", "Mob num :7842157291", "500"},
                    {"Doctor Name: Seokjin", "Hospital Address: Punjab", "Exp: 3yrs", "Mob num :6342457891", "600"},


            };
    TextView  tv;
    Button btn;

    HashMap<String,String> item;
    String[][] doctor_details = {};

    ArrayList list;

    SimpleAdapter sa;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        btn = findViewById(R.id.buttonDDBack);
        ListView lst = findViewById(R.id.ListViewDD);
        tv = findViewById(R.id.textViewDDTitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        if (title.compareTo("Family Physicians") == 0)
            doctor_details = doctor_details1;
        else if
        (title.compareTo("Dentist") == 0)
            doctor_details = doctor_details3;
        else if (title.compareTo("Surgeon") == 0)
            doctor_details = doctor_details4;
        else if (title.compareTo("Cardiologists") == 0)
            doctor_details = doctor_details2;

        else
            doctor_details = doctor_details5;



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });


        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item = new HashMap<String, String>();

            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees:"+doctor_details[i][4]+"/>");
            list.add(item);


        }
        sa = new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );

        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });
    }
}