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
        int nbJoueurs=4, i;
        String tempNom;
        LinkedList<Chevalet> ListeJoueurs = new LinkedList<Chevalet>();

    
        
        ListeJoueurs.add(new Chevalet("Manu"));
        ListeJoueurs.add(new Chevalet("Ustine"));
        ListeJoueurs.add(new Chevalet("Annie"));
        ListeJoueurs.add(new Chevalet("Didine"));
        Plateau lePlateau = new Plateau();
        Sac leSac = new Sac();
        //System.out.println(leSac.nb_lettres_restantes);
        //System.out.println(leSac.toString());

        lePlateau.afficher_plateau();

        ListeJoueurs.get(0).piocherLettre(leSac);

        System.out.println(ListeJoueurs.get(0).toString());

        //System.out.println(leSac.toString());

        System.out.println(ListeJoueurs.get(0).CompterPoint());
    }
}