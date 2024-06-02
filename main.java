import java.util.LinkedList;
import java.util.Scanner;

public class main
{

    public LinkedList<Chevalet> CreerJoueurs()
    {
        int nbJoueurs, i;
        String tempNom;
        LinkedList<Chevalet> ListeJoueurs = new LinkedList<Chevalet>();
        Scanner entree = new Scanner(System.in);
        System.out.println("Combien de joueurs voulez-vous dans la partie ?");
        nbJoueurs = entree.nextInt();
        for (i=0; i<nbJoueurs; i++)
        {
            System.out.println("Donner le nom du joueur " + i + "\n");
            entree.nextLine();
            tempNom = entree.nextLine();
            ListeJoueurs.add(new Chevalet(tempNom));
        }
        return ListeJoueurs;
    }



    public static void main(String[] args)
    {
        
        //LinkedList<Chevalet> ListeJoueurs = this.CreerJoueurs();

        Plateau lePlateau = new Plateau();
        Sac leSac = new Sac();
        
    }
}