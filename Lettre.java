public class Lettre
{
    public char symbole;
    public int valeur;

    public Lettre()
    {
        this.symbole = '%';
        this.valeur = 0;
    } 

    public Lettre(char symbole, int valeur)
    {
        this.symbole = symbole;
        this.valeur = valeur;
    }

    public String toString()
    {
        return "C'est la lettre " + this.symbole + " de valeur " + this.valeur;
    }
}