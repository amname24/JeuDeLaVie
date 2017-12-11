package model;

public interface Fourmi {
	
	public int getDirection();
	
	public int getCouleur();
	
	//retourne la nouvelle orientation de la Fourmi
	public int tourner(int couleur);

}