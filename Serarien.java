import java.util.LinkedList;

public class Serarien
{

    // classe-structure
    boolean valide;
    LinkedList<Lettre> nouveauMot;
    String bonus;
    

    // constructeur vide
    public Serarien()
    {
        this.valide = false;
        this.nouveauMot = new LinkedList<Lettre>();
        this.bonus = "";    
    }
}