package BocchiEternel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class PanelStarry extends JPanel
{
	private Image img;
	
	public PanelStarry( String fichier )
	{
		this.img = getToolkit().getImage ( fichier );
		this.setBackground(new Color(0,0,0,0));
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		//Image img = getToolkit().getImage ( "./images/BlobBocchi.gif" );
		g.drawImage( this.img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	public void setImage(String fichier)
	{
		this.img = getToolkit().getImage ( fichier ); //getToolkit().getImage(getClass().getResource("../images/" + fichier));
		repaint();
		
	}
}
