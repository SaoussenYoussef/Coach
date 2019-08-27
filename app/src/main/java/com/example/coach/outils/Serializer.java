package com.example.coach.outils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import static android.content.Context.MODE_PRIVATE;


/**
 * Classe qui permet de serialiser et déserialiser des Objets
 */
public abstract class Serializer {

    /**
     * Serialisation d'un objet
     * @param filename : dans ce fichier portant ce nm on sérialise l'objet "object"
     * @param object
     * @param context : il s'agit d'une activity
     */
    public static void serialiaze(String filename, Object object, Context context){

        try {

            FileOutputStream file = context.openFileOutput(filename, MODE_PRIVATE);
            ObjectOutputStream oos;


            try{

                oos = new ObjectOutputStream(file);
                oos.writeObject(object);
                oos.flush();
                oos.close();
            } catch (IOException e) {

                // erreur de serialisation
                e.printStackTrace();
            }

        }catch (FileNotFoundException e) {

            // Fichier non trouvé

            e.printStackTrace();
        }

    }

    /**
     * Déserailisation d'un objet
     * @param filename
     * @param context : il s'agit d'un activity
     * @return
     */
    public static Object deSerialize(String filename, Context context) {

        try{
            FileInputStream file = context.openFileInput(filename);
            ObjectInputStream ois;

            try {
                ois = new ObjectInputStream(file);
                try {
                    Object object = ois.readObject();
                    ois.close();
                    return object;
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }

            }catch(StreamCorruptedException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
