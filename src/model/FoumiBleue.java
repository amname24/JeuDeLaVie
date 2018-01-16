package model;

public class FoumiBleue implements Fourmi{
	/*direction: 0 = nord
	 * 			 1 = est
	 * 			 2 = sud
	 * 			 3 = ouest
	 */
	private int direction; 
	/*couleur: 2 = bleue
	 * 		   
	 */
	private int couleur;
	
	public FoumiBleue()
	{
		direction = 0;
		couleur = 2;
	}
	public FoumiBleue(int d)
	{
		direction = d;
		couleur = 2;
	}
	
	@Override
	public int getDirection() {
		// TODO Auto-generated method stub
		return direction;
	}

	@Override
	public int getCouleur() {
		// TODO Auto-generated method stub
		return couleur;
	}
	private int mod(int x, int y)
	{
		return x % y < 0 ? x % y + y : x % y;
	}

	@Override
	public int tourner(int couleur) {
		// TODO Auto-generated method stub
		if(couleur == 2 || couleur == 3 )
			direction = mod(direction + 1 , 4);
		else
			if(couleur == 0)
				direction = mod(direction - 1 , 4);
		return(direction);
	}

	public Object clone()
	{
		FoumiBleue f = new FoumiBleue(direction);
		return(f);
	}
}
