package com.example.coach.presenter;

import com.example.coach.contract.ICalculView;
import com.example.coach.model.Profil;

/**
 * Classe concernant le presenter
 */
public class CalculPresenter {
    private ICalculView vue;
    private Profil profil;

    /**
     * Méthode de liaison permettant de récupérer l'affichage du résultat provenant de la vue
     *
     * @param vue
     */
    public CalculPresenter(ICalculView vue) {
        this.vue = vue;
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
        profil = new Profil(poids, taille, age, sexe);

        // Résultats poussés vers la vue
        vue.afficherResultat(
                profil.getImage(),
                profil.getImg(),
                profil.getMessage(),
                profil.normal()
        );
    }
}
