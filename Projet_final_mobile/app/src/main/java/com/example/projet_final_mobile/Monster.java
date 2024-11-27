package com.example.projet_final_mobile;

public class Monster {
    public int dammage_min;
    public int dammage_max;
    public int armor;
    public int pv_max;
    public int pv_actuel;
    public int dodgechance;
    public int critchance;
    public String image;

    public Monster(int dammage_min_p,int dammage_max_p, int armor_p,  int pv_max_p,  int dodgechance_p,  int critchance_p,String image_p)
    {
        dammage_min = dammage_min_p;
        dammage_max = dammage_max_p;
        armor = armor_p;
        pv_max = pv_max_p;
        pv_actuel = pv_max;
        dodgechance = dodgechance_p;
        critchance = critchance_p;
        image = image_p;
    }
}


