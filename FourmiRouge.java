package model;

public class FourmiRouge implements Fourmi{
	
	/*direction: 0 = nord
	 * 			 1 = est
	 * 			 2 = sud
	 * 			 3 = ouest
	 */
	private int direction; 
	/*couleur: 1 = rouge
	 * 		   autres couleurs à implémenter dans le prochain sprint
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

	@Override
	public int tourner(int couleur) 
	{
		if(couleur == 1)
			direction = (direction + 1) % 4;
		else
			if(couleur == 0)
				direction = (direction - 1) % 4;
		
		return(direction);
	}
}
