import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Chevalet
{
    public Lettre[] mesLettres = new Lettre[7];
    public String nom;
    public int score;

    public Chevalet(String nom)
    {
        int i;

        for (i=0; i<7;i++)
        {
            this.mesLettres[i] = new Lettre('%', 0);
        }

        this.nom = nom;
        this.score = 0;
    }

    public boolean piocherLettre(Sac leSac)
    {

        int i, ind;

        for (i=0; i<7; i++)
        {
            if ((this.mesLettres[i].symbole == '%') && (leSac.nb_lettres_restantes > 0))
            {
                ind = (int)(Math.random() * (leSac.nb_lettres_restantes-1));
                this.mesLettres[i] = leSac.lettres_restantes.get(ind);
                leSac.lettres_restantes.remove(ind);
                leSac.nb_lettres_restantes--;
            } 
        }
        if (leSac.nb_lettres_restantes > 0) {return true;}
        else {return false;}
    }

    public int CompterPoint()
    {
        int sum=0, i;

        for (i=0; i<7; i++)
        {
            sum += this.mesLettres[i].valeur;
        }
        return sum;
    }


    //Cette fonction vérifie que le mot ne se place pas sur une lettre déjà placée sur le plateau, et que le mot est bien connecté à au moins une autre lettre
    public boolean VerifNeChevauchepasEtConnecte(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau)
    {
        boolean res = true, connecte = false;
        int i, j=1;
        if ((x + dx < 0) || (x+dx > 14) || (y+dy < 0) || (y+dy>14))
            {
                res = false;
            }
        else
        {
            for (i=0; i<leMot.size(); i++)
            {
                if (lePlateau.lePlateau[x+i*dx][y+i*dy].symbole != '%')
                {
                    res = false;
                }
                if ((lePlateau.lePlateau[x+i*dx+1][y+i*dy].symbole != '%') && (lePlateau.lePlateau[x+i*dx-1][y+i*dy].symbole != '%') && (lePlateau.lePlateau[x+i*dx][y+i*dy+1].symbole != '%') && (lePlateau.lePlateau[x+i*dx][y+i*dy-1].symbole != '%'))
                {
                    connecte = true;
                }
            }
        }
        return (res && connecte);
    }

    public int CompterPointCoup(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau)
    {
        int score=0, j=1, i;
        for (i=0; i<leMot.size(); i++)
        {
            while((lePlateau.lePlateau[x+i*dx+j][y+i*dy].symbole != '%') && (x+i*dx+j<14))
            {
                score += lePlateau.lePlateau[x+i*dx+j][y+i*dy].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx-j][y+i*dy].symbole != '%') && (x+i*dx-j>0))
            {
                score += lePlateau.lePlateau[x+i*dx-j][y+i*dy].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx][y+i*dy+j].symbole != '%') && (y+i*dy+j<14))
            {
                score += lePlateau.lePlateau[x+i*dx][y+i*dy+j].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx][y+i*dy-j].symbole != '%') && (y+i*dy-j>0))
            {
                score += lePlateau.lePlateau[x+i*dx][y+i*dy-j].valeur;
                j++;
            }
        }
        return score;
    }

    //vérifie que le mot placé est bien valide
    public boolean VerifMotPlace(LinkedList<Lettre> leMot, char direction, int x, int y, Plateau lePlateau)
    {
        boolean res;
        int dx=0, dy=0;

        if (direction == 'N')
        {
            dx = 0;
            dy = -1;
        }
        if (direction == 'S')
        {
            dx = 0;
            dy = 1;
        }
        if (direction == 'W')
        {
            dx = -1;
            dy = 0;
        }
        if (direction == 'E')
        {
            dx = 0;
            dy = 1;
        }
        res = VerifNeChevauchepasEtConnecte(leMot, dx, dy, x, y, lePlateau);
        if (res)
        {
            this.score += CompterPointCoup(leMot, dx, dy, x, y, lePlateau);
        }
        return res;
    }


    //place le mot sur le tableau en appelant les fonctions pour vérifier que le mot est correcte et compte les points.
    public int PlacerMot(LinkedList<Lettre> leMot, char direction, int x, int y, Plateau lePlateau) throws MotIncorrect
    {
        int i;
        if (!VerifMotPlace(leMot, direction, x, y, lePlateau))
        {
            throw new MotIncorrect("Le mot que vous avez entré ne peut pas être placé ici.");
        }
        else
        {
            if (direction == 'N')
            {
                
                for (i=0; i<leMot.size(); i++)
                {
                    lePlateau.lePlateau[x][y-i] = leMot.get(i);
                }
                
            }
            if (direction == 'S')
            {
                
                for (i=0; i<leMot.size(); i++)
                {
                    lePlateau.lePlateau[x][y+i] = leMot.get(i);
                }
                
            }
            if (direction == 'W')
            {
                
                for (i=0; i<leMot.size(); i++)
                {
                    lePlateau.lePlateau[x-i][y] = leMot.get(i);
                }
                
            }
            if (direction == 'E')
            {
                
                for (i=0; i<leMot.size(); i++)
                {
                    lePlateau.lePlateau[x+i][y] = leMot.get(i);
                }
                
            }
            return leMot.size();
        }
        
    }

    public boolean LettrePresente(char laLettre)
    {
        int i;
        boolean res = true;

        for (i=0; i<7; i++)
        {
            if (this.mesLettres[i].symbole == laLettre)
            {
                res = false;
            }
        }
        return res;
    }

    public ChoixJouer JouerUnCoup()
    {
        System.out.println(this.toString());
        ChoixJouer leChoix = new ChoixJouer();
        java.util.Scanner entree =   new java.util.Scanner(System.in);
        System.out.println("Voulez-vous jouer un mot (1) \n Changer des lettres (2) \n Passer votre tour (3) ");
        leChoix.choix = entree.nextInt();
        if (leChoix.choix == 1)
        {
            System.out.println("Donner le mot que vous voulez placer");
            leChoix.mot = entree.nextLine();
            System.out.println("Donner la coordonnées x");
            leChoix.x = entree.nextInt();
            System.out.println("Donner la coordonnées y");
            leChoix.y = entree.nextInt();
            while((leChoix.direction!='N') && (leChoix.direction!='S') && (leChoix.direction!='E') && (leChoix.direction!='W'))
            {
                System.out.println("Donner la direction : N, S, E, W");
                String str = entree.nextLine();
                leChoix.direction = str.charAt(0);
            }
        }
        else if (leChoix.choix == 2)
        {
            System.out.println("Donner les letres que vous voulez changer (sans espaces)");
            leChoix.mot = entree.nextLine();
        }
        return leChoix;
    }

    public void ChangerLettres(String LettresAChanger, Sac leSac) throws LettreNonPresente
    {
        int i,j;
        boolean resteLettres;

        for (i=0; i<LettresAChanger.length(); i++)
        {
            if (!LettrePresente(LettresAChanger.charAt(i)))
            {
                throw new LettreNonPresente("La lettre" + LettresAChanger.charAt(i) + " n'est pas dans votre chevalet.");
            }
            else
            {
                for (j=0; j<7; j++)
                {
                    if (this.mesLettres[j].symbole == LettresAChanger.charAt(i))
                    {
                        this.mesLettres[j].symbole = '%';
                        this.mesLettres[j].valeur = 0;
                        break;
                    }
                }
            }
        }
        resteLettres = piocherLettre(leSac);
    }

    public String toString()
    {
        int i;
        String out = "Les lettres du chevalet sont : ";
        for (i=0; i<7; i++)
        {
            out = out + " " + this.mesLettres[i].toString();
        }
        return out;
    }
}