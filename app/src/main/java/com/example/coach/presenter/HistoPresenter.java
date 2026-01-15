package com.example.coach.presenter;

import android.util.Log;

import com.example.coach.api.CoachApi;
import com.example.coach.api.HelperApi;
import com.example.coach.api.ICallbackApi;
import com.example.coach.api.IRequestApi;
import com.example.coach.api.ResponseApi;
import com.example.coach.contract.IHistoView;
import com.example.coach.model.Profil;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Classe concernant le presenter (activity d'historique)
 */
public class HistoPresenter {
    private IHistoView vue;

    /**
     * Constructeur
     *
     * @param vue
     */
    public HistoPresenter(IHistoView vue) {
        this.vue = vue;
    }

    /**
     * Méthode qui permet de récupérer les profils de la BDD distante et de les envoyer à la vue
     */
    public void chargerProfils() {
        HelperApi.call(HelperApi.getApi().getProfils(), new ICallbackApi<List<Profil>>(){
            @Override
            public void onSuccess(List<Profil> result) {
                if(result != null){
                    List<Profil> profils = result;
                    if (profils != null && !profils.isEmpty()) {
                        Collections.sort(profils, (p1, p2) -> p2.getDateMesure().compareTo(p1.getDateMesure()));
                        vue.afficherListe(profils);
                    }else{
                        vue.afficherMessage("échec chargement profils");
                    }
                }else{
                    vue.afficherMessage("échec chargement profils");
                }
            }

            @Override
            public void onError() {
                vue.afficherMessage("échec chargement profils");
            }
        });

    }

    /**
     * Méthode qui permet de supprimer dans la BDD et la liste le profil reçu en paramètre
     *
     * @param profil
     * @param callback
     */
    public void supprProfil(Profil profil, ICallbackApi<Void> callback) {
        String profilJson = CoachApi.getGson().toJson(profil);
        HelperApi.call(HelperApi.getApi().supprProfil(profilJson), new ICallbackApi<Integer>(){
            @Override
            public void onSuccess(Integer result) {
                if (result == 1) {
                    callback.onSuccess(null);
                    vue.afficherMessage("profil supprimé");
                }else{
                    vue.afficherMessage("échec supression profil");
                }
            }

            @Override
            public void onError() {
                vue.afficherMessage("échec suppression profil");
            }
        });
    }

    /**
     * Méthode qui permet de demander le transfert du profil vers une autre activity
     * @param profil
     */
    public void transfertProfil(Profil profil){
        vue.transfertProfil(profil);
    }
}
