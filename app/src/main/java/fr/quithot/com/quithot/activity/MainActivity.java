package fr.quithot.com.quithot.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import fr.quithot.com.quithot.R;
import fr.quithot.com.quithot.domain.Parametres;
import fr.quithot.com.quithot.views.GameSurfaceView;

public class MainActivity extends Activity {

    private GameSurfaceView view;
    private static Context context;
    private static Intent oldIntent;
    public static String diff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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

    public static void gameOver(int score) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);g
        builder1.setMessage("GAME OVER\n Votre score est :"+ score +"\n RETRY?");
        builder1.setCancelable(true);
        final EditText input = new EditText(context);
        input.setText("No Name");
        builder1.setView(input);
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
