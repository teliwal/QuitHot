package fr.quithot.com.quithot.domain;

import android.support.annotation.NonNull;

/**
 * Created by telly on 16/03/18.
 */

public class Score implements Comparable<Score>{

    private String joueur;
    private  int temps;

    public Score(String nom,int t){
        joueur = nom;
        temps = t;
    }

    public int getTemps() {
        return temps;
    }

    public String getJoueur() {
        return joueur;
    }

    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public void setTemps(int temps) {
        this.temps = temps;
    }

    @Override
    public int compareTo(@NonNull Score o) {
        if(this.temps > o.getTemps()){
            return 1;
        }else if(this.temps == o.getTemps()){
            return 0;
        }else{
            return -1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score = (Score) o;

        if (getTemps() != score.getTemps()) return false;
        return getJoueur().equals(score.getJoueur());
    }

    @Override
    public int hashCode() {
        int result = getJoueur().hashCode();
        result = 31 * result + getTemps();
        return result;
    }
}
