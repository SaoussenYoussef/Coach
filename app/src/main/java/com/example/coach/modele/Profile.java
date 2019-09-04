package com.example.coach.modele;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profile implements Serializable {

// Les Constantes

    private static final Integer minFemme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme = 25;


    // Propriétés
    private Date datemesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private Float img;
    private String message;

    public Profile(Date datemesure, Integer poids, Integer taille, Integer age, Integer sexe) {
        this.datemesure = datemesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calcIMG();
        this.resultIMG();
    }

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public Float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDatemesure() {
        return datemesure;
    }

    private void calcIMG() {

        float tailleM = ((float) taille) / 100;
        this.img = (float) ((1.2 * poids / (tailleM * tailleM)) + (0.23 * age) - (10.83 * sexe) - (5.4));

    }

    private void resultIMG() {

        Integer min;
        Integer max;


        if (sexe == 0) {
            min = minFemme;
            max = maxFemme;

        } else {

            min = minHomme;
            max = maxHomme;

        }

        message = "Normal";

        if (img < min) {

            message = "Trop faible";
        } else {
            if (img > max) {
                message = "Trop élevé";
            }
        }
    }


    /**
     * conversion du profil au format JSONArray
     * @return
     */
    public JSONArray convertToJSONArray(){

        List laListe = new ArrayList();

        laListe.add(datemesure);
        laListe.add(poids);
        laListe.add(taille);
        laListe.add(age);
        laListe.add(sexe);
        return new JSONArray(laListe);

    }

}
