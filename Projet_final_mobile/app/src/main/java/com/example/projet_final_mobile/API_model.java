package com.example.projet_final_mobile;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class API_model {

    //Transforme liste de monstre retourné par l'api en liste de monstre Java
    public static List<Monster> GetAllMonstre(){
        List<Monster> _monstres = new ArrayList<>();
        String brute = ApiCall_Monstre.AllMonstre();

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

    //Transforme la liste d'item Json en Java
    public static List<Item> GetAllItems(){
        List<Item> _items = new ArrayList<>();
        String brute = ApiCall_Item.GetAllItems();

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
                int pvMax = jsonObject.getInt("pv");
                int dodgeChance = jsonObject.getInt("dodgeChance");
                int critChance = jsonObject.getInt("critChance");
                String image = jsonObject.getString("image");
                String type = jsonObject.getString("type");


                _items.add(new Item(damageMin,damageMax,armor,pvMax,dodgeChance,critChance,image,type));
            }
        }
        catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return _items;
    }

    //Cette fonction sert à transformer un user Json retourné par
    // l'api en User Java
    public static User Login(String username, String password){
        User _user = null;
        String brute = ApiCall_Perso.connect(username,password);

        try {
            // Récupérer chaque élément comme JSONObject
            JSONObject jsonObject = new JSONObject(brute);

            // Extraire les attributs de l'utilisateur et les affecter aux variables
            String nom = jsonObject.getString("username");
            String email = jsonObject.getString("email");
            int id = jsonObject.getInt("id");
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

            _user = new User(id,nom,email,"",lvl,cash,upgradeBought,skinBought,bestwave);

        }
        catch (JSONException e) {
            Log.d("Login",e.getMessage());
        }

        return _user;
    }


    //Cette fonction appel postPerso qui créer le User dans l'api
    //ensuite avec le string de retour elle créer le nouveau user en java
    //et le renvoie
    public static User Signin(String username, String password){
        User _user = null;
        String brute = ApiCall_Perso.PostPerso(username,password);

        try {
            // Récupérer chaque élément comme JSONObject
            JSONObject jsonObject = new JSONObject(brute);

            // Extraire les attributs de l'utilisateur et les affecter aux variables
            String nom = jsonObject.getString("username");
            String email = jsonObject.getString("email");
            int id = jsonObject.getInt("id");
            int lvl = jsonObject.getInt("lvl");
            int cash = jsonObject.getInt("cash");

            int bestwave = jsonObject.getInt("bestwave");

            _user = new User(id,nom,email,"",lvl,cash,new int[]{0, 0, 0, 0, 0, 0}, new int[]{0},bestwave);

        }
        catch (JSONException e) {
            Log.d("Login",e.getMessage());
            return null;
        }
        return _user;
    }

    //Cette fonction sert à transformer la liste de users Json retourné par
    // l'api en liste de user Java
    public static List<User> GetAllUser(){
        List<User> _users = null;
        String brute = ApiCall_Perso.AllUser();

        try {
            JSONArray jsonArray = new JSONArray(brute);

            // Parcourir chaque élément du tableau JSON
            for (int i = 0; i < jsonArray.length(); i++) {
                // Récupérer chaque élément comme JSONObject
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Extraire les attributs de l'utilisateur et les affecter aux variables
                String nom = jsonObject.getString("username");
                String email = jsonObject.getString("email");
                int id = jsonObject.getInt("id");
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

                //Ajoute les users dans le tableau
                _users.add(new User(id, nom, email, "", lvl, cash, upgradeBought, skinBought, bestwave));
            }

        }
        catch (JSONException e) {
            Log.d("Login",e.getMessage());
        }

        return _users;
    }
}
