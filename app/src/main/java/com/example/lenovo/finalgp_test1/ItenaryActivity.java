package com.example.lenovo.finalgp_test1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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

public class ItenaryActivity extends AppCompatActivity {

    String url;
    String adventure, dinning, sports, entertainment, historical, exploring,
            religious, artistic;
    public List<ToursClass> tourList;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itenary);

        adventure = getIntent().getExtras().getString("adv");
        dinning = getIntent().getExtras().getString("din");
        sports = getIntent().getExtras().getString("sport");
        entertainment = getIntent().getExtras().getString("ent");
        historical = getIntent().getExtras().getString("hist");
        exploring = getIntent().getExtras().getString("exp");
        religious = getIntent().getExtras().getString("rel");
        artistic = getIntent().getExtras().getString("art");

        txt = findViewById(R.id.txtItenary);
        tourList = new ArrayList<>();
        if(getIntent().getExtras().getString("filter").equals("price"))
            url = "http://suspense.atwebpages.com/api/tours_price_sort.php?city_id=2";
        else
            url = "http://suspense.atwebpages.com/api/tours_rating_sort.php?city_id=2";

        getTours();
//        for (int j = 0; j < tourList.size(); j++) {
//            Log.d("tour_nameeee", tourList.get(j).getTour_name());
//            Toast.makeText(ItenaryActivity.this, "" + tourList.get(j).getTour_name(), Toast.LENGTH_SHORT).show();
//        }

    }

    private void getTours(){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("JSON_Hassan", response.toString());
                //Toast.makeText(GenerateItineraryActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        ToursClass toursClass = new ToursClass();
                        toursClass.setCity_name(jsonObject.getString("tour_name"));
                        toursClass.setTour_img(jsonObject.getString("tour_img"));
                        toursClass.setTour_id(jsonObject.getInt("tour_id"));
                        toursClass.setTour_price(jsonObject.getInt("tour_price"));
                        toursClass.setRating(jsonObject.getDouble("rating"));
                        toursClass.setCategory(jsonObject.getString("category"));

                        toursClass.setClose(Integer.parseInt(jsonObject.getString("closes_at").substring(0,1)));
                        toursClass.setOpen(Integer.parseInt(jsonObject.getString("opens_at").substring(0,1)));

                        if(adventure.equals("yes")){
                            if(toursClass.getCategory().equals("Adventure"))
                                tourList.add(toursClass);
                        }
                        else if(dinning.equals("yes")){
                            if(toursClass.getCategory().equals("Dinning"))
                                tourList.add(toursClass);
                        }
                        else if(sports.equals("yes")){
                            if(toursClass.getCategory().equals("Sports"))
                                tourList.add(toursClass);
                        }
                        else if(entertainment.equals("yes")){
                            if(toursClass.getCategory().equals("Entertainment"))
                                tourList.add(toursClass);
                        }
                        else if(historical.equals("yes")){
                            if(toursClass.getCategory().equals("Historical"))
                                tourList.add(toursClass);
                        }
                        else if(exploring.equals("yes")){
                            if(toursClass.getCategory().equals("Exploring"))
                                tourList.add(toursClass);
                        }
                        else if(religious.equals("yes")){
                            if(toursClass.getCategory().equals("Religious"))
                                tourList.add(toursClass);
                        }
                        else if(artistic.equals("yes")){
                            if(toursClass.getCategory().equals("Artistic"))
                                tourList.add(toursClass);
                        }
                        //Toast.makeText(ItenaryActivity.this, "" + tourList.get(i).getTour_name(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
