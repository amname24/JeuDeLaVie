package model;

import java.util.Observable;

public class Plateau extends Observable implements Cloneable{
	private Case[][] plateau;
	public static final int size = 75;
	
	public Plateau()
	{
		plateau = new Case[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				plateau[i][j] = new Case();
	}
	
	/*public void affiche(){
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
				plateau[i][j].affiche();
			System.out.println();
		}
	
	}*/
	
	public void ajouterFourmi(int x, int y, int type)
	{
		if(plateau[x][y].getOccupante() == null)
		{
			switch(type)
			{
				case 1:
					plateau[x][y].ajoutFourmi(new FourmiRouge());
					break;
				case 2 : 
					plateau[x][y].ajoutFourmi(new FoumiBleue());
					break;
				case 3 :
					plateau[x][y].ajoutFourmi(new FourmiJaune());
					break;
				case 4:
					plateau[x][y].ajoutFourmi(new FourmiNoire());
					break;
			}
		}
		
		setChanged();
		notifyObservers(this);
	}
	
	public void ajouterPlante(int x, int y){
		plateau[x][y]= new Plante();
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
	
	public boolean casePlante(int i, int j)
	{
		return plateau[i][j].isPlante();
	}
	public void supPlante(int i, int j){
		plateau[i][j] = new Case();
		setChanged();
		notifyObservers(this);
	}
	
	public void supFourmi(int i, int j)
	{
		plateau[i][j] = new Case(plateau[i][j].getCouleur());
		setChanged();
		notifyObservers(this);
	}
	
	public void updateGrid()
	{
		Plateau p = new Plateau();
		p = (Plateau) this.clone();
		
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
			{
				if(plateau[i][j].getOccupante() != null)
				{
					//reorientation de la fourmi
					int direction = plateau[i][j].getOccupante().tourner(plateau[i][j].getCouleur());
					
					int couleur = getCouleurFourmi(i, j);
					
					//modification de la couleur de la case
					if(couleur == plateau[i][j].getCouleur())
						p.plateau[i][j].setCouleur(0);
					else if (!plateau[i][j].isPlante() && couleur != 4)
						p.plateau[i][j].setCouleur(couleur);
				
					//mouvement de la fourmi
					if(p.getCouleurFourmi(i, j) == couleur) // on vérifie que la fourmi n'a pas ete mangee
					{
						switch(couleur)
						{
						case 1: //fourmi rouge
							switch(direction)
							{
							case 0: //nord, pas jaune
								if(p.plateau[mod(i - 1 , size)][j].getOccupante() == null && p.getCouleurCase(mod(i - 1 , size), j) != 3)
								{
									p.plateau[mod(i - 1 , size)][j].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 1: //est, pas jaune
								if(p.plateau[i][mod(j + 1 , size)].getOccupante() == null && p.getCouleurCase(i, mod(j + 1 , size)) != 3)
								{
									p.plateau[i][mod(j + 1 , size)].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 2: //sud, pas jaune
								if(p.plateau[mod(i + 1, size)][j].getOccupante() == null && p.getCouleurCase(mod(i + 1 , size), j) != 3)
								{
									p.plateau[mod(i + 1 , size)][j].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 3: //ouest, pas jaune
								if(p.plateau[i][mod(j - 1 , size)].getOccupante() == null && p.getCouleurCase(i, mod(j - 1 , size)) != 3)
								{
									p.plateau[i][mod(j - 1 , size)].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
							}
							break;
							
						case 2: //fourmi bleue
							switch(direction)
							{
							case 0: //nord, pas rouge
								if(p.plateau[mod(i - 1 , size)][j].getOccupante() == null && p.getCouleurCase(mod(i - 1 , size), j) != 1)
								{
									p.plateau[mod(i - 1 , size)][j].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 1: //est, pas rouge
								if(p.plateau[i][mod(j + 1 , size)].getOccupante() == null && p.getCouleurCase(i, mod(j + 1 , size)) != 1)
								{
									p.plateau[i][mod(j + 1 , size)].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 2: //sud, pas rouge
								if(p.plateau[mod(i + 1, size)][j].getOccupante() == null && p.getCouleurCase(mod(i + 1 , size), j) != 1)
								{
									p.plateau[mod(i + 1 , size)][j].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 3: //ouest, pas rouge
								if(p.plateau[i][mod(j - 1 , size)].getOccupante() == null && p.getCouleurCase(i, mod(j - 1 , size)) != 1)
								{
									p.plateau[i][mod(j - 1 , size)].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
							}
							break;
							
						case 3: //fourmi jaune
							switch(direction)
							{
							case 0: //nord, pas bleu
								if(p.plateau[mod(i - 1 , size)][j].getOccupante() == null && p.getCouleurCase(mod(i - 1 , size), j) != 2)
								{
									p.plateau[mod(i - 1 , size)][j].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 1: //est, pas bleu
								if(p.plateau[i][mod(j + 1 , size)].getOccupante() == null && p.getCouleurCase(i, mod(j + 1 , size)) != 2)
								{
									p.plateau[i][mod(j + 1 , size)].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 2: //sud, pas bleu
								if(p.plateau[mod(i + 1, size)][j].getOccupante() == null && p.getCouleurCase(mod(i + 1 , size), j) != 2)
								{
									p.plateau[mod(i + 1 , size)][j].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
								
							case 3: //ouest, pas bleu
								if(p.plateau[i][mod(j - 1 , size)].getOccupante() == null && p.getCouleurCase(i, mod(j - 1 , size)) != 2)
								{
									p.plateau[i][mod(j - 1 , size)].ajoutFourmi(plateau[i][j].getOccupante());
									p.plateau[i][j].supFourmi();
								}
								break;
							}
							break;
							
						case 4:
							int xcible = 0, ycible = 0,xmin=200,ymin=200;
							boolean trouvecible = false;
							for(int k = 0; k < size; k++)
								for(int l = 0; l < size ; l++)
								{
									if(p.plateau[k][l].getOccupante() != null && p.plateau[k][l].getOccupante().getCouleur() != 4 )
									{
										if((i-k)*(i-k)+(j-l)*(j-l)<(i-xmin)*(i-xmin)+(j-ymin)*(j-ymin))
										{
											System.out.println("je"+k+" "+l);
											xmin = k;
											ymin = l;
											trouvecible = true;
										}
									}
								}
							xcible = xmin;
							ycible = ymin;
							if(trouvecible)
							{
								xcible = i - xcible;
								ycible = j - ycible;
								
								if(xcible < 0)
									xcible = -1;
								else if(xcible > 0)
									xcible = 1;
								if(ycible < 0)
									ycible = -1;
								else if(ycible > 0)
									ycible = 1;
								
								if(p.plateau[mod(i - xcible, size)][mod(j - ycible , size)].getOccupante() == null || p.getCouleurFourmi(mod(i - xcible, size), mod(j - ycible , size)) != 4)
								{
									p.plateau[i][j].supFourmi();
									p.plateau[mod(i - xcible, size)][mod(j - ycible , size)].ajoutFourmi(plateau[i][j].getOccupante());
								}
							}
							break;
						}
					}
				}
					
				
				if (plateau[i][j].isPlante())
					if(plateau[i][j].getOccupante()!= null)
					{
						int couleurOcupante = plateau[i][j].getOccupante().getCouleur();
						boolean estmort = plateau[i][j].estMort();
						((Plante)p.plateau[i][j]).estMort();
						if(estmort)
						{
							//System.out.print("morte");
							p.plateau[i][j] = new Case();
						}	
						Fourmi f ;
							switch(couleurOcupante){
							case 1 :
								f= new FourmiRouge();
								p.plateau[i][j].ajoutFourmi(f);
								break;
							case 2 :
								f = new FoumiBleue();
								p.plateau[i][j].ajoutFourmi(f);
								break;
							case 3 :
								f = new FourmiJaune();
								p.plateau[i][j].ajoutFourmi(f);
								break;
						
						}
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
					p.plateau[i][j] =(Case) plateau[i][j].clone();
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
	
	public int getCouleurFourmi(int i, int j)
	{
		if(plateau[i][j].getOccupante()!=null)
			return(plateau[i][j].getOccupante().getCouleur());
		else 
			return -1;
	}
	
}
