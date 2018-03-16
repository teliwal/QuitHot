package fr.quithot.com.quithot.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.quithot.com.quithot.R;

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
                activateFacile();
            }
        });
    }

    private void activateFacile(){
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
    }


}
