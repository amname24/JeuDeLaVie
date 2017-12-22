package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import model.Plateau;

import java.awt.geom.*;

public class GrillePanel extends JPanel
{
	private volatile Plateau grille = null;
	
	public GrillePanel()
	{
		super();
	}
	
	public void setGrille(Plateau p)
	{
		this.grille = p;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = g2d.getTransform();
		g2d.scale(1, -1);
		g2d.setTransform(at);
		for(int i = 0; i < Plateau.size; i++) {
			for(int j = 0; j < Plateau.size; j++) {
				if((grille != null)) 
				{
					if(grille.getCouleurCase(i, j) == 0)
						g2d.draw(new Rectangle(i*10, j*10, 10, 10));
					else
						g2d.fill(new Rectangle(i*10, j*10, 10, 10));
					
					if(!grille.caseLibre(i, j))
					{
						Color c = g2d.getColor();
						g2d.setColor(Color.RED);
						g2d.fill(new Rectangle(i*10+2, j*10+2, 6, 6));
						g2d.setColor(c);
					}
				} 
				else
					g2d.draw(new Rectangle(i*10, j*10, 10, 10));
			}
		}
	}
}