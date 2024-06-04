import java.util.LinkedList;
//import java.util.Random;
import java.util.Scanner;
//import java.util.LinkedList;



public class Chevalet
{
    
    //attributs
    public Lettre[] mesLettres = new Lettre[7]; // les lettres présentes dans le Chevalet
    public String nom;                          // le nom du Joueur
    public int score;                           // le score du Joueur


    // Constructeur : Créer un chevalet avec un nom. Le chevalet est vide est le score est 0
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


    // Remplit le chevalet si il manque des lettres, et tant qu'il y en a dans le sac
    // cette méthode retourne false si il n'y a plus de lettre dans le sac apres que le joueur ait pioché
    public boolean piocherLettre(Sac leSac)
    {
        int i, ind;

        // pour chaque case du chevalet, si elle est vide, on pioche une lettre au hasard dans le sac 
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
        // on vérifie qu'il reste des lettres dans le sac
        return leSac.nb_lettres_restantes > 0;
    }


    // Compte les points des lettres restantes sur le chevalet
    // utile en fin de partie
    public int CompterPoint()
    {
        int sum=0, i;

        for (i=0; i<7; i++)
        {
            sum += this.mesLettres[i].valeur;
        }
        return sum;
    }
    

    // verifie que la lettre est présente dans le chevalet et l'ajoute à la liste chainée de Lettre pour la manipuler ensuite
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


