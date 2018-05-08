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
import com.example.lenovo.finalgp_test1.TripPlannerClasses.ToursClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListOfToursActivity extends AppCompatActivity {
    private String cityname;
    private int max_price, num_persons;
    private String url;
    //private String url = "http://192.168.56.1/trip_planner/ret_hotels.php";

    private RecyclerView tourL;
    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<ToursClass> tourList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_list_of_tours );

        cityname = getIntent().getExtras().getString("city_name");
        max_price = getIntent().getExtras().getInt("max_price");
        num_persons = getIntent().getExtras().getInt("num_persons");

        url = "http://10.0.3.2/trip_planner/ret_tours.php?cityname=" + cityname;
        tourL = findViewById( R.id.tours_rec );
        tourList = new ArrayList<ToursClass>();
        adapter = new TourAdapter(getApplicationContext(), tourList);
        linearLayoutManager = new LinearLayoutManager( this );
        linearLayoutManager.setInitialPrefetchItemCount( LinearLayoutManager.VERTICAL );
        dividerItemDecoration = new DividerItemDecoration( tourL.getContext(), linearLayoutManager.getOrientation() );

        tourL.setHasFixedSize( true );
        tourL.setLayoutManager( linearLayoutManager );
        tourL.addItemDecoration( dividerItemDecoration );
        tourL.setAdapter( adapter );
        getData();


    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        //Toast.makeText(this, ""+ cityname, Toast.LENGTH_SHORT).show();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                //Toast.makeText(getApplicationContext(), ""+ cityname, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        ToursClass toursClass = new ToursClass();
                        toursClass.setTour_name( jsonObject.getString( "tour name" ) );
                        toursClass.setTour_price(jsonObject.getInt( "tour price" ) );
                        toursClass.setCity_name(jsonObject.getString("city name"));
                        toursClass.setTour_img(jsonObject.getString("tour img"));
                        if(jsonObject.getInt( "tour price" ) <= max_price) {
                            tourList.add(toursClass);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                Toast.makeText( ListOfToursActivity.this, "count: " + response.length(), Toast.LENGTH_LONG ).show();
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
