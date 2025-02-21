package com.example.healthcare;

import static java.lang.Float.parseFloat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnAddToCart;

    Button btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName = findViewById(R.id.textViewLTDPackageName);
        tvTotalCost = findViewById(R.id.textViewLtdTotalCost);
        edDetails =findViewById(R.id.editTextLTDTextMultiLine);
        edDetails.setKeyListener(null);
        Intent intent=getIntent();
        tvPackageName.setText(intent.getStringExtra("test1"));
        edDetails.setText(intent.getStringExtra("test2"));
        tvTotalCost.setText("TotalCost:"+intent.getStringExtra("test3"));


        btnAddToCart =findViewById(R.id.buttonLTDAddToCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);

                String username = sharedPreferences.getString("username", "").trim();
                String product = tvPackageName.getText().toString();
                 float price = parseFloat(intent.getStringExtra("text3").trim());
                if (db.checkcart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addcart(username, product, price,"lab");
                    Toast.makeText(getApplicationContext(), "Product Added to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }
            }
                                        });



    btnBack =findViewById(R.id.buttonLTDBack);
        btnBack.setOnClickListener(v -> {
            Intent in= new Intent(LabTestDetailsActivity.this, LabTestActivity.class);
            startActivity(in);

        });

    }


}
