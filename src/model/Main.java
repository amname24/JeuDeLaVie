package model;

public class Main {
	
	public static void main(String arg[]){
		
		Plateau p = new Plateau();
		p.ajouterFourmi(25, 25, 1);
		p.ajouterFourmi(0, 0, 1);
		p.affiche();
		for(int i = 0; i < 20; i++){
			System.out.println("aaaaaaaaaaaaa");
			p.update();
			p.affiche();
			
		}
		
	}

}
