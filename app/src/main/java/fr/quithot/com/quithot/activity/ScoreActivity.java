package fr.quithot.com.quithot.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

public class ScoreActivity extends AppCompatActivity {

    ListView listViewScore;
    DatabaseReference referenceRoot =  FirebaseDatabase.getInstance().getReference();
    DatabaseReference refrenceSccoreTemps = referenceRoot.child("scoreTemps");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        listViewScore = (ListView) findViewById(R.id.list_view_score);

    }

    protected void onStart(){
        super.onStart();
        refrenceSccoreTemps.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<Score>> genericTypeIndicator =new GenericTypeIndicator<List<Score>>(){};
                List<Score> scores = new ArrayList<Score>();
                scores = dataSnapshot.getValue(genericTypeIndicator);
                if(scores == null){
                    scores = new ArrayList<Score>();
                }else{
                    if(scores.size() > 0){
                                   Collections.sort(scores);
                    }
                }
                ScoreAdaptateur adapter = new ScoreAdaptateur(ScoreActivity.this,scores.subList(0, min(scores.size(), 3)));
                listViewScore.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
