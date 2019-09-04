package com.example.coach.outils;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class AccesHTTP extends AsyncTask<String, Integer, String> {


    private ArrayList<NameValuePair> parameters; // contient des paramètres de type POST : nom, valeur
    private String ret = null; // chaine de caractères récupérant la réponse
    public AsyncResponse delegate = null;


    /**
     * Constructeur : on instancie la classe NameVlauePair qui permettra de
     * récupérer des variables sous la forme clé:valeur
     */
    public AccesHTTP(){
        parameters = new ArrayList<NameValuePair>();
    }


    /**
     * Ajout d'un paramètre Post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur){

        // ajout d'un couple nom, valeur
        parameters.add(new BasicNameValuePair(nom, valeur));
    }

    /**
     * Connexion en tâche de fond dans un thread séparé
     * @param strings
     * @return
     */
    @Override
    protected String doInBackground(String... strings) {

        // Objets nécessaires à la connexion

        HttpClient cnxHttp = new DefaultHttpClient();// se connecter
        HttpPost paramCnx = new HttpPost(strings[0]);// envoyer  les paramètres en POST et attends les paramètres
        // et qui seront contenues dans la case zéro du tableau contenu lui même dans l'enveloppe POST


        try {

            // Encodage des paramètres
            paramCnx.setEntity(new UrlEncodedFormEntity(parameters));

            // Connexion et envoi des paramètres, attente de réponse

            HttpResponse reponse = cnxHttp.execute(paramCnx); // tant que le Client n'a pas reçu de réponse
            // il reste bloqué sur cette ligne

            // Récupération de l reponse = Entity et sa Transformation en String

            ret = EntityUtils.toString(reponse.getEntity());



        } catch (UnsupportedEncodingException e) {
            Log.d("Erreur encodage", "********************************" +e.toString())  ;
        } catch (ClientProtocolException e) {
            Log.d("Erreur protocole", "********************************" +e.toString())  ;
        } catch (IOException e) {
            Log.d("Erreur Input/Output", "********************************" +e.toString())  ;
        }
        return null ;
    }

    /**
     * méthode qui va s'éxecuter en dehors du thread, cela nous évite
     * d'avoir une multittude de connexion Http
     * @param s
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    /*@Override
    protected void onPostExecute(Long result){
        super.onPostExecute(result);
        delegate.processFinish(ret);
    }*/
}
