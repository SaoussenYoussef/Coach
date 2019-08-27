package com.example.coach.outils;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //Propriétés
    private String creation="create table profile(datemesure TEXT PRIMARY KEY, poids INTEGER NOT NULL, taille INTEGER NOT NULL, age INTEGER NOT NULL, sexe INTEGER NOT NULL);";


    /**
     * Constructeur : il vérifie si la table existe, si elle n'existe pas,
     * il la crée, si elle existe il ne va pas la recréer
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Cette méthode ne s'éxécute que Si changement de BDD
     * exp changement du nom de la Base, donc elle utiliser al même BDD la prochiane fois
     * sans avoir à recrééer la même BDD
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(creation);

        // la ppté creation permet de créer la table une seule fois
        // Et c'est le constructeur qui vérifie si elle existe ou pas

    }

    /**
     * Si changement de version
     * @param sqLiteDatabase
     * @param i ancienne version
     * @param i1 nouvelle version
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
