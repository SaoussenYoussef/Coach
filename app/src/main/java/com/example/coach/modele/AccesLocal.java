package com.example.coach.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {

    // propriétés

    private String nomBase ="bdcoach.sqlite";
    private Integer versionBase =1;
    private MySQLiteOpenHelper acceBD; // objet pour accéder à la BDD
    private SQLiteDatabase bd; // permet de crééer des canaux d 'écriture ou
    // de lecture dans la BDD


    /**
     * Constructeur : instancier la classe MySQLiteOpenHelper
     * @param context
     */
    public AccesLocal(Context context){
        acceBD = new MySQLiteOpenHelper(context, nomBase, null, versionBase);

    }

    /**
     * Permet d'ajouter un profile à la BDD
     * @param profile
     */
    public void ajout(Profile profile){

        bd = acceBD.getWritableDatabase();
        String req = "insert into profile(datemesure, poids, taille, age, sexe) values";
                req +="(\""+profile.getDatemesure()+"\" , "+profile.getPoids()+","+profile.getTaille()+" ,"+profile.getAge()+" , "+profile.getSexe()+"  )" ;
                bd.execSQL(req);
    }


    /**
     * Récupération du dernier profile de la base de données
     * @return
     */
    public Profile recupDernier(){

        bd = acceBD.getReadableDatabase();
        Profile profile = null; // contiendra le dernier profile créé s'il y en a
                                // dans la BDD

        String req = "select * from profile";

        Cursor cursor = bd.rawQuery(req, null) ;

        cursor.moveToLast();

        // Est-ce que je suis après le dernier ?
        // Si je ne suis pas "après lde dernier" alors je peux récupérer le profil

        if (!cursor.isAfterLast()) {
            Date date  = new Date();
            Integer poids = cursor.getInt(1);
            Integer  taille = cursor.getInt(2);
            Integer  age = cursor.getInt(3);
            Integer  sexe = cursor.getInt(4);

           profile = new Profile(date, poids, taille, age, sexe);
        }
        cursor.close();
        return profile;
    }



}
