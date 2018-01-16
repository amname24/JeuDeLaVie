package model;

public class FourmiJaune implements Fourmi{
	/*direction: 0 = nord
	 * 			 1 = est
	 * 			 2 = sud
	 * 			 3 = ouest
	 */
	private int direction; 
	/*couleur: 3 = Jaune
	 * 		   
	 */
	private int couleur;
	
	public FourmiJaune()
	{
		direction = 0;
		couleur = 3;
	}
	
	private FourmiJaune(int d)
	{
		direction = d;
		couleur = 3;
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
		if(couleur == 3 || couleur == 1)
			direction = mod(direction + 1 , 4);
		else
			if(couleur == 0)
				direction = mod(direction - 1 , 4);
		
		return(direction);
	}
	
	public Object clone()
	{
		FourmiJaune f = new FourmiJaune(direction);
		return(f);
	}
	
}
