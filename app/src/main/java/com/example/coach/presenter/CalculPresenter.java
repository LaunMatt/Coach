package com.example.coach.presenter;

import android.content.Context;

import com.example.coach.contract.ICalculView;
import com.example.coach.data.ProfilDAO;
import com.example.coach.model.Profil;

import java.util.Date;

/**
 * Classe concernant le presenter
 */
public class CalculPresenter {
    private ICalculView vue;
    private Profil profil;
    private ProfilDAO profilDAO;

    /**
     * Constructeur
     *
     * @param vue
     */
    public CalculPresenter(ICalculView vue, Context context) {
        this.vue = vue;
        this.profilDAO = new ProfilDAO(context);
    }

    /**
     * Méthode qui permet de récupérer la création d'un profil avec ses différentes informations
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe) {
        profil = new Profil(poids, taille, age, sexe, new Date());
        profilDAO.insertProfil(profil);

        // Résultats poussés vers la vue
        vue.afficherResultat(
                profil.getImage(),
                profil.getImg(),
                profil.getMessage(),
                profil.normal()
        );
    }

    /**
     * Méthode qui permet de récupérer le dernier profil dans la bdd et envoie les informations à la vue
     */
    public void chargerDernierProfil() {
        Profil profil = profilDAO.getLastProfil();
        if (profil != null) {
            vue.remplirChamps(
                    profil.getPoids(),
                    profil.getTaille(),
                    profil.getAge(),
                    profil.getSexe()
            );
        }

    }
}
