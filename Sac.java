import java.util.LinkedList;

public class Sac
{
    public LinkedList<Lettre> lettres_restantes;
    public int nb_lettres_restantes;

    // contructeur, on remplit le sac avec toutes les lettres 
    public Sac()
    {
        LinkedList<Lettre> lettres_restantes_temp = new LinkedList<Lettre>();
        int i;
        for (i=0; i<9; i++)                                                      //il y a 9 "a"
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
            lettres_restantes_temp.add(new Lettre('$', 0));  
        }

        this.lettres_restantes = lettres_restantes_temp;
        this.nb_lettres_restantes = 102;
    }
    
    // affiche les lettres présentes dans le sac
    public String toString()
    {
        int i;
        String str = "Les lettres du sac sont : \n";
        for (i=0; i<this.nb_lettres_restantes; i++)
        {
            str = str + " " + this.lettres_restantes.get(i).toString() + "\n";
        }
        return str;
    }

    //affichage compact du sac pour les tests, en insérant un décalage aux lettres qui valent plus de 10
    //marche avec un sac rempli dans l'ordre
    public void afficher_sac()
    {
        System.out.println();
        System.out.println("Lettres du sac : ");
        for (int i=0; i<47; i++)
        {
            System.out.print(this.lettres_restantes.get(i).symbole);
        }
        System.out.print(" ");
        for (int i=47; i<nb_lettres_restantes - 6; i++)
        {
            System.out.print(this.lettres_restantes.get(i).symbole);
        }
        for (int i=nb_lettres_restantes-6; i<nb_lettres_restantes-2; i++)
        {
            System.out.print(this.lettres_restantes.get(i).symbole + " ");
        }
        for (int i=nb_lettres_restantes-2; i<nb_lettres_restantes; i++)
        {
            System.out.print(this.lettres_restantes.get(i).symbole );
        }

        System.out.println();
        for (int i=0; i<nb_lettres_restantes; i++)
        {
            System.out.print(this.lettres_restantes.get(i).valeur);
        }
        System.out.println();
    }
}