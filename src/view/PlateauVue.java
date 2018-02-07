package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import controller.*;
import model.Plateau;

//import Controller/VieControleur;

import java.util.Observable;
import java.util.Observer;

public class PlateauVue implements Observer
{
	private JFrame mainFrame;
	private GrillePanel grille;
	private JButton btNG, btReset, btPlay, btStop,btPlante;
	private JButton btFR,btFB,btFJ, btFN;
	private FourmiController control;
	private JSlider slider;
	private Boolean isPlante;
	public int couleur;
	
	public PlateauVue(FourmiController control)
	{
		this.control = control;
		initComponents();
		mainFrame.setVisible(true);
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		isPlante = false;
		couleur = 0;
	}
	
	public void update(Observable o , Object arg)
	{
		Plateau ngrille = (Plateau) arg;
		grille.setGrille(ngrille);
		grille.repaint();
	}
	
	private void initComponents()
	{
		mainFrame = new JFrame();
		mainFrame.setTitle("Ant and Plant");
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setSize(Plateau.size*10 + 10, Plateau.size*10 + 120);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Box mainbox = Box.createVerticalBox();
		
		grille = new GrillePanel();
		grille.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			
			public void mouseEntered(MouseEvent e) {}
	
			public void mouseExited(MouseEvent e) {}
	
			public void mousePressed(MouseEvent e) {
				if (isPlante)
					control.AddPlante(e.getX()/10, e.getY()/10);
				else
					control.modifyCell(e.getX() / 10, e.getY() / 10,couleur);
			}
	
			public void mouseReleased(MouseEvent e) {}
		});
		mainbox.add(grille);
		
		Box buttonbox = Box.createHorizontalBox();
		btNG = new JButton("<html>Generation<br>suivante</html>");
		btNG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.inputNextGeneration();
			}
		});
		
		btPlante = new JButton("<html>ajouter une<br>Plante</html>");
		btPlante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPlante = true;
			}
		});
		
		btReset = new JButton("<html>Remise a<br>zero</html>");
		btReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.resetGrid();
			}
		});
		
		Box animbox = Box.createHorizontalBox();
		btPlay = new JButton("Demarrer");
		btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.beginAnimation();
				btNG.setEnabled(false);
				btReset.setEnabled(false);
				btPlay.setEnabled(false);
				btPlante.setEnabled(false);
				btStop.setEnabled(true);
			}
		});
		
		btStop = new JButton("Arreter");
		btStop.setEnabled(false);		
		btStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.stopAnimation();
				btNG.setEnabled(true);
				btPlante.setEnabled(true);
				btReset.setEnabled(true);
				btPlay.setEnabled(true);
				btStop.setEnabled(false);
			}
		});
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				control.setAnimationSpeed(((100 - slider.getValue()) * 3));
			}
		});
		
		btFR = new JButton("Fourmi rouge");
		btFR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPlante = false;
				couleur = 1;
			}
		});
		btFB = new JButton("Fourmi bleue");
		btFB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPlante = false;
				couleur = 2;
			}
		});
		btFJ = new JButton("Fourmi jaune");
		btFJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPlante = false;
				couleur = 3;
			}
		});
		btFN = new JButton("Fourmi noire");
		btFN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isPlante = false;
				couleur = 4;
			}
		});
		
		buttonbox.add(btReset);
		buttonbox.add(btNG);
		buttonbox.add(btFR);
		buttonbox.add(btFB);
		buttonbox.add(btFJ);
		buttonbox.add(btFN);
		buttonbox.add(btPlante);
		animbox.add(btPlay);
		animbox.add(btStop);
		animbox.add(slider);
		
		mainbox.add(Box.createVerticalStrut(5));
		mainbox.add(buttonbox);
		mainbox.add(Box.createVerticalStrut(5));
		mainbox.add(animbox);
		mainbox.add(Box.createVerticalStrut(5));
		
		mainFrame.setContentPane(mainbox);
	}

	private ActionListener addActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		return null;
	}
}