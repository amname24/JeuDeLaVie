package model;




public class Plateau{
	private Case[][] plateau;
	
	public Plateau()
	{
		plateau = new Case[50][50];
		System.out.println("On cree un plateau");
		for(int i = 0; i < 50; i++)
			for(int j = 0; j < 50; j++)
				plateau[i][j] = new Case();
	}
	
	public void affiche(){
		for(int i = 0; i < 50; i++)
		{
			for(int j = 0; j < 50; j++)
				plateau[i][j].affiche();
			System.out.println();
		}
	
	}
	
	public void ajouterFourmi(int x, int y, int type)
	{
		if(plateau[x][y].getOccupante() == null)
		{
			switch(type)
			{
			case 1:
				plateau[x][y].ajoutFourmi(new FourmiRouge());
				break;
			}
		}
	}
	private int mod(int x, int y)
	{
		return x % y < 0 ? x % y + y : x % y;
	}
	
	public void update()
	{
		for(int i = 0; i < 50; i++)
			for(int j = 0; j < 50; j++)
				if(plateau[i][j].getOccupante() != null)
				{
					//reorientation de la fourmi
					int direction = plateau[i][j].getOccupante().tourner(plateau[i][j].getCouleur());
					
					if(plateau[i][j].getOccupante().getCouleur() == plateau[i][j].getCouleur())
						plateau[i][j].setCouleur(0);
					//modification de la couleur de la case
						plateau[i][j].setCouleur(plateau[i][j].getOccupante().getCouleur());
					
					//mouvement de la fourmi
					switch(direction)
					{
					case 0: //nord
						if(plateau[mod(i - 1 , 50)][j].getOccupante() == null)
						{
							plateau[mod(i - 1 , 50)][j].ajoutFourmi(plateau[i][j].getOccupante());
							plateau[i][j].supFourmi();
						}
						break;
						
					case 1: //est
						if(plateau[i][mod(j + 1 , 50)].getOccupante() == null)
						{
							plateau[i][mod(j + 1 , 50)].ajoutFourmi(plateau[i][j].getOccupante());
							plateau[i][j].supFourmi();
						}
						break;
						
					case 2: //sud
						if(plateau[mod(i + 1, 50)][j].getOccupante() == null)
						{
							plateau[mod(i + 1 , 50)][j].ajoutFourmi(plateau[i][j].getOccupante());
							plateau[i][j].supFourmi();
						}
						break;
						
					case 3: //est
						if(plateau[i][mod(j - 1 , 50)].getOccupante() == null)
						{
							plateau[i][mod(j - 1 , 50)].ajoutFourmi(plateau[i][j].getOccupante());
							plateau[i][j].supFourmi();
						}
						break;
					}
				}
	}

}
