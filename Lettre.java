public class Lettre
{
    public char symbole;
    public int valeur;

    // constructeur vide
    public Lettre()
    {
        this.symbole = '_';
        this.valeur = 0;
    } 

    // constructeur
    public Lettre(char symbole, int valeur)
    {
        this.symbole = symbole;
        this.valeur = valeur;
    }

    // affiche la lettre et sa valeur
    public String toString()
    {
        return "La lettre " + this.symbole + " de valeur " + this.valeur;
    }
}