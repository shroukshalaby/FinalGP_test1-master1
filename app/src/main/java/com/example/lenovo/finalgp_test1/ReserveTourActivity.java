package com.example.lenovo.finalgp_test1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ReserveTourActivity extends AppCompatActivity {

    private int total_price, num_persons;
    private String tour_name;
    TextView tour_txt, total_txt;
    ImageView tour_img;
    Button bookTour ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_tour);

        tour_name = getIntent().getExtras().getString("tour_name");

        num_persons = ToursActivity.num_persons;
        Log.i("persons : ", ""+num_persons);
        tour_img = (ImageView)findViewById(R.id.tour_img1);
        Picasso.with(getApplication()).load(getIntent().getExtras().getString("tour_img")).into(this.tour_img);

        total_price = getIntent().getIntExtra("tour_price", 1);
        total_price *= num_persons;

        tour_txt = (TextView)findViewById(R.id.tour_reserve_name);
        tour_txt.setText(tour_name);

        total_txt = (TextView)findViewById(R.id.tour_reserve_price);
        total_txt.setText("$ "+ String.valueOf(total_price));
        //Toast.makeText(this, "total: " + total_price, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "persons: " + num_persons, Toast.LENGTH_SHORT).show();

        bookTour = findViewById(R.id.ReserveTourBtn);
        bookTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ReserveTourActivity.this, "Done", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReserveTourActivity.this , HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
