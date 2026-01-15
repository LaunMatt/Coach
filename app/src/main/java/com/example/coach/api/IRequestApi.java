package com.example.coach.api;

import com.example.coach.model.Profil;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface de liaison à l'API
 */
public interface IRequestApi {
    /**
     * Méthode qui permet de récupérer des profils
     *
     * @return
     */
    @GET("profil")
    Call<ResponseApi<List<Profil>>> getProfils();

    /**
     * Méthode qui permet de créer un profil
     *
     * @param profilJson
     * @return
     */
    @FormUrlEncoded
    @POST("profil")
    Call<ResponseApi<Integer>> creerProfil(@Field("champs") String profilJson);

    /**
     * Méthode qui permet de supprimer un profil
     *
     * @param profilJson
     * @return
     */
    @DELETE("profil/{champs}")
    Call<ResponseApi<Integer>> supprProfil(@Path(value = "champs", encoded = true) String profilJson);
}
