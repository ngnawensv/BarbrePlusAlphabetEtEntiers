package barbreplusavecframe;

import java.util.ArrayList;

/**
 * 
 * @author Ngnawen
 * @param <Type> 
 */
public class Noeud<Type> implements java.io.Serializable {

    //Collection des Noeuds enfants du noeud
    public ArrayList<Noeud<Type>> fils = new ArrayList<>();
    //Collection des clés du noeud
    public ArrayList<Type> keys = new ArrayList<>();
    //Noeud Parent
    private Noeud<Type> parent;
    //Classe interfaéant "Executable" et donc contenant une procédure de comparaison de <Type>
    private Executable compar;
    //Ordre de l'abre (u = nombre de clés maximum)
    private int u; //sup 

    /* Constructeur de la classe noeud, qui permet l'ajout et la recherche d'élément dans les branches	 * 
     * @param u Nombre de clés maximum du noeud
     * @param e Classe interfaéant "Executable" et donc contenant une procédure de comparaison de <Type>
     * @param parent Nombre de clés minimum du noeud	 * 
     */
    public Noeud(int u, Executable e, Noeud<Type> parent) {
        this.u = u;
        compar = e;
        this.parent = parent;
    }

    public boolean compare(Type arg1, Type arg2) {
        return compar.execute(arg1, arg2);
    }

    public Noeud<Type> ajoutValeur(Type valeur) {

        return null;
    }

    /* Cherche une valeur dans la branche	 * 
     * @param valeur Valeur é rechercher dans la branche
     * @return Vrai si la valeur est trouvée, sinon non
     */
    public Noeud<Type> contient(Type valeur) {
        Noeud<Type> retour = null;

        if (this.keys.contains(valeur) && (this.fils.isEmpty())) {
            retour = this;
        } else {
            Noeud<Type> trouve = null;
            int i = 0;
            //if (this.fils.size() > 0)

            while ((trouve == null) && (i < this.fils.size())) {
                trouve = this.fils.get(i).contient(valeur);
                i++;
            }

            retour = trouve;

        }
        return retour;
    }

    public Noeud<Type> choixNoeudAjout(Type valeur) {
        Noeud<Type> retour = null;

        if (this.fils.isEmpty()) {
            retour = this;
        } else {
            int index = 0;

            boolean trouve = false;
            while (!trouve && (index < this.keys.size())) {
                trouve = compare(valeur, this.keys.get(index));
                if (!trouve) {
                    index++;
                }
            }


            retour = this.fils.get(index).choixNoeudAjout(valeur);
        }
        return retour;
    }

    public void afficheNoeud(boolean afficheSousNoeuds, int lvl) {

        String dots = "";

        for (int i = 0; i < lvl; i++) {
            dots += "..";
        }

        for (Type valeur : this.keys) {
            dots += valeur.toString() + " ";
        }

        System.out.println(dots);

        if (afficheSousNoeuds) {
            for (Noeud<Type> noeud : this.fils) {
                noeud.afficheNoeud(afficheSousNoeuds, lvl + 1);
            }
        }
    }

    private void insert(Type valeur) {
        int i = 0;
        while ((this.keys.size() > i) && compare(this.keys.get(i), valeur)) {
            i++;
        }

        this.keys.add(i, valeur);

    }


    /*
     * Algo d'ajout de données dans l'arbre :
     * 
     * On choisit un noeud approprié en recherchant dans l'arbre l'endroit oé devrait se
     * situer la donnée.
     * On ajoute la donnée é ce noeud (qui peut ne pas étre une feuille si l'ajout résulte du fait 
     * qu'une donnée médiane d'un noeud fils vient de remonter)
     * Si la taille du noeud dépasse l'ordre de l'arbre, on trouve l'élément médian,
     * on le remonte dans son parent (eventuellement on recrée une racine), et on crée deux nouveaux noeuds
     * le premier avec tous les éléments dont la comparaison renvoie faux et le deuxieme tous les éléments
     * dont la comparaison renvoie true.
     * On ajoute les éventuels noeuds fils de notre noeud aux nouveaux noeuds enfants 
     * On raz la collection d'enfants de notre noeud et on y a ajoute nos deux nouveaux noeud gauche et droit 
     * On renvoie la racine (potentiellement la nouvelle)
     * 
     * 
     */
    public Noeud<Type> addValeur(Type nouvelleValeur) {
        Noeud<Type> racine = addValeur(nouvelleValeur, false);
        return racine;
    }