    // supprime du jeu les lettres données du chevalet
    // les lettres à changer sont données sous forme de String
    public boolean ChangerLettres(String LettresAChanger, Sac leSac) throws LettreNonPresente
    {
        int i,j;
        boolean resteLettres;

        // pour chaque lettre dans la string LettresAChanger, on vérifie qu'elle est bien dans le chevalet
        // si c'est le cas, on la remplace par une lettre vide
        for (i=0; i<LettresAChanger.length(); i++)
        {
            if (!LettrePresente(LettresAChanger.charAt(i), new LinkedList<Lettre>()))
            {
                throw new LettreNonPresente("La lettre " + LettresAChanger.charAt(i) + " n'est pas dans votre chevalet.");
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
                        // le break sert à ce que toutes les mêmes lettres dans le chevalet ne soient pas changées en même temps
                    }
                }
            }
        }
        // le joueur pioche pour remplir les cases vides qui viennent d'être créées. 
        resteLettres = piocherLettre(leSac);
        return resteLettres;
    }
    
    
    
    // Demande les infos au joueur pour jouer un coup
    // retourne une classe/structure ChoixJouer, avec toutes les données nécessaires pour jouer un coup
    public ChoixJouer ChoixCoup()
    {
        ChoixJouer leChoix = new ChoixJouer();
        char direction = 'm';
        Scanner entree = new Scanner(System.in);

        while ( !((leChoix.choix == 1) || (leChoix.choix == 2) || (leChoix.choix == 3) ) )
        {
            System.out.println("Voulez-vous jouer un mot (1) \n Changer des lettres (2) \n Passer votre tour (3) ");
            leChoix.choix = entree.nextInt();
        }

        if (leChoix.choix == 1)
        {
            System.out.println("Donner le mot que vous voulez placer");
            entree.nextLine();
            leChoix.mot = entree.nextLine();
            System.out.println("Donner la coordonnée x");
            leChoix.x = entree.nextInt() -1;                    // on prends en compte le décalage, l'indice commence à 0 en java
            System.out.println("Donner la coordonnée y");
            leChoix.y = entree.nextInt() -1;
            entree.nextLine();
            while((direction!='N') && (direction!='S') && (direction!='E') && (direction!='W'))
            {
                System.out.println("Donner la direction : N, S, E, W");
                String str = entree.nextLine();
                direction = str.charAt(0);
            }
            // on traduit les directions en dx et dy, qu'on va entrer dans les autres méthodes, 
            // pour ne pas avoir à refaire la disjontion de cas à chaque fois
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
        if (leChoix.choix == 2)
        {
            System.out.println("Donner les lettres que vous voulez changer (sans espaces)");
            entree.nextLine();
            leChoix.mot = entree.nextLine();
        }
        //entree.close();
        return leChoix;
    }



    // méthode qui permet de jouer la premier coup de la partie (qui a des règles différentes du reste de la partie)
    public Plateau JouerPremierCoup(Plateau lePlateau, Sac leSac)
    {
        ChoixJouer leChoix = new ChoixJouer();
        boolean lettreDansChevalet, bienPlace, coupValide = false;
        int i;
        LinkedList<Lettre> leMotEnLettre = new LinkedList<Lettre>();
        
        // tant que le coup n'est pas valide, on redemande au joueur de jouer
        while (!(coupValide))
        {   
            System.out.println();
            System.out.println("-------Vous ne pouvez que placer un mot au milieu.-------");
            System.out.println();
            // on remet le mot qui va être joué à 0
            leMotEnLettre.clear();
            // on affiche le chevalet pour que le joueur puisse jouer
            System.out.println("Voici votre chevalet :");
            this.afficher_chevalet();
            // le joueur entre les données
            leChoix = this.ChoixCoup();
            
            if (leChoix.choix == 1)                                 // si le joueur pose un mot (seul choix au 1er tour)
            {
                lettreDansChevalet = true;
                bienPlace = true;
                // on vérifie que toutes les lettres sont dans le chevalet
                for (i=0; i < leChoix.mot.length(); i++ )
                {
                    if (!LettrePresente(leChoix.mot.charAt(i), leMotEnLettre))
                    {
                        lettreDansChevalet = false;
                        System.out.print("Une de ces lettres n'est pas présente dans votre chevalet. \n ");
                        break;
                    }
                }
                // on vérifie que le mot est bien placé (qu'il passe par la case du milieu du plateau pour le premier coup)
                if (!PremierCoupValide(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau))
                {
                    System.out.println("Votre mot ne peut pas être posé ici, veuillez en entrer un nouveau. \n ");
                    bienPlace = false;
                }

                coupValide = ((bienPlace) && (lettreDansChevalet));
                // si le coup est valide, on le joue
                if (coupValide)
                {
                    // on stocke le mot complet (lettre qui viennent du chevalet + lettres déjà présente sur le plateau)
                    Serarien res = this.VerifMotPlace(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau);
                    leMotEnLettre = res.nouveauMot;
                    // les bonus sous forme de String
                    String bonus = res.bonus;
                    // on met à jour le score du joueur (*2 car c'est le premier coup)
                    this.score += this.CompterPointCoup(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau, bonus) * 2;
                    // on met à jour le Plateau en posant les lettres
                    lePlateau = this.PlacerMot(leMotEnLettre, leChoix.dx, leChoix.dy, leChoix.x, leChoix.y, lePlateau);
                    // le joueur pioche pour remplacer les lettres qu'il vient de jouer
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
            else if((leChoix.choix == 2) || (leChoix.choix == 3)) 
            {
                coupValide = false;
                System.out.println();
                System.out.println("Vous ne pouvez pas faire ce choix.");
                System.out.println();
            }
        }   
        return lePlateau;
    }
    

    // vérifie que le mot est bien placé pour le premier coup
    public boolean PremierCoupValide(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau)
    {
        boolean res = false;
        if (( (x == 7) && (dy == 1) && (y+leMot.size() >= 7) && (y<=7) ) || (((y == 7) && (dx == 1)) && (x+leMot.size() >= 7)  &&  (x<=7)))
        {
            res = true;
        }
        return res;
    }
    
    

    // vérifie que le mot est bien placé pour un coup classique
    // retourne le mot complet
    // retourne une chaîne de caractères avec les bonus présents
    public Serarien VerifMotPlace(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau)
    {
        boolean depassePas = true, connecte = false;
        Serarien res = new Serarien();
        int i, tempSize = leMot.size();
        int tempValeur;
        char tempSymbole;
        // pour chaque lettre, si la case ou on veut la poser n'est pas vide,
        // on ajoute la lettre sur la case au mot
        // on met sa valeur sur le plateau à 0 pour ne pas la compter deux fois par la suite 
        for (i=0; i<tempSize; i++)
        {
            if (lePlateau.lePlateau[x+i*dx][y+i*dy].symbole != '_')
            {
                tempSymbole = lePlateau.lePlateau[x+i*dx][y+i*dy].symbole;
                tempValeur = lePlateau.lePlateau[x+i*dx][y+i*dy].valeur;
                leMot.add(i, new Lettre(tempSymbole, tempValeur));
                lePlateau.lePlateau[x+i*dx][y+i*dy].valeur = 0;
            }
            // on vérifie que le mot est bien connecté à au moins une lettre déjà posée
            if ((lePlateau.lePlateau[x+i*dx+1][y+i*dy].symbole != '_') || (lePlateau.lePlateau[x+i*dx-1][y+i*dy].symbole != '_') || (lePlateau.lePlateau[x+i*dx][y+i*dy+1].symbole != '_') || (lePlateau.lePlateau[x+i*dx][y+i*dy-1].symbole != '_'))
            {
                connecte = true;
            }
        }
        // on vérifie que le mot ne déborde pas du plateau
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


    // méthode pour jouer un coup au milieu de la partie
    // marche pareil que JouerPremierCoup
    // seul la méthode pour vérifier que le mot est bien placé change
    // on aurait pu la passer en paramètre...
    // et le joueur peut maintenant échanger des lettres
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
                
                //LinkedList<Lettre> copyMotEnLettre = (LinkedList<Lettre>) leMotEnLettre.clone();
                LinkedList<Lettre> copyMotEnLettre = new LinkedList<Lettre>(leMotEnLettre);
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
                    coupValide=true;
                } 
                catch (LettreNonPresente e) 
                {
                    System.out.println(e+ " Réessayez.");
                    coupValide=false;
                }
            }
            if (leChoix.choix == 3)
            {
                coupValide = true;
            }
        }    
        return lePlateau;
    }



    // pose les lettres sur le plateau
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


    // compte les points gagné en jouant un coup. Nous n'avons pas eu le temps d'implémenter les bonus
    public int CompterPointCoup(LinkedList<Lettre> leMot, int dx, int dy, int x, int y, Plateau lePlateau, String bonus)
    {
        int scoreFait = 0, j, i;
        for (i=0; i<leMot.size(); i++)
        {
            scoreFait += leMot.get(i).valeur;

            j=1;
            // pour chaque direction, on vérife s'il y a des lettres. Si oui, on ajoute leur valeur au score.
            // Ca ne compte pas les lettres que l'on pose en double, car elle ne sont pas encore posées sur le plateau
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



    //pas besoin au final
    // on a utilisé des LinkedList de Lettre plutôt que des String
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
    

    // réécriture de la méthode toString pour afficher les lettres et leur valeur
    // permet de faire des tests facilement
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

    // affiche le chevalet pour que le joueur puisse jouer
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
        System.out.println("Votre score est de : " + this.score +  "\n\n");

    }
}