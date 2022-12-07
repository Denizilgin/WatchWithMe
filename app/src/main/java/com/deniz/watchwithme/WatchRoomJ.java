package com.deniz.watchwithme;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.deniz.watchwithme.databinding.ActivityWatchRoomJBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.HashMap;
import java.util.Map;

public class WatchRoomJ extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    private ActivityWatchRoomJBinding binding;
    private FirebaseAuth auth;
    private LandmarkAdapter landmarkAdapter;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    public String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_room_j);
        YouTubePlayerView youTubePlayerView = findViewById(R.id.ytb);
        getLifecycle().addObserver(youTubePlayerView);



        getData();

        Intent intent = getIntent();



//20,31

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
               String videoId = link.substring(32,43);
               youTubePlayer.loadVideo(videoId, 0);
                youTubePlayerView.enterFullScreen();


            }
        });
    }


    public void getData() {
        Intent intent = getIntent();
        Odalar selectedLandmark = (Odalar) intent.getSerializableExtra("Odalar");
        String roomname = selectedLandmark.getName();
        //binding.odaismitext.setText(selectedLandmark.getName());
        firestore.collection("rooms")
                .document(roomname)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        link = documentSnapshot.getString("RoomLink");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG,"onFailure: ",e);
                    }
                });

        }




}








