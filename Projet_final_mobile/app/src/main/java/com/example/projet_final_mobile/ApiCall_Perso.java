package com.example.projet_final_mobile;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.io.OutputStream;
import java.net.URL;


public class ApiCall_Perso {

    public static void PutPerso(User p) {
        try {
            URL url = new URL("https://mathf32.pythonanywhere.com/api/Utilisateurs/"+p.id+"/");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{\"id\": "+ p.id +",\"username\": \""+ p.username +"\",\"password\": \""+p.password+"\",\"email\": \""+ p.email +"\",\"lvl\": "+p.lvl+",\"cash\": "+p.cash+",\"upgradebought\": [";

            for (int x = 0; x<p.upgradebought.length;x++)
            {
                jsonInputString += p.upgradebought[x];
                if(x < p.upgradebought.length-1)
                {
                    jsonInputString += ",";
                }
            }
            jsonInputString += "],\"skinbought\": [";
            for (int x = 0; x<p.skinbought.length;x++)
            {
                jsonInputString += p.skinbought[x];
                if(x < p.skinbought.length-1)
                {
                    jsonInputString += ",";
                }
            }
            jsonInputString += "],\"bestwave\": "+ p.bestwave +"}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Vérifier la réponse de l'API
            int status = conn.getResponseCode();
            System.out.println("Response Code: " + status);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
