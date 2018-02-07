package model;

public class Plante extends Case {
	
	private int dureeDeVie;
		
	public Plante(){
		dureeDeVie = 2;
		this.setCouleur(-1);
	}
	
	public boolean estMort(){
		dureeDeVie--;
		return dureeDeVie == 0;
	}
	
	public void setDureeDeVie(int d){
		dureeDeVie = d;
	}
	
	public int getdureeDeVie(){
		return dureeDeVie;
	}
	
	public Object clone(){
		Plante c = new Plante();
		if(getOccupante()!=null)
			c.ajoutFourmi((Fourmi)getOccupante().clone());
		c.setCouleur(getCouleur());
		c.setDureeDeVie(getdureeDeVie());
		return c;
	}
	
	public boolean isPlante(){
		return true;
	}
	
}
