package com.example.lenovo.finalgp_test1;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.finalgp_test1.TripPlannerClasses.HotelClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfHotelsActivity extends AppCompatActivity {

    private String url = "http://10.0.3.2/trip_planner/ret_hotels.php";
    //private String url = "http://192.168.56.1/trip_planner/ret_hotels.php";

    private RecyclerView hotelL;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<HotelClass> hotelList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_of_hotels );

        hotelL = findViewById( R.id.main_list );
        hotelList = new ArrayList<>();
        adapter = new HotelAdapter( getApplicationContext(), hotelList );
        linearLayoutManager = new LinearLayoutManager( this );
        linearLayoutManager.setInitialPrefetchItemCount( LinearLayoutManager.VERTICAL );
        dividerItemDecoration = new DividerItemDecoration( hotelL.getContext(), linearLayoutManager.getOrientation() );

        hotelL.setHasFixedSize( true );
        hotelL.setLayoutManager( linearLayoutManager );
        hotelL.addItemDecoration( dividerItemDecoration );
        hotelL.setAdapter( adapter );
        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        HotelClass hotelClass = new HotelClass();
                        hotelClass.setHotel_name( jsonObject.getString( "hotel_name" ) );
                        hotelClass.setHotel_stars( jsonObject.getInt( "hotel_stars" ) );
                        hotelList.add(hotelClass);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                Toast.makeText( ListOfHotelsActivity.this, "" + response.length(), Toast.LENGTH_LONG ).show();
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
