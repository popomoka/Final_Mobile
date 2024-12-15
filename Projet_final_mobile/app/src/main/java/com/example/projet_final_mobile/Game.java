package com.example.projet_final_mobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Game extends AppCompatActivity {
    //tableau qui regi les chances d'avoir un item avec de meilleurs statistiques
    private static double[] probabilities = {0.529, 0.2, 0.1, 0.08, 0.05, 0.023, 0.01, 0.005, 0.002, 0.001};

    //variables
    public Perso perso;
    public Monster monstre;
    boolean combatIsOver;
    User user;

    //vitesse de jeux
    String vitesse;

    //liste
    final List<Item> listeItems = new ArrayList<>();
    final List<Monster> listeMonstre = new ArrayList<>();
    Item[] ItemSelection = new Item[3];

    //pour la loop de combat
    Handler handler = new Handler(Looper.getMainLooper());

    //var de combat
    int round;
    int nbCoup;

    //random
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set les variables
        user = (User)getIntent().getSerializableExtra("USER");
        nbCoup = 0;
        round =0;
        vitesse = "normal";

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_activity);

        //set les parametres pour débuter le jeu
        StartGame();

        //boutton qui commence une ronde
        Button button = (Button)findViewById(R.id.ButtonStart);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                View buttonStart = findViewById(R.id.ButtonStart);
                buttonStart.setVisibility(View.GONE);
                combatIsOver = false;
                View OptionsBoissons = findViewById(R.id.OptionsBoissons);
                OptionsBoissons.setVisibility(View.GONE);

                handler.post(Combat);
            }
        });

        //boutton qui permet de quiter la partie
        Button buttonquit = (Button)findViewById(R.id.Quitter);
        buttonquit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Game.this, HomeActivity.class);
                intent.putExtra("USER", user );
                startActivity(intent);
            }
        });

        //change la vitesse de jeu entre normal et rapide
        Button buttonvitesse = (Button)findViewById(R.id.vitesse);
        buttonvitesse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(vitesse.equals("normal"))
                {
                    vitesse = "rapide";
                    buttonvitesse.setText(R.string.vitesse_rapide);
                }
                else
                {
                    vitesse = "normal";
                    buttonvitesse.setText(R.string.vitesse_normal);
                }
            }
        });

        //bouton qui te redonne une petite quantité de point de vie
        Button buttonsmallpv = (Button)findViewById(R.id.smallpvButton);
        buttonsmallpv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                perso.pv_actuel += 3;
                if (perso.pv_actuel>perso.getPv())
                {
                    perso.pv_actuel=perso.getPv() ;
                }
                UpdateStats();
                HideButtonBoissons();
            }
        });

        //bouton qui te redonne une grande quantité de point de vie
        Button buttonBigPv = (Button)findViewById(R.id.bigpvButton);
        buttonBigPv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                perso.pv_actuel += 10;
                if (perso.pv_actuel>perso.getPv())
                {
                    perso.pv_actuel=perso.getPv() ;
                }
                UpdateStats();
                HideButtonBoissons();
            }
        });

        //boutton qui te rajoute 1% de crit de base
        Button buttoncrit = (Button)findViewById(R.id.critButton);
        buttoncrit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                perso.critChance += 1;
                UpdateStats();
                HideButtonBoissons();
            }
        });

        //boutton de selection du premier item lors des récompences
        ImageView ImageViewItem1 = findViewById(R.id.Item1);
        ImageViewItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view;
                TextView textview;
                if(ItemSelection[0].type.equals("Casque"))
                {
                    perso.casque = ItemSelection[0];
                    textview = findViewById((R.id.casqueText));
                    view = findViewById(R.id.casqueImage);
                }
                else if(ItemSelection[0].type.equals("Plastron"))
                {
                    perso.plastron = ItemSelection[0];
                    textview = findViewById((R.id.plastronText));
                    view = findViewById(R.id.plastronImage);
                }
                else if(ItemSelection[0].type.equals("Jambière"))
                {
                    perso.jambiere = ItemSelection[0];
                    textview = findViewById((R.id.jambiereText));
                    view = findViewById(R.id.jambiereImage);
                }
                else if(ItemSelection[0].type.equals("Bottes"))
                {
                    perso.bottes = ItemSelection[0];
                    textview = findViewById((R.id.bottesText));
                    view = findViewById(R.id.bottesImage);
                }
                else
                {
                    perso.arme = ItemSelection[0];
                    textview = findViewById((R.id.armeText));
                    view = findViewById(R.id.armeImage);
                }
                textview.setText(GetItemTextStat(ItemSelection[0]));
                view.setImageResource(ConversionStringEnDrawableImage(ItemSelection[0].image));
                buttonClickedRecompense();
            }
        });

        //boutton de selection du deuxieme item lors des récompences
        ImageView ImageViewItem2 = findViewById(R.id.Item2);
        ImageViewItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view;
                TextView textview;
                if(ItemSelection[1].type.equals("Casque"))
                {
                    perso.casque = ItemSelection[1];
                    textview = findViewById((R.id.casqueText));
                    view = findViewById(R.id.casqueImage);
                }
                else if(ItemSelection[1].type.equals("Plastron"))
                {
                    perso.plastron = ItemSelection[1];
                    textview = findViewById((R.id.plastronText));
                    view = findViewById(R.id.plastronImage);
                }
                else if(ItemSelection[1].type.equals("Jambière"))
                {
                    perso.jambiere = ItemSelection[1];
                    textview = findViewById((R.id.jambiereText));
                    view = findViewById(R.id.jambiereImage);
                }
                else if(ItemSelection[1].type.equals("Bottes"))
                {
                    perso.bottes = ItemSelection[1];
                    textview = findViewById((R.id.bottesText));
                    view = findViewById(R.id.bottesImage);
                }
                else
                {
                    perso.arme = ItemSelection[1];
                    textview = findViewById((R.id.armeText));
                    view = findViewById(R.id.armeImage);
                }
                textview.setText(GetItemTextStat(ItemSelection[1]));
                view.setImageResource(ConversionStringEnDrawableImage(ItemSelection[1].image));
                buttonClickedRecompense();
            }
        });

        //boutton de selection du troisième item lors des récompences
        ImageView ImageViewItem3 = findViewById(R.id.Item3);
        ImageViewItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view;
                TextView textview;
                if(ItemSelection[2].type.equals("Casque"))
                {
                    perso.casque = ItemSelection[2];
                    textview = findViewById((R.id.casqueText));
                    view = findViewById(R.id.casqueImage);
                }
                else if(ItemSelection[2].type.equals("Plastron"))
                {
                    perso.plastron = ItemSelection[2];
                    textview = findViewById((R.id.plastronText));
                    view = findViewById(R.id.plastronImage);
                }
                else if(ItemSelection[2].type.equals("Jambière"))
                {
                    perso.jambiere = ItemSelection[2];
                    textview = findViewById((R.id.jambiereText));
                    view = findViewById(R.id.jambiereImage);
                }
                else if(ItemSelection[2].type.equals("Bottes"))
                {
                    perso.bottes = ItemSelection[2];
                    textview = findViewById((R.id.bottesText));
                    view = findViewById(R.id.bottesImage);
                }
                else
                {
                    perso.arme = ItemSelection[2];
                    textview = findViewById((R.id.armeText));
                    view = findViewById(R.id.armeImage);
                }

                textview.setText(GetItemTextStat(ItemSelection[2]));
                view.setImageResource(ConversionStringEnDrawableImage(ItemSelection[2].image));
                buttonClickedRecompense();
            }
        });
    }

    //loop de combat
    Runnable Combat = new Runnable() {
        //gere c'est le tour du monstre ou du joueur d'attaquer
        int montour = 1;

        @Override
        public void run() {
            //cache le hitmarqueur si il est affiché
            hideHit();

            //génère deux variables aléatoire pour savoir si notre % de crit ou de dodge est assez grand pour appliquer son effet
            int dodge = rand.nextInt(101);
            int crit = rand.nextInt(101);

            //Attaque
            switch(montour)
            {
                //le joueur attaque
                case 1:
                    //le hitmarqueur
                    TextView dammageText = findViewById(R.id.monstrehit);

                    //dodge hit?
                    if(dodge>=monstre.dodgechance) {
                        //crit hit?
                        if(crit>=perso.getCritChance()){
                            //envoie des dommage au monstre qui varrie entre les dommage min et les dommage max
                            //la couleur est set a rouge (couleur de dégat normal)
                            dammageText.setTextColor(Color.parseColor("#FF0000"));
                            GiveDammage(rand.nextInt((perso.getDammage_max() - perso.getDammage_min()) + 1) + perso.getDammage_min());
                        } else{
                            //envoie des dommage au monstre qui varrie entre les dommage min et les dommage max FOIS 2
                            //la couleur est set a jaune (couleur de dégat critique)
                            dammageText.setTextColor(Color.parseColor("#FFFF00"));
                            GiveDammage(2*(rand.nextInt((perso.getDammage_max() - perso.getDammage_min()) + 1) + perso.getDammage_min()));
                        }
                    }
                    //le monstre dodge
                    else{
                        //couleur de texte bleu pour le dodge
                        dammageText.setTextColor(Color.parseColor("#0000FF"));
                        //affiche dodge
                        GiveDodge();
                    }
                    break;
                //le monstre attaque
                case 0:
                    //hitmarqueur
                    TextView dammagepersoText = findViewById(R.id.persohit);
                    //fonction temporaire pré balancement qui fait en sorte que si le perso et le monstre ont trop d'armure vs les dégats ils ne se frappent pas en boucle sans ce prendre de dégats
                    //si le joueur ce prend 30 coup le monstre le tue
                    if(nbCoup>30)
                    {
                        TakeDammage(10000);
                    }
                    //même logique que pour les attaques du joeuur
                    else if(dodge>=perso.getDodgeChance()) {
                        if(crit>=monstre.critchance){
                            dammagepersoText.setTextColor(Color.parseColor("#FF0000"));
                            TakeDammage(rand.nextInt((monstre.dammage_max - monstre.dammage_min) + 1) + monstre.dammage_min);
                        } else{
                            dammagepersoText.setTextColor(Color.parseColor("#FFFF00"));
                            TakeDammage(2*(rand.nextInt((monstre.dammage_max - monstre.dammage_min) + 1) + monstre.dammage_min));
                        }
                    }
                    else{
                        dammagepersoText.setTextColor(Color.parseColor("#FF0000"));
                        TakeDodge();
                    }
                    break;
            }

            //met a jours les pv
            UpdatePv();

            //rend le tour à l'autre
            if(montour == 1)
                montour = 0;
            else
                montour = 1;

            //variable permet de savoir si le monstre est mort
            if(!combatIsOver)
            {
                //rappel la fonction avec un délai dépendant de la vitesse de jeu
                if(vitesse.equals("normal")){
                    handler.postDelayed(Combat, 1200);
                }else{
                    handler.postDelayed(Combat, 600);
                }
            }
            else
            {
                //fait les mécanique de fin de ronde : ex les récompences
                endround();
            }
        }
    };

    //set les parametres de début de rondes
    void StartRound()
    {
        //genere un nouveau monstre et fait la gestion de quels sont les boutons qui devrait etre visible
        GenerateMonster();
        View buttonStart = findViewById(R.id.ButtonStart);
        View OptionsBoissons = findViewById(R.id.OptionsBoissons);
        OptionsBoissons.setVisibility(View.VISIBLE);
        buttonStart.setVisibility(View.VISIBLE);
    }

    //recevoir des dommage
    void TakeDammage(int ammount)
    {
        //hitmarqueur
        TextView view = findViewById(R.id.persohit);

        //reduit les dégats avec l'armure
        ammount -= perso.getArmor();

        //ne pas oublier de ne pas "heal" le perso si l'armure est plus élevé que les dommage
        if (ammount<0)
        {
            ammount = 0;
        }

        //affichage
        view.setText("-" + ammount);
        view.setVisibility(View.VISIBLE);

        //ajuster les pv
        perso.pv_actuel -= ammount;

        //si le perso meur appeller la fonciton de fin de partie
        if (perso.pv_actuel <=0)
        {
            combatIsOver = true;
            GAMEOVER();
        }

        //mettre a jours l'affichage
        UpdatePv();

        //temp
        nbCoup++;
    }

    //dodge l'attaque et fait l'affichage
    void TakeDodge()
    {
        TextView view = findViewById(R.id.persohit);
        view.setText(R.string.dodge_combat);
        view.setVisibility(View.VISIBLE);
        nbCoup++;
    }

    //envoyer des dommage au monstre
    void GiveDammage(int ammount)
    {
        //hitmarqueur
        TextView view = findViewById(R.id.monstrehit);

        //redire les dégats avec l'Armure
        ammount -= monstre.armor;

        //ne pas heal si armure > que dammage
        if (ammount<0)
        {
            ammount = 0;
        }

        //affichage
        view.setText("-" + ammount);
        view.setVisibility(View.VISIBLE);

        //retirer les pv
        monstre.pv_actuel -= ammount;

        //regarde si le monstre est mort
        if (monstre.pv_actuel <=0)
        {
            combatIsOver = true;
            hideHit();
        }

        //update affichage
        UpdatePv();
    }

    //si le monstre dodge faire l'affichage
    void GiveDodge()
    {
        TextView view = findViewById(R.id.monstrehit);
        view.setText(R.string.dodge_combat);
        view.setVisibility(View.VISIBLE);
    }

    //fin de rounde gestion des récompences
    void endround()
    {
        //temp
        nbCoup=0;

        //ajoute 1 au nombre de ronde gagné
        round ++;

        //retirer le monstre
        ImageView ImageMonstre = findViewById(R.id.ImageMonstre);
        ImageMonstre.setImageResource(R.drawable.vide);

        //génère un item avec la table de chance
        int id1 = GetItemId();
        int id2 = GetItemId();
        int id3 = GetItemId();

        //trouve l'item avec son id généré plus haut
        Item item1 = listeItems.get(id1);
        Item item2 = listeItems.get(id2);
        Item item3 = listeItems.get(id3);

        //met a jour la liste d'itemselection
        ItemSelection[0] = item1;
        ItemSelection[1] = item2;
        ItemSelection[2] = item3;

        //affichage
        ImageView item1Image = findViewById(R.id.Item1);
        ImageView item2Image = findViewById(R.id.Item2);
        ImageView item3Image = findViewById(R.id.Item3);

        TextView item1Text = findViewById(R.id.TextItem1);
        TextView item2Text = findViewById(R.id.TextItem2);
        TextView item3Text = findViewById(R.id.TextItem3);

        item1Image.setImageResource(ConversionStringEnDrawableImage(item1.image));
        item2Image.setImageResource(ConversionStringEnDrawableImage(item2.image));
        item3Image.setImageResource(ConversionStringEnDrawableImage(item3.image));

        item1Text.setText(GetItemTextStat(item1));
        item2Text.setText(GetItemTextStat(item2));
        item3Text.setText(GetItemTextStat(item3));

        //activer les boutons
        View recompences = findViewById(R.id.ChoixRecompenses);
        recompences.setVisibility(View.VISIBLE);
    }

    void buttonClickedRecompense()
    {
        //met a jour le visuel, update les stats avec le novel item et recommence une nouvelle rounde
        View v = findViewById(R.id.ChoixRecompenses);
        View v2 = findViewById(R.id.ButtonStart);
        View v3 = findViewById(R.id.OptionsBoissons);
        v.setVisibility(View.GONE);
        v2.setVisibility(View.VISIBLE);
        v3.setVisibility(View.VISIBLE);
        UpdateStats();
        StartRound();
    }

    //générer un monstre aléatoirement
    void GenerateMonster()
    {
        //genere un id selon la round
        int i = rand.nextInt(round+4);

        //renvoie le dernier si on est endehors du nombre max
        if (i > listeMonstre.size())
        {
            i = listeMonstre.size();
        }

        //met le monstre choisi dans la variable
        monstre = listeMonstre.get(i);
        monstre.pv_actuel = monstre.pv_max;

        //visuel
        ImageView v = findViewById(R.id.ImageMonstre);
        v.setImageResource(ConversionStringEnDrawableImage(monstre.image));
        UpdatePv();
    }

    //début de partie set les variables
    void StartGame()
    {
        //vas chercher la liste des monstres dans l'api
        Thread t = new Thread(() -> {
            List<Monster> m = API_model.GetAllMonstre();
            synchronized (listeMonstre){
                listeMonstre.clear();
                listeMonstre.addAll(m);
            }
        });
        t.start();
        try {
            t.join(); // Attendre que le thread termine son exécution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //vas chercher la liste des items dans l'api
        Thread t2 = new Thread(() -> {
            List<Item> i = API_model.GetAllItems();
            synchronized (listeItems){
                listeItems.clear();
                listeItems.addAll(i);
            }
        });
        t2.start();
        try {
            t2.join(); // Attendre que le thread termine son exécution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // creer un perso vide + les upgrades acheté hors partie
        perso = new Perso(user.upgradebought[0], user.upgradebought[1]+1, user.upgradebought[2], user.upgradebought[3] + 20, user.upgradebought[4], user.upgradebought[5], null, null, null, null, null);

        //visuel
        UpdateStats();

        //commencer la rounde
        StartRound();
    }

    //le joueur est mort
    void GAMEOVER()
    {
        //envoie à l'activité recompence
        Intent intent = new Intent(Game.this, RecomenceActivity.class);
        intent.putExtra("USER", user);
        intent.putExtra("ROUND", round);
        startActivity(intent);
    }

    //fait la gestion des affichages des bouttons
    void HideButtonBoissons()
    {
        View buttons = findViewById(R.id.OptionsBoissons);
        buttons.setVisibility(View.GONE);

    }

    //refresh l'affichage en fonction des stats du joueur
    void UpdateStats()
    {
        if (perso.pv_actuel>perso.getPv())
        {
            perso.pv_actuel=perso.getPv() ;
        }

        int dmgmin = perso.getDammage_min();
        int dmgmax = perso.getDammage_max();
        TextView text_dammage_stats = findViewById(R.id.text_dammage_stats);
        text_dammage_stats.setText(getString(R.string.Dommage) + " : " + dmgmin + "-" + dmgmax);

        int pvmax = perso.getPv();
        TextView text_pv_stats = findViewById(R.id.text_pv_stats);
        text_pv_stats.setText(getString(R.string.Pv) + " : " + perso.pv_actuel + "/" + pvmax);

        int armor = perso.getArmor();
        TextView text_armor_stats = findViewById(R.id.text_armor_stats);
        text_armor_stats.setText(getString(R.string.Armure) +" : " + armor);

        int critChance = perso.getCritChance();
        TextView text_crit_stats = findViewById(R.id.text_crit_stats);
        text_crit_stats.setText( getString(R.string.Crit) + " : " + critChance + "%");

        int dodgeChance = perso.getDodgeChance();
        TextView text_dodge_stats = findViewById(R.id.text_dodge_stats);
        text_dodge_stats.setText( getString(R.string.Dodge) + " : " + dodgeChance + "%");

    }

    //met a jour les points de vie
    void UpdatePv()
    {
        int pvmax = perso.getPv();
        int monsterPvMax = monstre.pv_max;
        TextView text_pv_stats = findViewById(R.id.text_pv_stats);
        TextView text_pv_stats_monstre = findViewById(R.id.monstrePv);

        text_pv_stats.setText(getString(R.string.Pv) + " : " + perso.pv_actuel + "/" + pvmax);
        text_pv_stats_monstre.setText(monstre.pv_actuel + "/" + monsterPvMax);
    }

    //cache les hitmarqueur
    void hideHit()
    {
        TextView monshit = findViewById(R.id.monstrehit);
        TextView pershit = findViewById(R.id.persohit);
        monshit.setVisibility(View.GONE);
        pershit.setVisibility(View.GONE);
    }

    //convertie un string en id pour le dossier drawable
    int ConversionStringEnDrawableImage(String image)
    {
        switch(image) {
            // Casque (1 à 10)
            case "casque_1":
                return R.drawable.casque_1;
            case "casque_2":
                return R.drawable.casque_2;
            case "casque_3":
                return R.drawable.casque_3;
            case "casque_4":
                return R.drawable.casque_4;
            case "casque_5":
                return R.drawable.casque_5;
            case "casque_6":
                return R.drawable.casque_6;
            case "casque_7":
                return R.drawable.casque_7;
            case "casque_8":
                return R.drawable.casque_8;
            case "casque_9":
                return R.drawable.casque_9;
            case "casque_10":
                return R.drawable.casque_10;

            // Jambes (1 à 10)
            case "jamb_1":
                return R.drawable.jamb_1;
            case "jamb_2":
                return R.drawable.jamb_2;
            case "jamb_3":
                return R.drawable.jamb_3;
            case "jamb_4":
                return R.drawable.jamb_4;
            case "jamb_5":
                return R.drawable.jamb_5;
            case "jamb_6":
                return R.drawable.jamb_6;
            case "jamb_7":
                return R.drawable.jamb_7;
            case "jamb_8":
                return R.drawable.jamb_8;
            case "jamb_9":
                return R.drawable.jamb_9;
            case "jamb_10":
                return R.drawable.jamb_10;

            // plastron (1 à 10)
            case "plastron_1":
                return R.drawable.plastron_1;
            case "plastron_2":
                return R.drawable.plastron_2;
            case "plastron_3":
                return R.drawable.plastron_3;
            case "plastron_4":
                return R.drawable.plastron_4;
            case "plastron_5":
                return R.drawable.plastron_5;
            case "plastron_6":
                return R.drawable.plastron_6;
            case "plastron_7":
                return R.drawable.plastron_7;
            case "plastron_8":
                return R.drawable.plastron_8;
            case "plastron_9":
                return R.drawable.plastron_9;
            case "plastron_10":
                return R.drawable.plastron_10;

            // Bottes (1 à 10)
            case "bottes_1":
                return R.drawable.bottes_1;
            case "bottes_2":
                return R.drawable.bottes_2;
            case "bottes_3":
                return R.drawable.bottes_3;
            case "bottes_4":
                return R.drawable.bottes_4;
            case "bottes_5":
                return R.drawable.bottes_5;
            case "bottes_6":
                return R.drawable.bottes_6;
            case "bottes_7":
                return R.drawable.bottes_7;
            case "bottes_8":
                return R.drawable.bottes_8;
            case "bottes_9":
                return R.drawable.bottes_9;
            case "bottes_10":
                return R.drawable.bottes_10;

            // epee (1 à 10)
            case "epee1":
                return R.drawable.epee1;
            case "epee2":
                return R.drawable.epee2;
            case "epee3":
                return R.drawable.epee3;
            case "epee4":
                return R.drawable.epee4;
            case "epee5":
                return R.drawable.epee5;
            case "epee6":
                return R.drawable.epee6;
            case "epee7":
                return R.drawable.epee7;
            case "epee8":
                return R.drawable.epee8;
            case "epee9":
                return R.drawable.epee9;
            case "epee10":
                return R.drawable.epee10;

            // Blobs
            case "blue_blob":
                return R.drawable.blue_blob;
            case "green_blob":
                return R.drawable.green_blob;
            case "pink_blob":
                return R.drawable.pink_blob;
            case "purple_blob":
                return R.drawable.purple_blob;
            case "red_blob":
                return R.drawable.red_blob;
            case "white_blob":
                return R.drawable.white_blob;
            case "yellow_blob":
                return R.drawable.yellow_blob;

// Cubes
            case "blue_cube":
                return R.drawable.blue_cube;
            case "green_cube":
                return R.drawable.green_cube;
            case "pink_cube":
                return R.drawable.pink_cube;
            case "purple_cube":
                return R.drawable.purple_cube;
            case "red_cube":
                return R.drawable.red_cube;
            case "white_cube":
                return R.drawable.white_cube;
            case "yellow_cube":
                return R.drawable.yellow_cube;

// Eggmen
            case "blue_eggman":
                return R.drawable.blue_eggman;
            case "green_eggman":
                return R.drawable.green_eggman;
            case "pink_eggman":
                return R.drawable.pink_eggman;
            case "purple_eggman":
                return R.drawable.purple_eggman;
            case "red_eggman":
                return R.drawable.red_eggman;
            case "white_eggman":
                return R.drawable.white_eggman;
            case "yellow_eggman":
                return R.drawable.yellow_eggman;

// Gluttons
            case "blue_glutton":
                return R.drawable.blue_glutton;
            case "green_glutton":
                return R.drawable.green_glutton;
            case "pink_glutton":
                return R.drawable.pink_glutton;
            case "purple_glutton":
                return R.drawable.purple_glutton;
            case "red_glutton":
                return R.drawable.red_glutton;
            case "white_glutton":
                return R.drawable.white_glutton;
            case "yellow_glutton":
                return R.drawable.yellow_glutton;


            default:
                return R.drawable.vide; // Image par défaut si aucun cas ne correspond
        }
    }

    //retourne une longe chaine de texte pour les statistique d'un item
    String GetItemTextStat(Item item)
    {
        return "dmg : " + item.dammage_min + "-" + item.dammage_max + "\n" + "arm : " + item.armor + "\n" + "pv : " +item.pv + "\n" + "crit : " + item.critChance+ "\n" + "ddg : " + item.dodgeChance;
    }

    //fonction qui genere un item selon la table de chance
    public static int GetItemId() {
        Random rand = new Random();

        //générer un nombre aléatoire entre 0 et 1
        double randomValue = rand.nextDouble();

        //calculer lequel des objets doit être choisi en fonction de la probabilité
        double cumulativeProbability = 0.0;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return rand.nextInt(5)+i*5+1;
            }
        }
        //retourne le dernier objet si quelque chose ne va pas
        return rand.nextInt(5)+1;
    }

}