package com.example.projet_final_mobile;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApiCall_Item {

    //Appel l'api pour recevoir la liste d'item de l'api
    public static String GetAllItems() {
        try {
            //Parametre de la requete Get
            URL url = new URL("https://mathf32.pythonanywhere.com/api/items");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //Reponse de l'api
            int responsecode = conn.getResponseCode();


            //Lecture de la réponse
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Log.d("Reponse", response.toString());
            return response.toString();
        } catch (IOException e) {
            Log.d("Erreur", e.getMessage());
            return "Erreur";
        }
    }
}
