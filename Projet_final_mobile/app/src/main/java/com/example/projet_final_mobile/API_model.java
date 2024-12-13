package com.example.projet_final_mobile;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class API_model {
    public static List<Monster> GetAllMonstre(){
        List<Monster> _monstres = new ArrayList<>();
        String brute = API_Caller.test();

        try {
            JSONArray jsonArray = new JSONArray(brute);

            // Parcourir chaque élément du tableau JSON
            for (int i = 0; i < jsonArray.length(); i++) {
                // Récupérer chaque élément comme JSONObject
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Extraire les valeurs
                int id = jsonObject.getInt("id");
                int damageMin = jsonObject.getInt("dammage_min");
                int damageMax = jsonObject.getInt("dammage_max");
                int armor = jsonObject.getInt("armor");
                int pvMax = jsonObject.getInt("pv_max");
                int pvActuel = jsonObject.getInt("pv_actuel");
                int dodgeChance = jsonObject.getInt("dodgechance");
                int critChance = jsonObject.getInt("critchance");
                String image = jsonObject.getString("image");

                _monstres.add(new Monster(damageMin,damageMax,armor,pvMax,dodgeChance,critChance,image));
            }
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return _monstres;
    }

    public static User Login(String username, String password){
        User _user = null;
        String brute = API_Caller.connect(username,password);

        try {
            // Récupérer chaque élément comme JSONObject
            JSONObject jsonObject = new JSONObject(brute);

            // Extraire les attributs de l'utilisateur et les affecter aux variables
            String nom = jsonObject.getString("username");
            String email = jsonObject.getString("email");
            int lvl = jsonObject.getInt("lvl");
            int cash = jsonObject.getInt("cash");

            // Récupérer la liste des upgrades et skins
            JSONArray upgradeBoughtArray = jsonObject.getJSONArray("upgradebought");
            JSONArray skinBoughtArray = jsonObject.getJSONArray("skinbought");

            // Convertir les JSONArrays en listes Java
            int[] upgradeBought = new int[upgradeBoughtArray.length()];
            for (int x = 0; x < upgradeBoughtArray.length(); x++) {
                upgradeBought[x] = upgradeBoughtArray.getInt(x);
            }

            int[] skinBought = new int[skinBoughtArray.length()];
            for (int y = 0; y < skinBoughtArray.length(); y++) {
                skinBought[y] = skinBoughtArray.getInt(y);
            }

            int bestwave = jsonObject.getInt("bestwave");

            _user = new User(nom,email,"mdp",lvl,cash,upgradeBought,skinBought,bestwave);

        }
        catch (JSONException e) {
            Log.d("Login",e.getMessage());
        }

        return _user;
    }
}
