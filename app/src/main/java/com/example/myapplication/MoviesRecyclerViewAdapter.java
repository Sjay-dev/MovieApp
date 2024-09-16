package com.example.myapplication;




import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.movieViewHolder>{
    Context context;
    ArrayList<Movies> moviesList = new ArrayList<>();

    public MoviesRecyclerViewAdapter(Context context, ArrayList<Movies> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }
    @NonNull
    @Override
    public MoviesRecyclerViewAdapter.movieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movies_list_view , parent , false);
        return new movieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesRecyclerViewAdapter.movieViewHolder holder, int position) {
      Picasso.get().load(moviesList.get(position).getImageUrl())
              .into(holder.movieImage);
        holder.title.setText(moviesList.get(position).getTitle());
        holder.desc.setText(moviesList.get(position).getDesc());


        holder.movieView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , MovieView.class);
                Bundle bundle = new Bundle();
           bundle.putSerializable("movieData" , moviesList.get(position));
                intent.putExtras(bundle);
               context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class movieViewHolder extends RecyclerView.ViewHolder {
        TextView title , desc;
        ImageView movieImage ;

        RelativeLayout movieView;
        public movieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtMovieTitle);
            desc = itemView.findViewById(R.id.txtMoviesDesc);
            movieImage = itemView.findViewById(R.id.imgMovies);
            movieView = itemView.findViewById(R.id.viewMovies);


        }
    }
}

