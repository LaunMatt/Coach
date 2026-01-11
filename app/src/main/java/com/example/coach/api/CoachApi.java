package com.example.coach.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Classe d'accès à l'API
 */
public class CoachApi {
    /**
     * URL de l'API
     */
    private static final String API_URL = "http://10.0.2.2/rest_coach/";
    private static Retrofit retrofit = null;
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    /**
     * Retourne le gson
     *
     * @return
     */
    public static Gson getGson() {
        return gson;
    }

    /**
     * Méthode qui permet de construire l'objet unique qui permet d'accéder à l'API
     *
     * @return retrofit
     */
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            // crée l'objet d'accès à l'API
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL) // renseigne l'url de l'API
                    .addConverterFactory(GsonConverterFactory.create(gson)) // ajoute le convertisseur json
                    .build();
        }
        return retrofit;
    }
}
