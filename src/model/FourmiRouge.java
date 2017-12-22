package model;

public class FourmiRouge implements Fourmi, Cloneable{
	
	/*direction: 0 = nord
	 * 			 1 = est
	 * 			 2 = sud
	 * 			 3 = ouest
	 */
	private int direction; 
	/*couleur: 1 = rouge
	 * 		   autres couleurs a implementer dans le prochain sprint
	 */
	private int couleur;
	
	public FourmiRouge()
	{
		direction = 0;
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
		if(couleur == 1)
		{
			direction = mod(direction + 1 , 4);
		}
		else
			if(couleur == 0)
				direction = mod(direction - 1 , 4);
		
		return(direction);
	}
	
	public Object clone()
	{
		FourmiRouge f = new FourmiRouge();
		f.setDirection(direction);
		return(f);
	}

	private void setDirection(int d) {
		direction = d;
		
	}
	
}
