package PetitMessage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.*;

public class PetitMessage extends JFrame
{
	public PetitMessage( String message )
	{
		this.setTitle   ( "Nouveau Message !" );
		
		this.setLocation( (int)(Math.random()*1500), (int)(Math.random()*1500));
		
		this.setSize(message.length()*10,100);
		
		this.add(new JLabel(message));
		
		this.setVisible(true);
	}
	
	public static void main(String[] a)
	{
		new PetitMessage("12345678901234567890123456789");
		new PetitMessage("yo");
		new PetitMessage("yo");
		new PetitMessage("yo");
		new PetitMessage("yo");
		new PetitMessage("yo");
		new PetitMessage("yo");
		new PetitMessage("yo");
		new PetitMessage("yo");
		new PetitMessage("yo");
		new PetitMessage("yo");
	}

}
