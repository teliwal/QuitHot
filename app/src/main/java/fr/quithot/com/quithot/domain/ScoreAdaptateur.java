package fr.quithot.com.quithot.domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.quithot.com.quithot.R;

/**
 * Created by bahpetit on 16/03/2018.
 */

public class ScoreAdaptateur  extends BaseAdapter {
    Context mContext;
    List<Score> scoreList;

    public ScoreAdaptateur() {
        scoreList = new ArrayList<Score>();
    }

    public ScoreAdaptateur(Context mContext, List<Score> scoreList) {
        this.mContext = mContext;
        this.scoreList = scoreList;
    }

    @Override
    public int getCount() {
        return scoreList.size();
    }

    @Override
    public Score getItem(int position) {
        return scoreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.en_tete_list_scores, null);
        }
        else {
            view = convertView;
        }
        TextView nomJoueur = (TextView) view.findViewById(R.id.nom_joueurs);
        nomJoueur.setText(scoreList.get(position).getJoueur());
        TextView scoreJoueur = (TextView) view.findViewById(R.id.score_joueurs);
        scoreJoueur.setText(scoreList.get(position).getTemps());
        return view;
    }
}
