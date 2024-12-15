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

    //Cette fonction sert à modifier les donnée d'un user comme son nombre d'argent,
    // et ses stats
    public static void PutPerso(User p) {
        try {
            //Parametre pour l'envoie de la requete
            URL url = new URL("https://mathf32.pythonanywhere.com/api/Utilisateurs/"+p.id+"/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            //Création du body
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

    //Cette fonction permet de créer un perso dans l'api en passant
    //une requête post à l'api avec un body contenant les information
    //pour créer un compte
    public static String PostPerso(String username, String password) {
        try {
            //Parametre pour l'envoie de la requete à l'api
            URL url = new URL("https://mathf32.pythonanywhere.com/api/Utilisateurs/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            //Body de la requete
            String jsonInputString = "{\"username\": \""+ username +
                    "\",\"password\": \""+password+"\",\"email\": \"\",\"lvl\": 0,\"cash\": 0,\"upgradebought\": null"
                    +",\"skinbought\": null,\"bestwave\": 0}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Vérifier la réponse de l'API
            int status = conn.getResponseCode();
            System.out.println("Response Code: " + status);

            //Lecture de la reponse
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //Fonction qui appel l'Api pour valider le user qui ce connect
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
            Log.d("connect", e.getMessage());
            return null;
        }
    }
    //Cette fonction appelle l'api et doit recevoir la liste de tout les user
    public static String AllUser() {
        try {
            //Parametre de la requete
            URL url = new URL("https://mathf32.pythonanywhere.com/api/Utilisateur");
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

            Log.d("Reponse",response.toString());
            return response.toString();
        } catch (IOException e) {
            Log.d("Erreur",e.getMessage());
            return "Erreur";
        }
    }
}

