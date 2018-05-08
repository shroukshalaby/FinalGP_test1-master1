package com.example.lenovo.finalgp_test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ReserveCarActivity extends AppCompatActivity {

    private int total_price, num_days;
    private String car_model;
    TextView car_txt, total_txt;
    ImageView car_img;
    Button bookCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_reserve_car );

        car_model = getIntent().getExtras().getString("car_model");
        num_days = CarActivity.num_days;
        if(num_days==0)
            num_days = 1;

        //Toast.makeText(this, ""+ getIntent().getExtras().getString("car_img"), Toast.LENGTH_SHORT).show();
        car_img = (ImageView)findViewById(R.id.car_img1);
        Picasso.with(getApplication()).load(getIntent().getExtras().getString("car_img")).into(this.car_img);

        total_price = getIntent().getIntExtra("car_price", 1);
        total_price *= num_days;

        car_txt = (TextView)findViewById(R.id.car_type);
        car_txt.setText(car_model);

        total_txt = (TextView)findViewById(R.id.car_total);
        total_txt.setText("$ " + String.valueOf(total_price));

        bookCar = (Button) findViewById(R.id.ReserveCarBtn);
        bookCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReserveCarActivity.this, "Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReserveCarActivity.this , HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
