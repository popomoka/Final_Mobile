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

public class ApiCall_Monstre {

    public static String AllMonstre() {
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
}

