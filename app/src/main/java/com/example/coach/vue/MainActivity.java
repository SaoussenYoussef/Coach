package com.example.coach.vue;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach.R;
import com.example.coach.controller.Controle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        this.controle = Controle.getInstance();
    }



    // Propriétés

    private EditText txtPoids;
    private EditText txtTaille;
    private EditText txtAge;
    private RadioButton rdHomme;
    private TextView lblIMG;
    private ImageView imgSmiley;

    private Controle controle;

    /**
     * Initialisation des liens avec les objets graphiques
     * */
    private void init() {

        txtPoids = (EditText)findViewById(R.id.txtPoids);
        txtTaille = (EditText)findViewById(R.id.txtTaille);
        txtAge    = (EditText)findViewById(R.id.txtAge);
        rdHomme = (RadioButton)findViewById(R.id.rdHomme);
        lblIMG  = (TextView)findViewById(R.id.lblIMG);
        imgSmiley = (ImageView)findViewById(R.id.imgSmiley);
        ecouteCalcul();
    }


    /**
     * Ecoute le click sur le bouton Calcul, controle les données saisies (il faut qu'lles soient toutes de type Integer
     * et enfin appelle la methode afficheResult qui crée un Profile et génère l'IMG  et le message correspondant
     */
    private void ecouteCalcul(){

        findViewById(R.id.btnCalc).setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "C EST PARTI !!", Toast.LENGTH_SHORT).show();
                Log.d("message", "Click OK pour le bouton Calc************************************");


                Integer poids = 0;
                Integer taille = 0;
                Integer age = 0;
                Integer sexe = 0 ;


                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Saisir des chiffres", Toast.LENGTH_SHORT).show();
                }

                if(rdHomme.isChecked()) {
                    sexe = 1;
                }

                // Controle des données saisies

                if( (poids == 0) || (taille == 0) || (age == 0) ){

                    Toast.makeText(MainActivity.this, "Saisie incorrecte!!", Toast.LENGTH_SHORT).show();

                } else {

                    afficheResult(poids, taille, age, sexe);
                }


            }
        });

    }

    /**
     * la methode afficheResult crée un Profile et génère l'IMG, le message correspondant et le Smiley
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficheResult(Integer poids, Integer taille, Integer age, Integer sexe) {


        // Création de Profile et récupération des informations





        this.controle.creerProfile(poids, taille, age, sexe);

        float img = this.controle.getImg();
        String msg = this.controle.getMessage();

        if(msg.equals("Normal")){

            imgSmiley.setImageResource(R.drawable.smiley_content_vert);
            lblIMG.setTextColor(Color.GREEN);
        }

        else{

            if(msg.equals("Trop faible")){

                imgSmiley.setImageResource(R.drawable.smiley_moyen_content);
                lblIMG.setTextColor(Color.BLACK);

            }else {

                imgSmiley.setImageResource(R.drawable.smiley_pas_content);
                lblIMG.setTextColor(Color.RED);
            }
        }
        lblIMG.setText(String.format("%s :  IMG%s", img, msg));


    }


    /*public void setControle(Controle controle) {
        this.controle = controle;
    }*/
}
