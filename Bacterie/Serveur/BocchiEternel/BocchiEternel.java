package BocchiEternel;

import java.awt.*;
import java.awt.event.*;
import java.awt.Toolkit;

import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.MouseInfo;
import java.awt.Point;

public class BocchiEternel extends JDialog implements ActionListener
{
    private static int vitesse = 6; //vitesse de déplacement
    private static int temps = (int)(Math.random() * 4000) + 1000; //temps d'apparition
    
    private PanelStarry panel;
    private int posX;
    private int posY;
    
    //taille ecran
    private int screenWidth;
    private int screenHeight;
    
    Timer timer;
    
    private String nom; //image
	
	private int taille; //taille de l'image

    private int survie;

    public BocchiEternel(String nom, int taille)
	{
		this.nom = nom;
        this.taille = taille;

        this.panel = new PanelStarry("./BocchiEternel/images/" + this.nom + ".gif");
        this.add(this.panel);

        this.setSize(this.taille, this.taille);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setAlwaysOnTop(true);

        this.setVisible(true);

        // Obtenir la taille de l'écran
        this.screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        

        // Coordonnées initiales sur un côté de l'écran
        this.posX = (int)(Math.random() * this.screenWidth) ;
        this.posY = (int)(Math.random() * this.screenHeight) ;
        
        this.setLocation(posX, posY);
		
		//this.setCuror(new Cursor("nomimage"));
		
		this.survie = 0;
		
		//lancer le déplacement
		timer = new Timer(20, this);
		timer.start();
	}

	public void setPos(int x, int y)
	{
		this.posX = x;
		this.posY = y;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// Obtenir la position de la souris
        Point position = MouseInfo.getPointerInfo().getLocation();
		
		double distanceX = position.x - this.posX - this.taille/2;
		double distanceY = position.y - this.posY - this.taille/2;
		double distanceTotale = Math.sqrt(distanceX * distanceX + distanceY * distanceY);
		
		//System.out.println(distanceTotale);
		
		if (distanceTotale > 0)
		{
			double ratio = BocchiEternel.vitesse / distanceTotale;
			this.posX += (int) (ratio * distanceX);
			this.posY += (int) (ratio * distanceY);
			
			//System.out.println((int) (ratio * distanceX) + "  " + (int) (ratio * distanceY) + "  " + ratio);
		}
		
		//Bocchi a attrapé ta souris
		if (distanceTotale <= this.taille/2)
		{
			//mort
			System.out.println("DEAD");
			this.dispose();
			timer.stop();
			deconnexion();
		}
		
		this.setLocation(this.posX, this.posY);
		
		this.survie++;
		//System.out.println(this.survie);
		
		if( this.survie >= BocchiEternel.temps)
		{
			this.dispose();
			timer.stop();
		}
	}
	
	public void deconnexion()
    {
        try {
            // Changer le chemin ici
            Process process = Runtime.getRuntime().exec("mate-terminal -e \"/home/etudiant/sm220306/MesJeux/BocchiEternel/test.sh\"");

            // Attend que la commande se termine
            process.waitFor();

            // Récupère le code de sortie de la commande
            int exitCode = process.exitValue();
            System.out.println("La commande s'est terminée avec le code de sortie : " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) 
    {
    	try
    	{
    		int dodo = (int)(Math.random() * 60); //minute
    		System.out.println(dodo);
    		dodo = dodo * 60000; //milisecondes
    		
    		Thread.sleep( dodo );
    		
    	}catch (Exception e) {
            e.printStackTrace();
        }
    	
    	new BocchiEternel("BlobBocchi", 50);
    }
}
