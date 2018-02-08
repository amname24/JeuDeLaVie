package view;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Plateau;

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
			BufferedImage imgfRO = ImageIO.read(new File("./src/assets/fR.png") );
			imgfR = resize(imgfRO,12,12);
			BufferedImage imgfBO = ImageIO.read(new File("./src/assets/fB.png") );
			imgfB = resize(imgfBO,12,12);
			BufferedImage imgfJO = ImageIO.read(new File("./src/assets/fJ.png") );
			imgfJ = resize(imgfJO,12,12);
			BufferedImage imgfNO = ImageIO.read(new File("./src/assets/fN.png") );
			imgfN = resize(imgfNO,12,12);
			BufferedImage imgPO = ImageIO.read(new File("./src/assets/plant.png") );
			imgP = resize(imgPO,10,10);
		}catch (IOException e){}
		
	}
	
	public void setGrille(Plateau p)
	{
		this.grille = p;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		/*AffineTransform at = g2d.getTransform();
		g2d.scale(1, -1);
		g2d.setTransform(at);*/
		for(int i = 0; i < Plateau.size; i++) {
			for(int j = 0; j < Plateau.size; j++) {
				if((grille != null)) 
				{
					if(grille.getCouleurCase(i, j) == 0)
					{
						img =imgIB;
						g2d.drawImage(img, j*10, i*10, 10, 10, null, null);
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
						g2d.drawImage(img, j*10, i*10, 10, 10, null, null);
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
						g2d.drawImage(imgf, j*10, i*10, imgf.getWidth(), imgf.getHeight(), null, null);
						img = imgC;
					}
					else if(grille.getCouleurCase(i, j)==-1)
					{
						BufferedImage imgC = img;
						g2d.drawImage(imgP, j*10, i*10, imgP.getWidth(), imgP.getHeight(), null, null);
						img = imgC;
					}
				} 
				else	
				{
					img =imgIB;
					g2d.drawImage(img, j*10, i*10, 10, 10, null, null);
				}
			}
		}
	}
	private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}