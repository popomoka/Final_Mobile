package com.example.projet_final_mobile;

public class Item {
    public int dammage_min;
    public int dammage_max;
    public int armor;
    public int pv;
    public int dodgeChance;
    public int critChance;
    public String image;

    public Item(int dammage_min_p,int dammage_max_p, int armor_p,  int pv_p,  int dodgechance_p,  int critchance_p,String image_p)
    {
        dammage_min = dammage_min_p;
        dammage_max = dammage_max_p;
        armor = armor_p;
        pv = pv_p;
        dodgeChance = dodgechance_p;
        critChance = critchance_p;
        image = image_p;
    }
}
