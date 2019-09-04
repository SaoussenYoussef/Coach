package com.example.coach.modele;

import android.util.Log;

import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;

import org.json.JSONArray;

/**
 * Classe permettant l'accès distant à la BDD
 */

public class AccesDistant implements AsyncResponse {

    // cette classe doit se connecter à un serveur

    // constantne

    private static final String SERVERADDR = "http://192.168.0.48:8080/coach/serveurcoach.php";

    //private static final String SERVERADDR = "http://localhost/coach/serveurcoach.php";

  //  private static final String SERVERADDR = "http://10.0.2.2/coach/serveurcoach.php";
    /**
     * Constructeur qui ne fait rien
     */
    public AccesDistant(){
        super();
    }


    /**
     *  récupère le retour du serveur distant
     * @param output
     */
    @Override
    public void processFinish(String output) {

        // pour voir les erreurs qui se passent côte PHP

        Log.d("serveur", "***********************"+output);

        // découpage du message reçu avec %

        String[] message = output.split("%");

        // dans message[0]  j'aurais une des trois possibilités:  "enreg", ""dernier", "Erreur" !

        // dans   message [1] : le reste du message


        // d'abord il faut vérifer s'il y a bien 2 cases, d'où l'utlisation de length

        if(message.length>1){
            if (message[0].equals("enreg")){

                Log.d("enreg", "*************************"+message[1]);

            }else{
                if(message[0].equals("dernier")){

                    Log.d("dernier", "*************************"+message[1]);

                }else{
                    if(message[0].equals("Erreur!")){
                        Log.d("Erreur!", "*************************"+message[1]);
                    }
                }
            }
        }

    }

    /**
     * Envoie des données au serveur (et pour cela elle crée un acces Http)
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONArray lesDonneesJSON){

        AccesHTTP accesDonnees = new AccesHTTP();

        // Lien de délégation

        accesDonnees.delegate = this;// j envoie l'instantce de mon !accesDitant Actuelmon objet

        // ajout paramètres
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());

        // appel au serveur
        accesDonnees.execute(SERVERADDR);

    }

}
