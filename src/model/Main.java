package model;

public class Main {
	
	public static void main(String arg[]){
		
		Plateau p = new Plateau();
		p.ajouterFourmi(25, 25, 1);
		p.ajouterFourmi(0, 0, 1);
		p.affiche();
		System.out.println("aaaaaaaaaaaaa");
		p.update();
		p.affiche();
		System.out.println("aaaaaaaaaaaaa");
		p.update();
		p.affiche();
		System.out.println("aaaaaaaaaaaaa");
		p.update();
		p.affiche();
		System.out.println("aaaaaaaaaaaaa");
		p.update();
		p.affiche();
		
	}

}
