package FauxFreeze;

import javax.swing.*;
import java.awt.*;

public class FauxFreeze extends JDialog
{
    //taille ecran
    private int screenWidth;
    private int screenHeight;
    
    public FauxFreeze()
	{
        // Obtenir la taille de l'écran
        this.screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        this.setBounds(0, 0, screenWidth, screenHeight);
        
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
		this.setAlwaysOnTop(true);
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		this.setVisible(true);
	}
	
    public static void main(String[] args) 
    {
    	new FauxFreeze();
    }
}
