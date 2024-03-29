package com.example.coach.controller;

import android.content.Context;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profile;
import com.example.coach.outils.Serializer;

import org.json.JSONArray;

import java.util.Date;

public final class Controle {

    /**
     * Création d'une instance de la classe Controle
     */
    private static Controle instance = null;
    private static Profile profile;
    private static String nomFic = "saveprofil";
   // private static AccesLocal accesLocal;
    private static AccesDistant accesDistant;

    /**
     * Constructeur vide, on met super car TOUTES les classes héritent de la classe Object
     */
    private Controle() {

        super();
    }


    /**
     * Création de l'instance
     * @return instance
     */
    public static final Controle getInstance(Context context){

        if(Controle.instance == null){
            Controle.instance = new Controle();
            // Au moment de la création de l'instantce Singleton je déserialize
         //   recupSerialize(context);
            accesDistant = new AccesDistant();
         //   profile = accesLocal.recupDernier();
            accesDistant.envoi("dernier", new JSONArray());
        }
        return Controle.instance ;
    }


    /**
     * Création du profile pour récupérer les paramètres suivants :
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 si Homme, 0 si Femme
     * @param context
     */
    public void creerProfile(Integer poids, Integer taille, Integer age, Integer sexe, Context context){


        profile = new Profile(new Date(), poids, taille, age, sexe );


        //accesLocal.ajout(profile);

        accesDistant.envoi("enreg", profile.convertToJSONArray() );

        // ****Au moment de la création du Profil, je le sérialise

      //  Serializer.serialiaze(nomFic, profile, context);


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


    /**
     * Récuoération de l'objet sérialisé : le Profil
     * @param context
     */
    private static void recupSerialize(Context context){

        profile = (Profile)Serializer.deSerialize(nomFic, context);
    }

    /**
     * Recupération du poids
     * @return
     */
    public Integer getPoids(){

        // le cas où on ne récupère rine en données
        if(profile == null)
        {
            return null;
        }
        else
        {
            return profile.getPoids();
        }
    }


    public Integer getTaille(){

        // le cas où on ne récupère rine en données
        if(profile == null)
        {
            return null;
        }
        else
        {
            return profile.getTaille();
        }
    }

    public Integer getAge(){

        // le cas où on ne récupère rine en données
        if(profile == null)
        {
            return null;
        }
        else
        {
            return profile.getAge();
        }
    }

    public Integer getSexe(){

        // le cas où on ne récupère rien en données
        if(profile == null)
        {
            return null;
        }
        else
        {
            return profile.getSexe();
        }
    }




}
