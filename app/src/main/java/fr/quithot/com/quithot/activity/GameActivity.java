package fr.quithot.com.quithot.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.quithot.com.quithot.R;
import fr.quithot.com.quithot.domain.Score;
import fr.quithot.com.quithot.domain.ScoreAdaptateur;

import static java.lang.Math.min;

public class GameActivity extends AppCompatActivity {

    Button bouttonFacile;
    Button bouttonMoyens;
    Button bouttonDifficile;
    Button bouttonScore;
    Button bouttonAide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        bouttonFacile = (Button) findViewById(R.id.bouttonFacile);
        bouttonMoyens = (Button) findViewById(R.id.buttonMoyens);
        bouttonDifficile = (Button) findViewById(R.id.buttonDifficile);
        bouttonScore = (Button) findViewById(R.id.buttonScore);
        bouttonAide = (Button) findViewById(R.id.buttonAide);
        bouttonFacile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activiteFacile();
            }
        });

        bouttonScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activiteScore();
            }
        });
    }

    private void activiteFacile(){
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void activiteScore(){
        Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
        startActivity(intent);
    }
}
