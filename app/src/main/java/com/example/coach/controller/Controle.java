package com.example.coach.controller;

import com.example.coach.modele.Profile;

public final class Controle {

    /**
     * Création d'une instance de la classe Controle
     */
    private static Controle instance = null;
    private Profile profile;

    /**
     * Constructeur vide, on met super car toutes les classes héritent de la classe Object
     */
    private Controle() {

        super();
    }


    /**
     * Création de l'instance
     * @return instance
     */
    public static final Controle getInstance(){

        if(Controle.instance == null){
            Controle.instance = new Controle();
        }
        return Controle.instance ;
    }


    /*Integer p = profile.getPoids();
    Integer t = profile.getTaille();
    Integer a = profile.getAge();
    Integer s = profile.getSexe();*/


    public void creerProfile(Integer p, Integer t, Integer a, Integer s){


        profile = new Profile(p, t, a, s );


    }


    /**
     * Récupération de limg du profile
     * @return de l'img
     */
    public float getImg(){
        return profile.getImg();
    }


    /**
     *  Récupération du Message de profile
     * @return message
     */
    public String getMessage(){

        return profile.getMessage();
    }



}
