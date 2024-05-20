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
        int nbJoueurs=3, i;
        String tempNom;
        LinkedList<Chevalet> ListeJoueurs = new LinkedList<Chevalet>();

    
        
        ListeJoueurs.add(new Chevalet("Manu"));
        ListeJoueurs.add(new Chevalet("Ustine"));
        ListeJoueurs.add(new Chevalet("Annie"));
        Plateau lePlateau = new Plateau();
        Sac leSac = new Sac();
        leSac.toString();

        ListeJoueurs.get(0).piocherLettre(leSac, 7);

        System.out.println(ListeJoueurs.get(0).toString());
    }
}