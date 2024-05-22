public class Plateau
{
    public Lettre[][] lePlateau = new Lettre[15][15];

    // constructeur, en initialisant le tableau avec des cases vides représentées par des _

    public Plateau()
    {
        int i;
        int j;
        for (i=0; i<15; i++)
        {
            for (j=0; j<15; j++)
            {
                this.lePlateau[i][j] = new Lettre();
            }
        }
    }

    public void afficher_plateau()
    {
        int i;
        int j;
        for (i=0; i<15; i++)
        {
            for (j=0; j<15; j++)
            {
                System.out.print(this.lePlateau[i][j].symbole + " ");
            }
            System.out.println();
        }    
        System.out.println("\n\n\n");
    }

}