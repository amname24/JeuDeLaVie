package model;

public class Case {
	/*couleur:  0 = blanc
	 * 			1 = rouge
	 * 		   autres couleurs à implémenter dans le prochain sprint
	 */
	private int couleur;
	private Fourmi occupante;
	
	public Case()
	{
		couleur = 0;
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
}

