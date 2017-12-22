package model;

import java.util.Observable;

public class Plateau extends Observable implements Cloneable{
	private Case[][] plateau;
	public static final int size = 100;
	
	public Plateau()
	{
		plateau = new Case[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				plateau[i][j] = new Case();
	}
	
	public void affiche(){
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
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
		
		setChanged();
		notifyObservers(this);
	}
	
	private int mod(int x, int y)
	{
		return x % y < 0 ? x % y + y : x % y;
	}
	
	public boolean caseLibre(int i, int j)
	{
		return(plateau[i][j].getOccupante() == null);
	}
	
	public void supFourmi(int i, int j)
	{
		plateau[i][j] = new Case(plateau[i][j].getCouleur());
	}
	public void updateGrid()
	{
		Plateau p = new Plateau();
		p = (Plateau) this.clone();
		
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				if(plateau[i][j].getOccupante() != null)
				{
					//reorientation de la fourmi
					int direction = plateau[i][j].getOccupante().tourner(plateau[i][j].getCouleur());

					//modification de la couleur de la case
					if(plateau[i][j].getOccupante().getCouleur() == plateau[i][j].getCouleur())
						p.plateau[i][j].setCouleur(0);
					else
						p.plateau[i][j].setCouleur(plateau[i][j].getOccupante().getCouleur());
					
					//mouvement de la fourmi
					switch(direction)
					{
					case 0: //nord
						if(plateau[mod(i - 1 , size)][j].getOccupante() == null)
						{
							p.plateau[mod(i - 1 , size)][j].ajoutFourmi(plateau[i][j].getOccupante());
							p.plateau[i][j].supFourmi();
						}
						break;
						
					case 1: //est
						if(plateau[i][mod(j + 1 , size)].getOccupante() == null)
						{
							p.plateau[i][mod(j + 1 , size)].ajoutFourmi(plateau[i][j].getOccupante());
							p.plateau[i][j].supFourmi();
						}
						break;
						
					case 2: //sud
						if(plateau[mod(i + 1, size)][j].getOccupante() == null)
						{
							p.plateau[mod(i + 1 , size)][j].ajoutFourmi(plateau[i][j].getOccupante());
							p.plateau[i][j].supFourmi();
						}
						break;
						
					case 3: //ouest
						if(plateau[i][mod(j - 1 , size)].getOccupante() == null)
						{
							p.plateau[i][mod(j - 1 , size)].ajoutFourmi(plateau[i][j].getOccupante());
							p.plateau[i][j].supFourmi();
						}
						break;
					}
				}
		this.plateau = p.plateau.clone();
		
		setChanged();
		notifyObservers(this);
	}
	
	public Object clone()
	{
		Plateau p = new Plateau();
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
			{
				if(this.plateau[i][j].getOccupante()!=null)
					p.plateau[i][j].ajoutFourmi((Fourmi)plateau[i][j].getOccupante().clone());;
				p.plateau[i][j].setCouleur(plateau[i][j].getCouleur()); 
			}
		return p;	
		
	}

	public void resetGrid() {
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				plateau[i][j] = new Case();
	}
	
	public int getCouleurCase(int i, int j)
	{
		return(plateau[i][j].getCouleur());
	}
}
