package com.example.coach.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.coach.contract.ICalculView;
import com.example.coach.model.Profil;
import com.google.gson.Gson;

import java.util.Date;

/**
 * Classe concernant le presenter
 */
public class CalculPresenter {
    private ICalculView vue;
    private Profil profil;
    private static final String NOM_FIC = "coach_fic";
    private static final String PROFIL_CLE = "profil_json";
    private Gson gson;
    private SharedPreferences prefs;

    /**
     * Méthode de liaison permettant de récupérer l'affichage du résultat provenant de la vue
     *
     * @param vue
     */
    public CalculPresenter(ICalculView vue, Context context) {
        this.vue = vue;
        this.prefs = context.getSharedPreferences(NOM_FIC, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    /**
     * Méthode permettant la création d'un profil avec ses différentes informations
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        profil = new Profil(poids, taille, age, sexe, new Date());
        sauvegarderProfil(profil);

        // Résultats poussés vers la vue
        vue.afficherResultat(
                profil.getImage(),
                profil.getImg(),
                profil.getMessage(),
                profil.normal()
        );
    }

    private void sauvegarderProfil(Profil profil){
        String json = gson.toJson(profil);
        prefs.edit().putString(PROFIL_CLE, json).apply();
    }

    public void chargerProfil(){
        String json = prefs.getString(PROFIL_CLE, null);
        if (json != null) {
            Profil profil = gson.fromJson(json, Profil.class);

            // on pousse les informations vers la vue
            vue.remplirChamps(
                    profil.getPoids(),
                    profil.getTaille(),
                    profil.getAge(),
                    profil.getSexe()
            );
        }
    }
}
