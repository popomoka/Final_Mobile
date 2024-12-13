package com.example.projet_final_mobile;
import java.io.Serializable;

public class User implements Serializable {
    String username;
    String email;
    String password;
    int lvl;
    int cash;
    int[] upgradebought;
    int[] skinbought;
    int bestwave;


    public User(String username_p,String email_p,String password_p,int lvl_p,int cash_p,int[] upgradebought_p,int[] skinbought_p,int bestwave_p)
    {
        username = username_p;
        email = email_p;
        password = password_p;
        lvl = lvl_p;
        cash = cash_p;
        upgradebought =upgradebought_p;
        skinbought = skinbought_p;
        bestwave = bestwave_p;
    }

    public User()
    {

    }
}
