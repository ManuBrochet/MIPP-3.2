import java.util.LinkedList;

public class Sac
{
    public LinkedList<Lettre> lettres_restantes;
    public int nb_lettres_restantes;

    public void Sac()
    {
        LinkedList<Lettre> lettres_restantes_temp = new LinkedList<Lettre>();
        int i;
        for (i=0; i<9; i++)
        {
            lettres_restantes_temp.add(new Lettre('a', 1));
        }
        for (i=0; i<2; i++)
        {
            lettres_restantes_temp.add(new Lettre('b', 3));
        }
        for (i=0; i<2; i++)
        {
            lettres_restantes_temp.add(new Lettre('c', 3));
        }
        for (i=0; i<3; i++)
        {
            lettres_restantes_temp.add(new Lettre('d', 2));
        }
        for (i=0; i<15; i++)
        {
            lettres_restantes_temp.add(new Lettre('e', 1));
        }
        for (i=0; i<2; i++)
        {
            lettres_restantes_temp.add(new Lettre('f', 4));  
        }
        for (i=0; i<2; i++)
        {
            lettres_restantes_temp.add(new Lettre('g', 2));  
        }
        for (i=0; i<2; i++)
        {
            lettres_restantes_temp.add(new Lettre('h', 4));  
        }
        for (i=0; i<8; i++)
        {
            lettres_restantes_temp.add(new Lettre('i', 1));  
        }
        for (i=0; i<1; i++)
        {
            lettres_restantes_temp.add(new Lettre('j', 8));  
        }
        for (i=0; i<1; i++)
        {
            lettres_restantes_temp.add(new Lettre('k', 10));  
        }
        for (i=0; i<5; i++)
        {
            lettres_restantes_temp.add(new Lettre('l', 1));  
        }
        for (i=0; i<3; i++)
        {
            lettres_restantes_temp.add(new Lettre('m', 2));  
        }
        for (i=0; i<6; i++)
        {
            lettres_restantes_temp.add(new Lettre('n', 1));  
        }
        for (i=0; i<6; i++)
        {
            lettres_restantes_temp.add(new Lettre('o', 1));  
        }
        for (i=0; i<2; i++)
        {
            lettres_restantes_temp.add(new Lettre('p', 3));  
        }
        for (i=0; i<1; i++)
        {
            lettres_restantes_temp.add(new Lettre('q', 8));  
        }
        for (i=0; i<6; i++)
        {
            lettres_restantes_temp.add(new Lettre('r', 1));  
        }
        for (i=0; i<6; i++)
        {
            lettres_restantes_temp.add(new Lettre('s', 1));  
        }
        for (i=0; i<6; i++)
        {
            lettres_restantes_temp.add(new Lettre('t', 1));  
        }
        for (i=0; i<6; i++)
        {
            lettres_restantes_temp.add(new Lettre('u', 1));  
        }
        for (i=0; i<2; i++)
        {
            lettres_restantes_temp.add(new Lettre('v', 4));  
        }
        for (i=0; i<1; i++)
        {
            lettres_restantes_temp.add(new Lettre('w', 10));  
        }
        for (i=0; i<1; i++)
        {
            lettres_restantes_temp.add(new Lettre('x', 10));  
        }
        for (i=0; i<1; i++)
        {
            lettres_restantes_temp.add(new Lettre('y', 10));  
        }
        for (i=0; i<1; i++)
        {
            lettres_restantes_temp.add(new Lettre('z', 10));  
        }
        for (i=0; i<2; i++)
        {
            lettres_restantes_temp.add(new Lettre(' ', 0));  
        }

        this.lettres_restantes = lettres_restantes_temp;
        this.nb_lettres_restantes = 102;
    }
    
    public String toString()
    {
        int i;
        String out = "Les lettres du sac sont : ";
        for (i=0; i<this.nb_lettres_restantes; i++)
        {
            out = out + " " + this.lettres_restantes.get(i).toString();
        }
        return out;
    }
}