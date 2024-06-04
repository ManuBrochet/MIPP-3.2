import java.util.LinkedList;


public class test
{
    /**/


    public static void main(String[] args)
    {
        
        LinkedList<Chevalet> ListeJoueurs = new LinkedList<Chevalet>();

        ListeJoueurs.add(new Chevalet("Manu"));
        ListeJoueurs.add(new Chevalet("Didine"));

        ListeJoueurs.get(0).afficher_chevalet();                                    //chevalet vide

        Sac leSac = new Sac();
        System.out.print(leSac.nb_lettres_restantes + " lettres dans le sac.");     //sac plein
        leSac.afficher_sac();

        System.out.println("\n" + ListeJoueurs.get(0) + " pioche.");
        ListeJoueurs.get(0).piocherLettre(leSac);                                   //initialisation d'un chevalet               
        ListeJoueurs.get(0).afficher_chevalet();
        System.out.println(leSac.nb_lettres_restantes + " lettres dans le sac.");

        System.out.println("\nLe chevalet de " + ListeJoueurs.get(0).nom + " vaut " + ListeJoueurs.get(0).CompterPoint() + " points.");                     //Points d'un chevalet


//ChangerLettres

        System.out.println("\nTests changerLettres");
        for (int i=0;i<7;i++)
        {
            ListeJoueurs.get(1).mesLettres[i].symbole = 'a';
            ListeJoueurs.get(1).mesLettres[i].valeur = 1;
        }

        ListeJoueurs.get(1).afficher_chevalet();
        System.out.println(" On essaye de changer les lettres b,a et c");           //on essaye de changer les lettres b, a et c dans un chevalet remplis de a
        try {                                                                       //ne fonctionne pas
            ListeJoueurs.get(1).ChangerLettres("bac", leSac);
        } catch (Exception e) {
            System.out.print("Une de ces lettres n'est pas présente dans votre chevalet. \n ");
        }
        ListeJoueurs.get(1).afficher_chevalet();
        
        System.out.println(" On essaye de changer les lettres a,b et c");           //on essaye de changer les lettres a, b et c dans un chevalet remplis de a
        try {                                                                       //change le premier a puis exception
            ListeJoueurs.get(1).ChangerLettres("abc", leSac);
        } catch (Exception e) {
            System.out.print("Une de ces lettres n'est pas présente dans votre chevalet. \n ");
        }
        ListeJoueurs.get(1).afficher_chevalet();

        System.out.println(" On essaye de changer deux lettres a.");                //on essaye de changer deux lettres a dans un chevalet remplis de a
        try {                                                                       //fonctionne
            ListeJoueurs.get(1).ChangerLettres("aa", leSac);
        } catch (Exception e) {
            System.out.print("Une de ces lettres n'est pas présente dans votre chevalet. \n ");
        }
        ListeJoueurs.get(1).afficher_chevalet();

        
//JouerPremierCoup

        System.out.println("\nJouons ! ");
        Plateau lePlateau = new Plateau();
        lePlateau.afficher_plateau();

        lePlateau = ListeJoueurs.get(0).JouerPremierCoup(lePlateau, leSac);         //Si choix 2 ou 3, fait rejouer. De même si les coordonnées ne sont pas au centre.
        lePlateau.afficher_plateau();                                               //ou si une lettre n'est pas présente.

        System.out.println("Nouveau chevalet du joueur : ");
        ListeJoueurs.get(0).afficher_chevalet();

//Jouer
        boolean finPartie = false;
        while (!finPartie)
        {
            lePlateau = ListeJoueurs.get(1).JouerUnCoup(lePlateau, leSac);              //Choix 1, 2 et 3 fonctionnent
            lePlateau.afficher_plateau();
            ListeJoueurs.get(1).afficher_chevalet();

            lePlateau = ListeJoueurs.get(0).JouerUnCoup(lePlateau, leSac);              //Choix 1, 2 et 3 fonctionnent
            lePlateau.afficher_plateau();
            ListeJoueurs.get(0).afficher_chevalet();

            if (leSac.nb_lettres_restantes == 0) finPartie = true;
        }
        
    }
}