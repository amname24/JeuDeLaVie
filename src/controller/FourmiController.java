package controller;

import java.util.Observer;

import model.*;
import view.*;

public class FourmiController
{
	private Plateau modele;
	private PlateauVue vue1;
	private volatile Thread threadanim = null;
	private int animSpeed = 150;
	
	public FourmiController(Plateau modele)
	{
		this.modele = modele;
		vue1 = new PlateauVue(this);
		modele.addObserver((Observer) vue1);
	}
	
	public void modifyCell(int x, int y)
	{
		if(modele.caseLibre(x, y))
			modele.ajouterFourmi(x, y, 1);
		else
			modele.supFourmi(x, y);
	}
	
	public void inputNextGeneration()
	{
		if(threadanim != null)
			return;
		
		modele.updateGrid();
	}
	
	public void resetGrid()
	{
		if(threadanim != null)
			return;
		
		modele.resetGrid();
		modele.updateGrid();
	}
	
	/*public void Randlol()
	{
		if(threadanim != null)
			return;
		
		modele.Randlol();
		modele.updateGrid();
	}*/
	
	public void beginAnimation()
	{
		threadanim = new Thread(new Runnable() {
			public void run() {
				while(threadanim != null) {
					modele.updateGrid();
					try {
						Thread.sleep(animSpeed);
					} catch (InterruptedException e){
						break;
					}
				}
			}
		});
		threadanim.start();
	}
	
	public void stopAnimation()
	{
		if(threadanim != null) {
			threadanim.interrupt();
			threadanim = null;
		}
	}
	
	public void setAnimationSpeed(int newspeed)
	{
		animSpeed = newspeed;
	}
}