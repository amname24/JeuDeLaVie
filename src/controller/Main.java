package controller;

import model.Plateau;

public class Main {
	
	public static void main(String arg[]){
		
		Plateau modele = new Plateau();
		new FourmiController(modele);
		return;
		
	}

}