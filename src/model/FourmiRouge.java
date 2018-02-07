package model;

public class FourmiRouge implements Fourmi{
	
	/*direction: 0 = nord
	 * 			 1 = est
	 * 			 2 = sud
	 * 			 3 = ouest
	 */
	private int direction; 
	/*couleur: 1 = rouge
	 * 
	 */
	private int couleur;
	
	public FourmiRouge()
	{
		direction = 0;
		couleur = 1;
	}
	
	private FourmiRouge(int d)
	{
		direction = d;
		couleur = 1;
	}
	
	public int getDirection()
	{
		return(direction);
	}
	
	public int getCouleur()
	{
		return(couleur);
	}
	
	private int mod(int x, int y)
	{
		return x % y < 0 ? x % y + y : x % y;
	}
	
	public int tourner(int couleur) 
	{
		if(couleur == 1 || couleur == 2)
			direction = mod(direction + 1 , 4);
		else
			if(couleur == 0)
				direction = mod(direction - 1 , 4);
		
		return(direction);
	}
	
	public Object clone()
	{
		FourmiRouge f = new FourmiRouge(direction);
		return(f);
	}
	
}
