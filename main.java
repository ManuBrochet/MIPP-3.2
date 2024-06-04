import java.util.LinkedList;
import java.util.Scanner;

public class main
{



    public static void main(String[] args)
    {
        int nbJoueurs=0, i;
        String tempNom;
        LinkedList<Chevalet> ListeJoueurs = new LinkedList<Chevalet>();
        Scanner entree = new Scanner(System.in);
        Chevalet tempJoueur;
        //  on initialise la partie
        while(!(nbJoueurs > 0))
        {
            System.out.println("Combien de joueurs voulez-vous dans la partie ?");
            nbJoueurs = entree.nextInt();
        }
        entree.nextLine();
        for (i=0; i<nbJoueurs; i++)
        {
            System.out.println("Donner le nom du joueur " + i + "\n");
            
            tempNom = entree.nextLine();
            ListeJoueurs.add(new Chevalet(tempNom));
        }
        // on crée le plateau et le sac
        Plateau lePlateau = new Plateau();
        Sac leSac = new Sac();
        boolean finPartie = false;

        // tous les joueurs piochent
        for (i=0; i<nbJoueurs; i++)
        {
            ListeJoueurs.get(i).piocherLettre(leSac);
        }


        //le premier joueur joue le premier coup

        lePlateau.afficher_plateau();

        tempJoueur = ListeJoueurs.get(0);

        System.out.println("\n\nC'est à " + tempJoueur.nom + " de jouer \n");

        lePlateau = tempJoueur.JouerPremierCoup(lePlateau, leSac);

        ListeJoueurs.set(0,tempJoueur);

        System.out.println("\nA présent : ");
        ListeJoueurs.get(0).afficher_chevalet();

        int joueurActuel = 1;


        // les joueur jouent jusqu'à ce qu'il n'y ait plus de lettres dans le sac
        while (!finPartie)
        {
            tempJoueur = ListeJoueurs.get(joueurActuel);

            System.out.println("\n\nC'est à " + tempJoueur.nom + " de jouer \n\n");

            lePlateau.afficher_plateau();

            lePlateau = tempJoueur.JouerUnCoup(lePlateau, leSac);

            ListeJoueurs.set(joueurActuel, tempJoueur);

            System.out.println("\nA présent : ");
            ListeJoueurs.get(joueurActuel).afficher_chevalet();

            if (leSac.nb_lettres_restantes == 0) finPartie = true;

            // si le dernier joueur vient de jouer, on revient au premier joueur
            if (joueurActuel == nbJoueurs -1)
            {
                joueurActuel = 0;
            }
            else joueurActuel++;
        }
        
        // fin de partie
        for (i=0; i<nbJoueurs; i++)
        {
            System.out.println("Le score de " + ListeJoueurs.get(i).nom + "est" +  ListeJoueurs.get(i).score + "\n");
        }
        System.out.println("\n\n\nMerci d'avoir joué !\n\n\n");
    }
}