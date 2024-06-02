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
        System.out.println();
        System.out.println("   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15");
        System.out.println("_______________________________________________________________");
        System.out.println();
        for (i=1; i<16; i++)
        {
            System.out.print(i+ "| ");
            for (j=0; j<15; j++)
            {
                System.out.print(this.lePlateau[j][i-1].symbole + " | ");
            }
            System.out.println();
            System.out.println("_______________________________________________________________");
            System.out.println();
        }    
        System.out.println("\n\n\n");
    }

}