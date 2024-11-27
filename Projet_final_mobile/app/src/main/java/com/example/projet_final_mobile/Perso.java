package com.example.projet_final_mobile;

public class Perso {
    public int dammage_min;
    public int dammage_max;
    public int armor;
    public int pv_max;
    public int pv_actuel;
    public int dodgeChance;
    public int critChance;
    public Item casque;
    public Item plastron;
    public Item jambiere;
    public Item bottes;
    public Item arme1;
    public Item arme2;

    public Perso(int dammage_min_p,int dammage_max_p, int armor_p,  int pv_p,  int dodgechance_p,  int critchance_p,Item casque_p,Item plastron_p,Item jambiere_p,Item bottes_p,Item arme1_p,Item arme2_p)
    {
        dammage_min = dammage_min_p;
        dammage_max = dammage_max_p;
        armor = armor_p;
        pv_max = pv_p;
        dodgeChance = dodgechance_p;
        critChance = critchance_p;
        casque = casque_p;
        plastron = plastron_p;
        jambiere = jambiere_p;
        bottes = bottes_p;
        arme1 = arme1_p;
        arme2 = arme2_p;

        pv_actuel = pv_max;
    }

    public int getDammage_min() {
        int retour = dammage_min;

        if(casque != null)
        {
            retour += casque.dammage_min;
        }

        if(plastron != null)
        {
            retour += plastron.dammage_min;
        }

        if(jambiere != null)
        {
            retour += jambiere.dammage_min;
        }

        if(bottes != null)
        {
            retour += bottes.dammage_min;
        }

        if(arme1 != null)
        {
            retour += arme1.dammage_min;
        }

        if(arme2 != null)
        {
            retour += arme2.dammage_min;
        }

        return retour;
    }

    public int getDammage_max() {
        int retour = dammage_max;

        if(casque != null)
        {
            retour += casque.dammage_max;
        }

        if(plastron != null)
        {
            retour += plastron.dammage_max;
        }

        if(jambiere != null)
        {
            retour += jambiere.dammage_max;
        }

        if(bottes != null)
        {
            retour += bottes.dammage_max;
        }

        if(arme1 != null)
        {
            retour += arme1.dammage_max;
        }

        if(arme2 != null)
        {
            retour += arme2.dammage_max;
        }

        return retour;
    }

    public int getArmor() {
        int retour = armor;

        if(casque != null)
        {
            retour += casque.armor;
        }

        if(plastron != null)
        {
            retour += plastron.armor;
        }

        if(jambiere != null)
        {
            retour += jambiere.armor;
        }

        if(bottes != null)
        {
            retour += bottes.armor;
        }

        if(arme1 != null)
        {
            retour += arme1.armor;
        }

        if(arme2 != null)
        {
            retour += arme2.armor;
        }

        return retour;
    }

    public int getPv() {
        int retour = pv_max;

        if(casque != null)
        {
            retour += casque.pv;
        }

        if(plastron != null)
        {
            retour += plastron.pv;
        }

        if(jambiere != null)
        {
            retour += jambiere.pv;
        }

        if(bottes != null)
        {
            retour += bottes.pv;
        }

        if(arme1 != null)
        {
            retour += arme1.pv;
        }

        if(arme2 != null)
        {
            retour += arme2.pv;
        }

        return retour;
    }

    public int getDodgeChance() {
        int retour = dodgeChance;

        if(casque != null)
        {
            retour += casque.dodgeChance;
        }

        if(plastron != null)
        {
            retour += plastron.dodgeChance;
        }

        if(jambiere != null)
        {
            retour += jambiere.dodgeChance;
        }

        if(bottes != null)
        {
            retour += bottes.dodgeChance;
        }

        if(arme1 != null)
        {
            retour += arme1.dodgeChance;
        }

        if(arme2 != null)
        {
            retour += arme2.dodgeChance;
        }

        return retour;
    }

    public int getCritChance() {
        int retour = critChance;

        if(casque != null)
        {
            retour += casque.critChance;
        }

        if(plastron != null)
        {
            retour += plastron.critChance;
        }

        if(jambiere != null)
        {
            retour += jambiere.critChance;
        }

        if(bottes != null)
        {
            retour += bottes.critChance;
        }

        if(arme1 != null)
        {
            retour += arme1.critChance;
        }

        if(arme2 != null)
        {
            retour += arme2.critChance;
        }

        return retour;
    }

}