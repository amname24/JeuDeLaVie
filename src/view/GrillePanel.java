package view;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import model.Plateau;

import java.awt.geom.*;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrillePanel extends JPanel
{
	private volatile Plateau grille = null;
	private BufferedImage  img;
	private BufferedImage  imgR;
	private BufferedImage  imgB;
	private BufferedImage  imgJ;
	private BufferedImage  imgIB;
	private BufferedImage  imgIH;
	private BufferedImage  imgIHVerte;
	private BufferedImage  imgfR;
	private BufferedImage  imgf;
	private BufferedImage  imgfB;
	private BufferedImage  imgfJ;
	private BufferedImage  imgfN;
	private BufferedImage  imgP;
	
	public GrillePanel()
	{
		super();
		try {
			imgIB = ImageIO.read(new File("./src/assets/terre.jpg") );
			imgR = ImageIO.read(new File("./src/assets/rouge.jpg") );
			imgB =ImageIO.read(new File("./src/assets/bleu.png") );
			imgJ = ImageIO.read(new File("./src/assets/jaune.jpg") );
			imgIH = ImageIO.read(new File("./src/assets/surface.jpg") );
			imgIHVerte = ImageIO.read(new File("./src/assets/surfaceverte.jpg") );
			imgfR = ImageIO.read(new File("./src/assets/fR.png") );
			imgfB = ImageIO.read(new File("./src/assets/fB.png") );
			imgfJ = ImageIO.read(new File("./src/assets/fJ.png") );
			imgfN = ImageIO.read(new File("./src/assets/fN.png") );
			imgP = ImageIO.read(new File("./src/assets/plant.png") );
		}catch (IOException e){				
		}
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
					{
						
						if(j%100<4) img =imgIHVerte;
						else if(j%100==4)img =imgIH;
						else img =imgIB;
						g2d.drawImage(img, i*10, j*10, 10, 10, null, null);
						
					}
					else
					{
						
						BufferedImage imgC = img;
						if( grille.getCouleurCase(i,j) ==1)
									img = imgR;
								
							
						if( grille.getCouleurCase(i,j) ==2)
							
							img = imgB;
							
						if( grille.getCouleurCase(i,j) ==3)
							
							img = imgJ;
							
						
						g2d.drawImage(img, i*10, j*10, 10, 10, null, null);
						
						img = imgC;
						
					}
					
					if(!grille.caseLibre(i, j))
					{
						
						BufferedImage imgC = img;
						if(grille.getCouleurFourmi(i, j)==1)
							imgf = imgfR;
						if(grille.getCouleurFourmi(i, j)==2)
							imgf = imgfB;
						if(grille.getCouleurFourmi(i, j)==3)
							imgf = imgfJ;
						if(grille.getCouleurFourmi(i, j)==4)
							imgf = imgfN;
						
						/*AffineTransform f = AffineTransform.getRotateInstance(-Math.PI*grille.getDirectionFourmi(i, j)/2, i*10, j*10);
						AffineTransformOp op = new AffineTransformOp(f, AffineTransformOp.TYPE_BILINEAR);
						BufferedImage im = op.filter(imgf, null);*/
						g2d.drawImage(imgf, i*10, j*10, 12, 12, null, null);
						img = imgC;
					}
					else if(grille.getCouleurCase(i, j)==-1)
					{
						
						BufferedImage imgC = img;
							
						g2d.drawImage(imgP, i*10, j*10, 10, 10, null, null);
						
						img = imgC;
					}
				} 
				else
					
				{
					if(j%100<4) img =imgIHVerte;
					else if(j%100==4)img =imgIH;
					else img =imgIB;
					g2d.drawImage(img, i*10, j*10, 10, 10, null, null);
				}
				
			}
		}
	}
}