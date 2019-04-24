package barbreplusavecframe;



import javax.swing.tree.DefaultMutableTreeNode;


public class BArbrePlus<Type> implements java.io.Serializable 
{
	private Noeud<Type> racine;
	
	public BArbrePlus(int u, Executable e)
	{
		racine = new Noeud<Type>(u, e, null);
	}
	
	public void afficheArbre()
	{
		racine.afficheNoeud(true, 0);
	}
	
//	  /**
//	 * Méthode récursive permettant de récupérer
//	 * tous les neouds
//	 * @param root un noeud qui represente le noeud de départ (la racine)
//	 * @return DefaultMutableTreeNode
//	 */
	 public DefaultMutableTreeNode bArbreToJTree()
	 {
		 return bArbreToJTree(racine);
	 }
	 
	 private DefaultMutableTreeNode bArbreToJTree(Noeud<Type> root)
	 {
		 String txt = "";
		 for(Type key :root.keys)
			 txt += key.toString() + " ";
		 
		 DefaultMutableTreeNode racine2 = new DefaultMutableTreeNode(txt, true);		 
		 for(Noeud<Type> fil: root.fils)
			 racine2.add(bArbreToJTree(fil));		 
		 
		 return racine2;
	 }

	 
	public void addValeur(Type valeur)
	{
		System.out.println("Ajout de " + valeur.toString());
		if (racine.contient(valeur)!=null)
		{
			System.out.println(valeur.toString() + " déjà dans l'arbre");
		}
		else
		{
			Noeud<Type> newRacine = racine.addValeur(valeur);
			if (racine != newRacine)	
						racine = newRacine;
		}
	}
}
