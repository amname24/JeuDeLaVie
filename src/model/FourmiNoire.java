package model;

public class FourmiNoire implements Fourmi{
	/*direction: 0 = nord
	 * 			 1 = est
	 * 			 2 = sud
	 * 			 3 = ouest
	 */
	private int direction; 
	/*couleur: 4 = Noire
	 * 		   
	 */
	private int couleur;
	
	public FourmiNoire()
	{
		direction = 0;
		couleur = 4;
	}
	
	private FourmiNoire(int d)
	{
		direction = d;
		couleur = 4;
	}
	
	public int getDirection()
	{
		return(direction);
	}
	
	public int getCouleur()
	{
		return(couleur);
	}
	
	public int tourner(int couleur) 
	{
		return(direction);
	}
	
	public Object clone()
	{
		FourmiNoire f = new FourmiNoire(direction);
		return(f);
	}
	
}
