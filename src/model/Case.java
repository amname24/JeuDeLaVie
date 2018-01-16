package model;

public class Case implements Cloneable {
	/*couleur:  
	 * 			-1 = vert => Plante
	 * 			0 = blanc
	 * 			1 = rouge
	 * 			
	 * 		   autres couleurs � impl�menter dans le prochain sprint
	 */
	private int couleur;
	private Fourmi occupante;
	
	public void affiche(){
		if(occupante!=null) System.out.print("+"+occupante.getCouleur()+" ");
		else System.out.print(" "+couleur+" ");
	}
	
	public Case()
	{
		couleur = 0;
		occupante = null;
	}
	
	public Case(int couleur)
	{
		this.couleur = couleur;
		occupante = null;
	}
	
	public void ajoutFourmi(Fourmi f)
	{
		this.occupante = f;
	}
	
	public void supFourmi()
	{
		occupante = null;
	}
	
	public int getCouleur()
	{
		return(couleur);
	}
	
	public void setCouleur(int c)
	{
		this.couleur = c;
	}
	
	public Fourmi getOccupante()
	{
		return(occupante);
	}
	public boolean estMort(){
		return false;
	}
	public Object clone()
	{
		Case c = new Case();
		if(getOccupante()!=null)
			c.ajoutFourmi((Fourmi)getOccupante().clone());
		c.setCouleur(getCouleur());
		return c;
	}
	public boolean isPlante(){
		return false;
	}
}


