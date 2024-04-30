public class Plateau
{
    public Lettre[][] lePlateau = new Lettre[15][15];

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

}