package com.example.coach.modele;

public class Profile {

// Les Constantes

    private static final Integer minFemme = 15;
    private static final Integer maxFemme = 30;
    private static final Integer minHomme = 10;
    private static final Integer maxHomme = 25;


    // Propriétés

    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private Float img;
    private String message;







    public Profile(Integer poids, Integer taille, Integer age, Integer sexe) {
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

}
