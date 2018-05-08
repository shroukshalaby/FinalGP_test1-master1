package com.example.lenovo.finalgp_test1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.finalgp_test1.TripPlannerClasses.ToursClass;
import com.squareup.timessquare.CalendarPickerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GenerateItineraryActivity extends AppCompatActivity {

    public int city_id = 2;
    private String url;

    Button generateBtn;
    RadioButton adventure, dinning, sports, entertainment, historical, exploring,
    religious, artistic;
    Spinner filterSpinner, flightSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_generate_itinerary );

        url = "http://suspense.atwebpages.com/api/tours_price_sort.php?city_id=2";
        generateBtn = findViewById(R.id.generateIT_Btn);

        adventure = findViewById(R.id.AdventureTV);
        dinning = findViewById(R.id.DinningTV);
        sports = findViewById(R.id.SportsTV);
        entertainment = findViewById(R.id.EntertainmentTV);
        historical = findViewById(R.id.HistoricalTV);
        exploring = findViewById(R.id.ExploringTV);
        religious = findViewById(R.id.ReligiousTV);
        artistic = findViewById(R.id.ArtisticTV);

        filterSpinner = findViewById(R.id.filteredBy_spinner);
        flightSpinner = findViewById(R.id.flightCategory_spinner);

        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);

        CalendarPickerView datepicker = findViewById(R.id.calendar_GIt);
        datepicker.init(today,nextYear.getTime())
                .inMode(CalendarPickerView.SelectionMode.RANGE);

        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(GenerateItineraryActivity.this, ItenaryActivity.class);
                if(adventure.isChecked())
                    i.putExtra("adv", "yes");
                else
                    i.putExtra("adv", "no");
                if(dinning.isChecked())
                    i.putExtra("din", "yes");
                else
                    i.putExtra("din", "no");
                if(sports.isChecked())
                    i.putExtra("sport", "yes");
                else
                    i.putExtra("sport", "no");
                if(entertainment.isChecked())
                    i.putExtra("ent", "yes");
                else
                    i.putExtra("ent", "no");
                if(historical.isChecked())
                    i.putExtra("hist", "yes");
                else
                    i.putExtra("hist", "no");
                if(exploring.isChecked())
                    i.putExtra("exp", "yes");
                else
                    i.putExtra("exp", "no");
                if(religious.isChecked())
                    i.putExtra("rel", "yes");
                else
                    i.putExtra("rel", "no");
                if(artistic.isChecked())
                    i.putExtra("art", "yes");
                else
                    i.putExtra("art", "no");

                if(filterSpinner.getSelectedItem().equals("Price"))
                    i.putExtra("filter", "price");
                else
                    i.putExtra("filter", "rating");
                if(flightSpinner.getSelectedItem().equals("Economy"))
                    i.putExtra("flight", "economy");
                else
                    i.putExtra("flight", "business");

                startActivity(i);
            }
        });
    }
}
