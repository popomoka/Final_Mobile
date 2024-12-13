package com.example.projet_final_mobile;
import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.*;

public class API_Caller {

    public static String GetAllMonstre() {
        try {
            URL url = new URL("https://mathf32.pythonanywhere.com/api/monsters");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //Getting the response code
            int responsecode = conn.getResponseCode();


            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Log.d("Reponse",response.toString());
            return response.toString();
        } catch (IOException e) {
            Log.d("Erreur",e.getMessage());
            return "Erreur";
        }


    }

    public static String GetAllItems() {
        try {
            URL url = new URL("https://mathf32.pythonanywhere.com/api/items");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            //Getting the response code
            int responsecode = conn.getResponseCode();


            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            Log.d("Reponse",response.toString());
            return response.toString();
        } catch (IOException e) {
            Log.d("Erreur",e.getMessage());
            return "Erreur";
        }


    }

    public static String connect(String username, String password) {
        try {
            // Créer l'URL de l'API
            URL url = new URL("https://mathf32.pythonanywhere.com/login/");  // Remplacez par votre URL

            // Ouvrir la connexion
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");  // Spécifier que la requête est en JSON
            conn.setDoOutput(true);

            // Créer les données de la requête JSON
            String jsonInputString = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";  // Remplacez par vos données

            // Écrire les données dans le flux de sortie
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

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
            Log.d("connect", e.getMessage());
            return null;
        }


    }
}
