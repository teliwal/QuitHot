package fr.quithot.com.quithot.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.quithot.com.quithot.R;
import fr.quithot.com.quithot.domain.Difficulte;
import fr.quithot.com.quithot.domain.Parametres;

public class GameActivity extends AppCompatActivity {

    Button bouttonFacile;
    Button bouttonMoyens;
    Button bouttonDifficile;
    Button bouttonScore;
    Button bouttonAide;
    SharedPreferences preferences;

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
        preferences = getSharedPreferences(Parametres.DIFFICULTE, MODE_PRIVATE);
    }

    private void activateFacile(){
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        preferences.edit().putString(Parametres.DIFFICULTE, "FACILE").apply();
        startActivity(intent);
    }

    public void activateMoyen(View view) {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        preferences.edit().putString(Parametres.DIFFICULTE, "MOYEN").apply();
        startActivity(intent);
    }

    public void activateDifficile(View view) {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        preferences.edit().putString(Parametres.DIFFICULTE, "DIFFICILE").apply();
        startActivity(intent);
    }

}
