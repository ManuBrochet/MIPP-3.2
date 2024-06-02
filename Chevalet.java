import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;

public class Chevalet
{
    public Lettre[] mesLettres = new Lettre[7];
    public String nom;
    public int score;

    // Constructeur
    public Chevalet(String nom)
    {
        int i;

        for (i=0; i<7;i++)
        {
            this.mesLettres[i] = new Lettre('_', 0);
        }

        this.nom = nom;
        this.score = 0;
    }
    //fonctionne

    // Remplit le chevalet si il manque des lettres, et tant qu'il y en a dans le sac
    public boolean piocherLettre(Sac leSac)
    {

        int i, ind;

        for (i=0; i<7; i++)
        {
            if ((this.mesLettres[i].symbole == '_') && (leSac.nb_lettres_restantes > 0))
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
    //fonctionne

    // Compte les points des lettres sur le chevalet
    public int CompterPoint()
    {
        int sum=0, i;

        for (i=0; i<7; i++)
        {
            sum += this.mesLettres[i].valeur;
        }
        return sum;
    }
    //fonctionne

    /*
    // vérifie que le mot ne se place pas sur une lettre déjà placée sur le plateau, et que le mot est bien connecté à au moins une autre lettre
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
                if (lePlateau.lePlateau[x+i*dx][y+i*dy].symbole != '_')
                {
                    res = false;
                }
                if ((lePlateau.lePlateau[x+i*dx+1][y+i*dy].symbole != '_') && (lePlateau.lePlateau[x+i*dx-1][y+i*dy].symbole != '_') && (lePlateau.lePlateau[x+i*dx][y+i*dy+1].symbole != '_') && (lePlateau.lePlateau[x+i*dx][y+i*dy-1].symbole != '_'))
                {
                    connecte = true;
                }
            }
        }
        return (res && connecte);
    }*/


    /*
    //Compte les points du mot qui vient d'ếtre posé
    public int CompterPointCoup(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau)
    {
        int score=0, j=1, i;
        for (i=0; i<leMot.size(); i++)
        {
            while((lePlateau.lePlateau[x+i*dx+j][y+i*dy].symbole != '_') && (x+i*dx+j<14))
            {
                score += lePlateau.lePlateau[x+i*dx+j][y+i*dy].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx-j][y+i*dy].symbole != '_') && (x+i*dx-j>0))
            {
                score += lePlateau.lePlateau[x+i*dx-j][y+i*dy].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx][y+i*dy+j].symbole != '_') && (y+i*dy+j<14))
            {
                score += lePlateau.lePlateau[x+i*dx][y+i*dy+j].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx][y+i*dy-j].symbole != '_') && (y+i*dy-j>0))
            {
                score += lePlateau.lePlateau[x+i*dx][y+i*dy-j].valeur;
                j++;
            }
        }
        return score;
    }*/

    /*
    //vérifie que le mot placé est bien valide et ajoute les points au total
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
        //faudra ajouter pour vérifier que les mots sont valides
        if (res)
        {
            this.score += CompterPointCoup(leMot, dx, dy, x, y, lePlateau);
        }
        return res;
    }*/

    /*
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
        
    } */

    // verifie que la lettre est présente dans le chevalet et l'ajoute à la liste chainée de Lettre pour la manimuler ensuite
    public boolean LettrePresente(char laLettre, LinkedList<Lettre> lesLettres)
    {
        int i;
        boolean res = false;

        for (i=0; i<7; i++)
        {
            if (this.mesLettres[i].symbole == laLettre)
            {
                res = true;
                lesLettres.add(this.mesLettres[i]);
                //this.mesLettres[i].symbole = '_';
                break;
            }
        }
        return res;
    }




    // change les lettres données du chevalet
    public boolean ChangerLettres(String LettresAChanger, Sac leSac) throws LettreNonPresente
    {
        int i,j;
        boolean resteLettres;

        for (i=0; i<LettresAChanger.length(); i++)
        {
            if (!LettrePresente(LettresAChanger.charAt(i), new LinkedList<Lettre>()))
            {
                throw new LettreNonPresente("La lettre" + LettresAChanger.charAt(i) + " n'est pas dans votre chevalet.");
            }
            else
            {
                for (j=0; j<7; j++)
                {
                    if (this.mesLettres[j].symbole == LettresAChanger.charAt(i))
                    {
                        this.mesLettres[j].symbole = '_';
                        this.mesLettres[j].valeur = 0;
                        break;
                    }
                }
            }
        }
        resteLettres = piocherLettre(leSac);
        return resteLettres;
    }


    /*
    // Demande les info au joueur pour jouer un coup
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
    }*/


    
    
    // Demande les info au joueur pour jouer un coup
    public ChoixJouer ChoixCoup()
    {
        ChoixJouer leChoix = new ChoixJouer();
        char direction = 'm';
        Scanner entree = new Scanner(System.in);
        System.out.println("Voulez-vous jouer un mot (1) \n Changer des lettres (2) \n Passer votre tour (3) ");
        leChoix.choix = entree.nextInt();
        if (leChoix.choix == 1)
        {
            System.out.println("Donner le mot que vous voulez placer");
            entree.nextLine();
            leChoix.mot = entree.nextLine();
            System.out.println("Donner la coordonnées x");
            leChoix.x = entree.nextInt() -1;
            System.out.println("Donner la coordonnées y");
            leChoix.y = entree.nextInt() -1;
            entree.nextLine();
            while((direction!='N') && (direction!='S') && (direction!='E') && (direction!='W'))
            {
                System.out.println("Donner la direction : N, S, E, W");
                String str = entree.nextLine();
                direction = str.charAt(0);
            }
            if (direction == 'N')
            {
                leChoix.dx = 0;
                leChoix.dy = -1;
            }
            if (direction == 'S')
            {
                leChoix.dx = 0;
                leChoix.dy = 1;
            }
            if (direction == 'W')
            {
                leChoix.dx = -1;
                leChoix.dy = 0;
            }
            if (direction == 'E')
            {
                leChoix.dx = 1;
                leChoix.dy = 0;
            }
        }
        else if (leChoix.choix == 2)
        {
            System.out.println("Donner les letres que vous voulez changer (sans espaces)");
            leChoix.mot = entree.nextLine();
        }
        return leChoix;
    }
    //fonctionne


    public Plateau JouerPremierCoup(Plateau lePlateau, Sac leSac)
    {
        ChoixJouer leChoix = new ChoixJouer();
        boolean lettreDansChevalet, bienPlace, coupValide = false;
        int i;
        LinkedList<Lettre> leMotEnLettre = new LinkedList<Lettre>();
        
        
        while (!(coupValide))
        {
            leMotEnLettre.clear();
            this.afficher_chevalet();
            leChoix = this.ChoixCoup();
            if (leChoix.choix == 1)
            {
                lettreDansChevalet = true;
                bienPlace = true;

                for (i=0; i < leChoix.mot.length(); i++ )
                {
                    if (!LettrePresente(leChoix.mot.charAt(i), leMotEnLettre))
                    {
                        lettreDansChevalet = false;
                        System.out.print("Une de ces lettres n'est pas présente dans votre chevalet. \n ");
                        break;
                    }
                }
                if (!PremierCoupValide(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau))
                {
                    System.out.println("Votre mot ne peut pas être posé ici, veuillez en entrer un nouveau. \n ");
                    bienPlace = false;
                }

                coupValide = ((bienPlace) && (lettreDansChevalet));

                if (coupValide)
                {
                    Serarien res = this.VerifMotPlace(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau);
                    leMotEnLettre = res.nouveauMot;
                    for (i=0; i< leMotEnLettre.size(); i++)
                    {
                        System.out.println("La lettre " + i + " est : " + leMotEnLettre.get(i).symbole + " et sa valeur est " + leMotEnLettre.get(i).valeur +"\n");
                    }
                    String bonus = res.bonus;
                    System.out.println("Les bonus " + bonus + "\n");
                    System.out.println("Le score " + this.score + "\n");
                    this.score += this.CompterPointCoup(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau, bonus);
                    lePlateau = this.PlacerMot(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau);
                    try 
                    {
                        ChangerLettres(leChoix.mot, leSac);
                    } 
                    catch (LettreNonPresente e) 
                    {
                        System.out.println("La lettre n'est pas présente dans le chevalet \n");
                    }
                }
            }
            if (leChoix.choix == 2)
            {

                try 
                {
                    ChangerLettres(leChoix.mot, leSac);
                } 
                catch (LettreNonPresente e) 
                {
                    System.out.println(e);
                    coupValide=false;
                }
            }
        }    
        return lePlateau;
    }


    public boolean PremierCoupValide(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau)
    {
        boolean res = false;
        if (( (x == 7) && (dy == 1) && (y+leMot.size() >= 7) && (y<=7) ) || (((y == 7) && (dx == 1)) && (x+leMot.size() >= 7)  &&  (x<=7)))
        {
            res = true;
        }
        return res;
    }
    
    
    //!this.VerifMotPlace(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau).valide

    public Plateau JouerUnCoup(Plateau lePlateau, Sac leSac)
    {
        ChoixJouer leChoix = new ChoixJouer();
        boolean lettreDansChevalet, bienPlace, coupValide = false;
        int i;
        LinkedList<Lettre> leMotEnLettre = new LinkedList<Lettre>();
        
        
        while (!(coupValide))
        {
            leMotEnLettre.clear();
            this.afficher_chevalet();
            leChoix = this.ChoixCoup();
            if (leChoix.choix == 1)
            {
                lettreDansChevalet = true;
                bienPlace = true;

                for (i=0; i < leChoix.mot.length(); i++ )
                {
                    if (!LettrePresente(leChoix.mot.charAt(i), leMotEnLettre))
                    {
                        lettreDansChevalet = false;
                        System.out.print("Une de ces lettres n'est pas présente dans votre chevalet. \n ");
                        break;
                    }
                }
                
                LinkedList<Lettre> copyMotEnLettre = (LinkedList) leMotEnLettre.clone();
                if (!this.VerifMotPlace(copyMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau).valide)

                {
                    System.out.println("Votre mot ne peut pas être posé ici, veuillez en entrer un nouveau. \n ");
                    bienPlace = false;
                }
                copyMotEnLettre.clear();

                coupValide = ((bienPlace) && (lettreDansChevalet));

                if (coupValide)
                {
                    Serarien res = this.VerifMotPlace(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau);
                    leMotEnLettre = res.nouveauMot;
                    for (i=0; i< leMotEnLettre.size(); i++)
                    {
                        System.out.println("La lettre " + i + " est : " + leMotEnLettre.get(i).symbole + " et sa valeur est " + leMotEnLettre.get(i).valeur +"\n");
                    }
                    String bonus = res.bonus;
                    this.score += this.CompterPointCoup(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau, bonus);
                    lePlateau = this.PlacerMot(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau);
                    try 
                    {
                        ChangerLettres(leChoix.mot, leSac);
                    } 
                    catch (LettreNonPresente e) 
                    {
                        System.out.println("La lettre n'est pas présente dans le chevalet \n");
                    }
                }
            }
            if (leChoix.choix == 2)
            {

                try 
                {
                    ChangerLettres(leChoix.mot, leSac);
                } 
                catch (LettreNonPresente e) 
                {
                    System.out.println(e);
                    coupValide=false;
                }
            }
        }    
        return lePlateau;
    }



    public Plateau PlacerMot(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau)
    {
        int i;
        for (i=0; i<leMot.size(); i++)
        {
            lePlateau.lePlateau[x+i*dx][y + i*dy].symbole = leMot.get(i).symbole;
            lePlateau.lePlateau[x+i*dx][y + i*dy].valeur = leMot.get(i).valeur;
        }
        return lePlateau;
    }

    public int CompterPointCoup(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau, String bonus)
    {
        int scoreFait = 0, j, i;
        for (i=0; i<leMot.size(); i++)
        {
            scoreFait += leMot.get(i).valeur;

            j=1;
            while((lePlateau.lePlateau[x+i*dx+j][y+i*dy].symbole != '_') && (x+i*dx+j<14))
            {
                scoreFait += lePlateau.lePlateau[x+i*dx+j][y+i*dy].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx-j][y+i*dy].symbole != '_') && (x+i*dx-j>0))
            {
                scoreFait += lePlateau.lePlateau[x+i*dx-j][y+i*dy].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx][y+i*dy+j].symbole != '_') && (y+i*dy+j<14))
            {
                scoreFait += lePlateau.lePlateau[x+i*dx][y+i*dy+j].valeur;
                j++;
            }
            j=1;
            while((lePlateau.lePlateau[x+i*dx][y+i*dy-j].symbole != '_') && (y+i*dy-j>0))
            {
                scoreFait += lePlateau.lePlateau[x+i*dx][y+i*dy-j].valeur;
                j++;
            }
        }
        return scoreFait;
    }


    public Serarien VerifMotPlace(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau)
    {
        boolean depassePas = true, connecte = false;
        Serarien res = new Serarien();
        int i, j=1, tempSize = leMot.size();
        int tempValeur;
        char tempSymbole;
        for (i=0; i<tempSize; i++)
        {
            if (lePlateau.lePlateau[x+i*dx][y+i*dy].symbole != '_')
            {
                tempSymbole = lePlateau.lePlateau[x+i*dx][y+i*dy].symbole;
                tempValeur = lePlateau.lePlateau[x+i*dx][y+i*dy].valeur;
                leMot.add(i, new Lettre(tempSymbole, tempValeur));
                lePlateau.lePlateau[x+i*dx][y+i*dy].valeur = 0;
            }
            if ((lePlateau.lePlateau[x+i*dx+1][y+i*dy].symbole != '_') || (lePlateau.lePlateau[x+i*dx-1][y+i*dy].symbole != '_') || (lePlateau.lePlateau[x+i*dx][y+i*dy+1].symbole != '_') || (lePlateau.lePlateau[x+i*dx][y+i*dy-1].symbole != '_'))
            {
                connecte = true;
            }
        }
        if ((x + leMot.size()*dx < 0) || (x+leMot.size()*dx > 14) || (y+leMot.size()*dy < 0) || (y+leMot.size()*dy>14))
        {
            depassePas = false;
        }
        res.valide = (depassePas && connecte);
        res.nouveauMot = leMot;
        for (i=0; i<leMot.size(); i++)
        {
            res.bonus = res.bonus + lePlateau.lePlateau[x+i*dx][y+i*dy].symbole;
        }
        return res;
    }

    //pas besoin au final
    public String insererChar(String leMot, int ind, char leChar)
    {
        String res="";
        int i;

        for (i=0; i<ind-1; i++ )
        {
            res = res + leMot.charAt(i);
        }
        res = res + leChar;
        for (i=ind-1; i<leMot.length(); i++)
        {
            res = res + leMot.charAt(i);
        }
        return res;
    }
    

    // réécriture de la méthode toString pour afficher les lettres et leurs valeures
    public String toString()
    {
        int i;
        String str = "Les lettres du chevalet sont : \n";
        for (i=0; i<7; i++)
        {
            str = str + " " + this.mesLettres[i].toString() + "\n";
        }
        str = str + "\nSon score est : " + this.score + "\n\n";
        return str;
    }

    public void afficher_chevalet()
    {
        System.out.println("Joueur : "+ this.nom);
        for (int i=0; i<7; i++)
        {
            System.out.print(this.mesLettres[i].symbole + " ");
        }
        System.out.println();
        for (int i=0; i<7; i++)
        {
            System.out.print(this.mesLettres[i].valeur + " ");
        }
        System.out.println();
    }
    
}