import java.util.LinkedList;
import java.util.Random;

public class Chevalet
{
    public Lettre[] mesLettres = new Lettre[7];
    public String nom;

    public Chevalet(String nom)
    {
        int i;

        for (i=0; i<7;i++)
        {
            this.mesLettres[i] = new Lettre('%', 0);
        }

        this.nom = nom;
    }

    public void piocherLettre(Sac leSac, int nb_lettres)
    {
        if (nb_lettres > leSac.nb_lettres_restantes)
        {
            nb_lettres = leSac.nb_lettres_restantes;
        }

        int i, ind;

        for (i=0; i<7; i++)
        {
            if (this.mesLettres[i].symbole == '%')
            {
                ind = (int)(Math.random() * (leSac.nb_lettres_restantes-1));
                this.mesLettres[i] = leSac.lettres_restantes.get(ind);
                leSac.lettres_restantes.remove(ind);
                leSac.nb_lettres_restantes--;
            } 
        }
    }

    public int CompterPoint()
    {
        int sum=0, i;

        for (i=0; i<7; i++)
        {
            sum += this.mesLettres[i].valeur;
        }
        return(sum);
    }

    public boolean VerifMotPlace(LinkedList<Lettre> leMot, char direction, int x, int y, Plateau lePlateau)
    {
        boolean res = true, connecte = false;
        int i;

        if (direction == 'N')
        {
            if (y-leMot.size() < 0)
            {
                res = false;
            }
            else
            {
                for (i=0; i<leMot.size(); i++)
                {
                    if ((lePlateau.lePlateau[x][y-i].symbole != '%'))
                    {
                        res = false;
                    }
                }
            }
        }
        if (direction == 'S')
        {
            if (y+leMot.size() > 14)
            {
                res = false;
            }
            else
            {
                for (i=0; i<leMot.size(); i++)
                {
                    if ((lePlateau.lePlateau[x][y+i].symbole != '%'))
                    {
                        res = false;
                    }
                }
            }
        }
        if (direction == 'W')
        {
            if (x-leMot.size() < 0)
            {
                res = false;
            }
            else
            {
                for (i=0; i<leMot.size(); i++)
                {
                    if ((lePlateau.lePlateau[x-i][y].symbole != '%'))
                    {
                        res = false;
                    }
                }
            }
        }
        if (direction == 'E')
        {
            if (x+leMot.size() > 14)
            {
                res = false;
            }
            else
            {
                for (i=0; i<leMot.size(); i++)
                {
                    if ((lePlateau.lePlateau[x+i][y].symbole != '%'))
                    {
                        res = false;
                    }
                }
            }
        }
        return (res && connecte);
    }

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

    public void ChangerLettres(String LettresAChanger) throws LettreNonPresente
    {
        int i,j;

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
                    if (this.mesLettres[i].symbole == LettresAChanger.charAt(i))
                    {
                        this.mesLettres[i].symbole = '%';
                        this.mesLettres[i].valeur = 0;
                    }
                }
            }
        }
    }
}