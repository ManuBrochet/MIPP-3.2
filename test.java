import java.util.LinkedList;


public class test
{
    /*public LinkedList<Chevalet> CreerJoueurs()
    {
        int nbJoueurs, i;
        String tempNom;
        LinkedList<Chevalet> ListeJoueurs;
        java.util.Scanner entree =   new java.util.Scanner(System.in);
        System.out.println("Combien de joueurs voulez-vous dans la partie ?");
        nbJoueurs = entree.nextInt();
        for (i=0; i<nbJoueurs; i++)
        {
            System.out.println("Donner le nom du joueur " + i);
            tempNom = entree.next();
            ListeJoueurs.add(new Chevalet(tempNom));
        }
        return ListeJoueurs;
    }*/


    public static void main(String[] args)
    {
        int nbJoueurs, i;
        String tempNom;
        LinkedList<Chevalet> ListeJoueurs = new LinkedList();
        java.util.Scanner entree =   new java.util.Scanner(System.in);
        System.out.println("Combien de joueurs voulez-vous dans la partie ?");
        nbJoueurs = entree.nextInt();
        for (i=0; i<nbJoueurs; i++)
        {
            System.out.println("Donner le nom du joueur " + i);
            tempNom = entree.next();
            ListeJoueurs.add(new Chevalet(tempNom));
        }
        //LinkedList<Chevalet> ListeJoueurs = this.CreerJoueurs();

        Plateau lePlateau = new Plateau();
        Sac leSac = new Sac();
        
    }
}