package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Movie;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movies> moviesList = new ArrayList<>();
    RecyclerView recyclerView;

    ProgressDialog dialog;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rlMovies);

        String token = "h52lkwzgasm32wr8u6wjcfg4vpkqqra4qrxta9ep";

        MoviesRecyclerViewAdapter adapter = new MoviesRecyclerViewAdapter(this ,moviesList );
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.json-generator.com/templates/0AAZK4AO4l9t/data";

            dialog = new ProgressDialog(this);
            dialog.setTitle("Fetching Movies...");
            dialog.show();

            BearerTokenRequest request = new BearerTokenRequest(Request.Method.GET, url, null, token, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray array = response.getJSONArray("categories");
                        JSONObject object = array.getJSONObject(0);
                        JSONArray movies = object.getJSONArray("videos");


                        for (int a = 0 ; a<movies.length(); a++){
                            JSONObject ObjectMovies = movies.getJSONObject(a);
                            JSONArray videoArray = ObjectMovies.getJSONArray("sources");
                            String  Image = ObjectMovies.getString("thumb");
                            String   Title = ObjectMovies.getString("title");
                            String  Desc = ObjectMovies.getString("subtitle");
                            String LongDesc = ObjectMovies.getString("description");
                            String Video = videoArray.getString(0).toString();

                            moviesList.add(new Movies(Image , Title , Desc , LongDesc, Video));
                            adapter.notifyDataSetChanged();

                        }
                        dialog.dismiss();




                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(MainActivity.this, "An Error occurred", Toast.LENGTH_SHORT).show();
                }
            });




        queue.add(request);




    }
}