    public void addNoeud(Noeud<Type> noeud) {
        int i = 0;



        if (i == this.fils.size()) {
            this.fils.add(noeud);
        } else {
            while (((i < this.fils.size() && compare(this.fils.get(i).keys.get(this.fils.get(i).keys.size() - 1), noeud.keys.get(0))))) {
                i++;
            }
            this.fils.add(i, noeud);
        }
    }

    public boolean removeNoeud(Noeud<Type> noeud) {
        return fils.remove(noeud);
    }

    //renvoie la racine
    public Noeud<Type> addValeur(Type nouvelleValeur, boolean force) {
        Noeud<Type> noeud;
        Noeud<Type> racine;
        Type eleMedian;
        Integer indexMedian = 0;
        Integer tailleListe;
        Noeud<Type> noeud2 = this;

        while (noeud2.parent != null) {
            noeud2 = noeud2.parent;
        }

        racine = noeud2;

        if (force) {
            noeud = this;
        } else {
            noeud = this.choixNoeudAjout(nouvelleValeur);

        }

        tailleListe = noeud.keys.size();

        if (!noeud.keys.contains(nouvelleValeur)) {

            if (tailleListe == u) {
                Noeud<Type> noeudGauche = new Noeud<>(u, compar, null);
                Noeud<Type> noeudDroit = new Noeud<>(u, compar, null);

                noeud.insert(nouvelleValeur);
                tailleListe++;

                if (tailleListe % 2 == 0) //pair                
                {
                    indexMedian = 1 + (tailleListe / 2);
                } else {
                    indexMedian = (1 + tailleListe) / 2;
                }

                indexMedian--;


                eleMedian = noeud.keys.get(indexMedian);

                for (int i = 0; i < indexMedian; i++) {
                    noeudGauche.addValeur(noeud.keys.get(i));
                }


                if (!noeud.fils.isEmpty()) {
                    for (int i = indexMedian + 1; i < tailleListe; i++) {
                        noeudDroit.addValeur(noeud.keys.get(i));
                    }
                } else {
                    for (int i = indexMedian; i < tailleListe; i++) {
                        noeudDroit.addValeur(noeud.keys.get(i));
                    }
                }

                if (!noeud.fils.isEmpty()) {
                    indexMedian++;

                    for (int i = 0; i < (indexMedian); i++) {
                        noeudGauche.addNoeud(noeud.fils.get(i));
                        noeud.fils.get(i).parent = noeudGauche;
                    }


                    for (int i = (indexMedian); i < noeud.fils.size(); i++) {
                        noeudDroit.addNoeud(noeud.fils.get(i));
                        noeud.fils.get(i).parent = noeudDroit;
                    }
                }

                if (noeud.parent == null) {
                    Noeud<Type> nouveauParent = new Noeud<>(u, compar, null);




                    nouveauParent.addNoeud(noeudGauche);
                    nouveauParent.addNoeud(noeudDroit);
                    noeudGauche.parent = nouveauParent;
                    noeudDroit.parent = nouveauParent;
                    nouveauParent.addValeur(eleMedian, true);


                    racine = nouveauParent;


                } else {

                    noeud.parent.addNoeud(noeudGauche);
                    noeud.parent.addNoeud(noeudDroit);
                    noeud.parent.removeNoeud(noeud);
                    noeudGauche.parent = noeud.parent;
                    noeudDroit.parent = noeud.parent;
                    racine = noeud.parent.addValeur(eleMedian, true);


                }

            } else {
                noeud.insert(nouvelleValeur);
            }
        }

        return racine;
    }
}
