package com.example.coach.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;

/**
 * Classe principale de l'activity menu exécutée au lancement de l'application
 */
public class MainActivity extends AppCompatActivity {
    private ImageButton btnMonIMG;
    private ImageButton btnMonHistorique;

    /**
     * Méthode de chargement au lancement de l'application
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    /**
     * Méthode qui permet d'initialiser des éléments
     */
    private void init() {
        chargeObjetsGraphiques();
        creerMenu();
    }

    /**
     * Récupération des objets graphiques
     */
    private void chargeObjetsGraphiques(){
        btnMonIMG = findViewById(R.id.btnMonIMG);
        btnMonHistorique = findViewById(R.id.btnMonHistorique);
    }

    /**
     * Méthode qui permet une écoute sur le menu
     *
     * @param classe
     */
    private void ecouteMenu(Class classe){
        Intent intent = new Intent(MainActivity.this, classe);
        startActivity(intent);
    }

    /**
     * Méthode qui permet une écoute sur les boutons du menu
     */
    private void creerMenu(){
        btnMonIMG.setOnClickListener(v -> ecouteMenu(CalculActivity.class));
        btnMonHistorique.setOnClickListener(v -> ecouteMenu(HistoActivity.class));
    }
}