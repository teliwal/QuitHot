package fr.quithot.com.quithot.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import fr.quithot.com.quithot.R;
import fr.quithot.com.quithot.domain.Parametres;
import fr.quithot.com.quithot.views.GameSurfaceView;

public class MainActivity extends AppCompatActivity {

    private GameSurfaceView view;
    private static Context context;
    private static Intent oldIntent;
    public static String diff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        SharedPreferences preferences = getSharedPreferences(Parametres.DIFFICULTE, MODE_PRIVATE);
        diff = preferences.getString(Parametres.DIFFICULTE, "DIFFICULTE");
        setContentView(new GameSurfaceView(this, diff));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        SharedPreferences preferences = getSharedPreferences(Parametres.DIFFICULTE, MODE_PRIVATE);
        diff = preferences.getString(Parametres.DIFFICULTE, "DIFFICULTE");
        setContentView(new GameSurfaceView(this, diff));
    }

    public static void gameOver() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("GAME OVER\n RETRY?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        Intent intent = new Intent(context, GameActivity.class);
                        context.startActivity(intent);
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
