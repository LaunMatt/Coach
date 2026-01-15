package com.example.coach.view;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.coach.R;
import com.example.coach.contract.ICalculView;
import com.example.coach.model.Profil;
import com.example.coach.presenter.CalculPresenter;

/**
 * Classe de l'activity de calcul
 */
public class CalculActivity extends AppCompatActivity implements ICalculView {

    private EditText txtPoids, txtTaille, txtAge;
    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;
    private CalculPresenter presenter;

    /**
     * Méthode de chargement au lancement de l'activity
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calcul);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    /**
     * Méthode permettant l'affichage des éléments de résultat de l'application
     *
     * @param image
     * @param img
     * @param message
     * @param normal
     */
    @Override
    public void afficherResultat(String image, double img, String message, boolean normal) {
        int imageId = getResources().getIdentifier(image, "drawable", getPackageName());
        if (imageId != 0) {
            imgSmiley.setImageResource(imageId);
        } else {
            imgSmiley.setImageResource(R.drawable.normal);
        }

        String texte = String.format("%.01f", img) + " : IMG " + message;
        lblIMG.setText(texte);
        lblIMG.setTextColor(normal ? Color.GREEN : Color.RED);
    }

    /**
     * Méthode qui permet de remplir les champs de l'activity
     *
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    @Override
    public void remplirChamps(Integer poids, Integer taille, Integer age, Integer sexe) {
        txtPoids.setText(poids.toString());
        txtTaille.setText(taille.toString());
        txtAge.setText(age.toString());
        if(sexe == 1){
            rdHomme.setChecked(true);
        }else{
            rdFemme.setChecked(true);
        }
    }

    /**
     * Méthode qui permet de charger les objets graphiques
     */
    private void chargeObjetsGraphiques() {
        txtPoids = (EditText) findViewById(R.id.txtPoids);
        txtTaille = findViewById(R.id.txtTaille);
        txtAge = findViewById(R.id.txtAge);
        rdHomme = findViewById(R.id.rdHomme);
        rdFemme = findViewById(R.id.rdFemme);
        lblIMG = findViewById(R.id.lblResultat);
        imgSmiley = findViewById(R.id.imgSmiley);
        btnCalc = findViewById(R.id.btnCalc);
    }

    /**
     * Méthode qui permet d'initialiser des éléments
     */
    private void init() {
        chargeObjetsGraphiques();
        presenter = new CalculPresenter(this);
        btnCalc.setOnClickListener(v -> btnCalc_clic());
        recupProfil();
    }

    /**
     * Méthode qui permet de récupérer les valeurs des zones de saisie de l'application
     */
    private void btnCalc_clic() {
        Integer poids = 0, taille = 0, age = 0, sexe = 0;
        try {
            poids = Integer.parseInt(txtPoids.getText().toString());
            taille = Integer.parseInt(txtTaille.getText().toString());
            age = Integer.parseInt(txtAge.getText().toString());
        } catch (Exception ignored) {
        }

        if (rdHomme.isChecked()) {
            sexe = 1;
        }

        if (poids == 0 || taille == 0 || age == 0) {
            Toast.makeText(this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
        } else {
            presenter.creerProfil(poids, taille, age, sexe);
        }
    }

    /**
     * Méthode qui permet d'afficher un message de type Toast
     *
     * @param message
     */
    @Override
    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Méthode qui permet de récupérer un profil
     */
    private void recupProfil(){
        Profil profil = (Profil) getIntent().getSerializableExtra("profil");
        if (profil != null) {
            remplirChamps(profil.getPoids(), profil.getTaille(), profil.getAge(), profil.getSexe());
        }else{
            presenter.chargerDernierProfil();
        }
    }
}