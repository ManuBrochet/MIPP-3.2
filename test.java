import java.util.LinkedList;


public class test
{
    /**/


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
        //lePlateau.afficher_plateau();

        ListeJoueurs.get(0).piocherLettre(leSac);


        //System.out.println(leSac.toString());

        //System.out.println(ListeJoueurs.get(0).CompterPoint());


        



        lePlateau.afficher_plateau();

        lePlateau = ListeJoueurs.get(0).JouerPremierCoup(lePlateau, leSac);

        lePlateau.afficher_plateau();

        System.out.println(ListeJoueurs.get(0).toString());

        lePlateau = ListeJoueurs.get(0).JouerCoup(lePlateau, leSac);

        lePlateau.afficher_plateau();
    }
}