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
	private JButton btNG, btReset, btPlay, btStop, btRand;
	private FourmiController control;
	private JSlider slider;
	
	public PlateauVue(FourmiController control)
	{
		this.control = control;
		initComponents();
		mainFrame.setVisible(true);
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
				control.modifyCell(e.getX() / 10, e.getY() / 10);
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
		btReset = new JButton("<html>Remise a<br>zero</html>");
		btReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.resetGrid();
			}
		});
		
		/*btRand = new JButton("<html>Rand<br>lol</html>");
		btRand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.Randlol();
			}
		});*/
		
		Box animbox = Box.createHorizontalBox();
		btPlay = new JButton("Demarrer");
		btPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.beginAnimation();
				btNG.setEnabled(false);
				btReset.setEnabled(false);
				btPlay.setEnabled(false);
				btStop.setEnabled(true);
				//btRand.setEnabled(false);
			}
		});
		
		btStop = new JButton("Arreter");
		btStop.setEnabled(false);		
		btStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.stopAnimation();
				btNG.setEnabled(true);
				btReset.setEnabled(true);
				btPlay.setEnabled(true);
				btStop.setEnabled(false);
				//btRand.setEnabled(true);
			}
		});
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				control.setAnimationSpeed(((100 - slider.getValue()) * 3));
			}
		});
		
		buttonbox.add(btReset);
		buttonbox.add(btNG);
		//buttonbox.add(btRand);
		
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
}