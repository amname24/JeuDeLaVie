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
	private int dureeVie;
	
	public FourmiNoire()
	{
		direction = 0;
		couleur = 4;
		dureeVie = 3;
	}
	
	private FourmiNoire(int d)
	{
		direction = 0;
		couleur = 4;
		dureeVie = d;
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
	
	public boolean meurt()
	{
		dureeVie --;
		if(dureeVie <= 0)
			return(true);
		else
			return(false);
	}
	public Object clone()
	{
		FourmiNoire f = new FourmiNoire(dureeVie);
		return(f);
	}
	
}
