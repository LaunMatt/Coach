package com.example.coach.presenter;

import android.util.Log;

import com.example.coach.api.CoachApi;
import com.example.coach.api.IRequestApi;
import com.example.coach.api.ResponseApi;
import com.example.coach.contract.ICalculView;
import com.example.coach.model.Profil;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        profil = new Profil(poids, taille, age, sexe, new Date());
        String profilJson = CoachApi.getGson().toJson(profil);
        IRequestApi api = CoachApi.getRetrofit().create(IRequestApi.class);
        Call<ResponseApi<Integer>> call = api.creerProfil(profilJson);
        call.enqueue(new Callback<ResponseApi<Integer>>() {
            @Override
            public void onResponse(Call<ResponseApi<Integer>> call, Response<ResponseApi<Integer>> response) {
                Log.d("API", "code : " + response.body().getCode() +
                        " message : " + response.body().getMessage() +
                        " result : " + response.body().getResult()
                );
                if (response.isSuccessful() && response.body().getResult() == 1) {
                    // envoie les résultats à la vue
                    vue.afficherResultat(
                            profil.getImage(),
                            profil.getImg(),
                            profil.getMessage(),
                            profil.normal()
                    );
                } else {
                    Log.e("API", "Erreur API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseApi<Integer>> call, Throwable throwable) {
                Log.e("API", "Échec d'accès à l'api", throwable);
            }
        });
    }

    /**
     * Récupère les profils de la BDD distante
     * Extrait le plus récent
     * Envoie ses informations à la vue
     */
    public void chargerDernierProfil() {
        // Crée l'objet d'accès à l'api avec les différentes méthodes d'accès
        IRequestApi api = CoachApi.getRetrofit().create(IRequestApi.class);
        // crée l'objet call qui envoie la demande
        Call<ResponseApi<List<Profil>>> call = api.getProfils();
        // exécute la requête en mode asynchrone
        call.enqueue(new Callback<ResponseApi<List<Profil>>>() {
            @Override
            public void onResponse(Call<ResponseApi<List<Profil>>> call, Response<ResponseApi<List<Profil>>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Profil> profils = response.body().getResult();
                    if (profils != null && !profils.isEmpty()) {
                        // récupérer le plus récent
                        Profil dernier = Collections.max(
                                profils,
                                (p1, p2) -> p1.getDateMesure().compareTo(p2.getDateMesure())
                        );
                        vue.remplirChamps(dernier.getPoids(), dernier.getTaille(),
                                dernier.getAge(), dernier.getSexe());
                    } else {
                        Log.d("API", "Aucun profil trouvé");
                    }
                } else {
                    Log.e("API", "Erreur API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ResponseApi<List<Profil>>> call, Throwable t) {
                Log.e("API", "Échec d'accès à l'api", t);
            }
        });
    }
}
