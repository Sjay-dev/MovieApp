package com.example.myapplication;

import android.media.MediaController2;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MovieView extends AppCompatActivity {

    TextView title , desc;
    VideoView player;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_view);
        Bundle bundle = getIntent().getExtras();
        Movies movies = (Movies) bundle.get("movieData");

            player = findViewById(R.id.videoPlayer);
            title = findViewById(R.id.txtplayerTitle);
            desc = findViewById(R.id.txtplayerDesc);
            progressBar = findViewById(R.id.ProgressBar);
            progressBar.setVisibility(View.VISIBLE);
        MediaController mc = new MediaController(this);
        player.setMediaController(mc);

            Uri videoUrl = Uri.parse(movies.getVideoURL());
            player.setVideoURI(videoUrl);

            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp4) {
                    player.start();
                    progressBar.setVisibility(View.GONE);
                }
            });

        title.setText(movies.getTitle());
        desc.setText(movies.getLongDesc());
    }
}