package com.example.lenovo.finalgp_test1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;

public class HotelActivity extends AppCompatActivity {


    private SeekBar seekBar;
    private TextView seekVal;
    int progress;
    String datefrom, dateto;
    int counter = 0;
    public static int num_days;
    int fday, lday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hotel );

        final Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR,1);
        final CalendarPickerView datepicker = (CalendarPickerView) findViewById(R.id.calendar_hotel);
        datepicker.init(today,nextYear.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);
        EditText num_personsET = (EditText)findViewById(R.id.numOfPerson_ET_hotel);
        final EditText destination = (EditText)findViewById(R.id.DestinationHotelET);

        Button searchHotel = (Button)findViewById( R.id.SearchHotelBtn);
        searchHotel.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent( HotelActivity.this , ListOfHotelsActivity.class );
                intent.putExtra("city_name", destination.getText().toString());
                intent.putExtra("max_car_price", progress);
                startActivity( intent );
            }
        } );

        seekBar = (SeekBar)findViewById(R.id.seekBar1_hotel);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //seekVal.setText(progress);
                Toast.makeText(HotelActivity.this, "Max price: " + progress, Toast.LENGTH_SHORT).show();
            }
        });

        datepicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                //  String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);

                Calendar calselected = Calendar.getInstance();
                calselected.setTime( date );

                String selectedDate = "" + calselected.get( Calendar.DAY_OF_MONTH )
                        + "-" + ( calselected.get( Calendar.MONTH ) + 1 )
                        + "-" + ( calselected.get( Calendar.YEAR ) );


                if (counter % 2 == 0) {
                    datefrom = selectedDate;
                    Log.d( "from", datefrom );
                    fday = calselected.get( Calendar.DAY_OF_MONTH );
                } else {
                    dateto = selectedDate;
                    Log.d( "to", dateto );
                    lday = calselected.get( Calendar.DAY_OF_MONTH );
                }
                counter++;
                num_days = lday - fday + 1;
                //Toast.makeText(CarActivity.this, selectedDate, Toast.LENGTH_SHORT).show();
                Toast.makeText( HotelActivity.this, "days: " + num_days, Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onDateUnselected(Date date) {

            }


        });
    }
}
