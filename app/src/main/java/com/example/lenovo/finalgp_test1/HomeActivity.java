package com.example.lenovo.finalgp_test1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_home );

        ImageButton hotelimg  = (ImageButton)findViewById( R.id.HotelimageButton );
        ImageButton flightimg = (ImageButton)findViewById( R.id.FlightimageButton );
        ImageButton carsimg   = (ImageButton)findViewById( R.id.CarimageButton );
        ImageButton toursimg  = (ImageButton)findViewById( R.id.ToursimageButton );
        Button generateIT     = (Button) findViewById( R.id.GenerateItineraryBtn );

        hotelimg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( HomeActivity.this , HotelActivity.class );
                startActivity( intent );
            }


        } );

        flightimg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomeActivity.this , FlightActivity.class);
                startActivity( intent );
            }
        } );

        carsimg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomeActivity.this , CarActivity.class);
                startActivity( intent );
            }
        } );
        toursimg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomeActivity.this , ToursActivity.class);
                startActivity( intent );
            }
        } );
        generateIT.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( HomeActivity.this ,GenerateItineraryActivity.class);
                startActivity( intent );
            }
        } );
    }
}
