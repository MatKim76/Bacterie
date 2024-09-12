import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;
import java.awt.event.*;

public class FenetreControl extends JFrame implements ActionListener, KeyListener
{
	private JPanel panelBtn;
	
	private JButton btnBocchi;
	private JButton btnSouris;
	private JButton btnMessage;
	private JButton btnFreeze;
	
	private JButton btnDeco;
	
	private JPanel pnlMessage;
	private JTextField txtMessage;
	
	// Ajout d'un flag pour suivre l'état d'activation
    private boolean isTracking = false;
    private ClientSimple2 clientSouris;
	
	public FenetreControl( String serv)
	{
		//création du client
		this.clientSouris = new ClientSimple2(serv);
		
		//para de base de la frame
		this.setSize(300,300);
		this.setLayout( new BorderLayout() );
		
		this.setTitle   ( "Controle" );
		this.setLocation( 400, 200 );
		
		// Ajout du KeyListener à la frame
        this.addKeyListener(this);
        this.setFocusable(true);
		
		//création du panel
		this.panelBtn = new JPanel();
		this.panelBtn.setLayout(new GridLayout(3,3));
		
		//Ajout du panel a la frame
		this.add(this.panelBtn, BorderLayout.CENTER);
		
		
		//création des boutons
		this.btnBocchi = new JButton("Spawn bocchi");
		this.btnBocchi.addActionListener(this);
		this.btnBocchi.setBackground(Color.PINK);
		
		this.btnSouris = new JButton("Suivre souris");
		this.btnSouris.addActionListener(this);
		this.btnSouris.setBackground(Color.WHITE);
		
		this.btnMessage = new JButton("Message");
		this.btnMessage.addActionListener(this);
		
		this.btnFreeze = new JButton("Freeze");
		this.btnFreeze.addActionListener(this);
		this.btnFreeze.setBackground(Color.WHITE);
		
		this.btnDeco = new JButton("Déconnexion");
		this.btnDeco.addActionListener(this);
		
		//Panel pour les messages
		this.txtMessage = new JTextField("");
		this.pnlMessage = new JPanel();
		this.pnlMessage.setLayout(new GridLayout(2,1));
		
		this.pnlMessage.add(this.btnMessage);
		this.pnlMessage.add(this.txtMessage);
		
		//Ajout des boutons
		this.panelBtn.add(this.btnBocchi);
		this.panelBtn.add(this.btnSouris);
		this.panelBtn.add(this.pnlMessage);
		this.panelBtn.add(this.btnFreeze);
		
		this.panelBtn.add(this.btnDeco);
		
		//mettre la frame visible
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed ( ActionEvent e)
	{
		if(e.getSource() == this.btnDeco)
		{
			this.clientSouris.deconnexion();
			this.dispose();
		}
		
		if(e.getSource() == this.btnBocchi)
		{
			this.clientSouris.envoiServeur("bocchi");
		}
		
		if(e.getSource() == this.btnSouris)
		{
			if (!isTracking)
            {
                // Démarrer le tracking
                
                new Thread(() -> {
                    this.clientSouris.startTracking();
                }).start();
                this.btnSouris.setText("Stop tracking souris");
                isTracking = true;
                this.btnSouris.setBackground(Color.GRAY);
            }
            else
            {
                // Arrêter le tracking
                this.clientSouris.stopTracking();
                this.btnSouris.setText("Start tracking souris");
                isTracking = false;
                this.btnSouris.setBackground(Color.WHITE);
            }
		}
		
		if(e.getSource() == this.btnMessage)
		{
			this.clientSouris.envoiServeur("message-" + this.txtMessage.getText());
			this.txtMessage.setText("");
		}
		
		if(e.getSource() == this.btnFreeze)
		{
			if(this.btnFreeze.getText().equals("Freeze"))
			{
				this.clientSouris.envoiServeur("fauxfreeze start");
				this.btnFreeze.setText("Unfreeze");
				this.btnFreeze.setBackground(Color.GRAY);
			}
			else
			{
				this.clientSouris.envoiServeur("fauxfreeze stop");
				this.btnFreeze.setText("Freeze");
				this.btnFreeze.setBackground(Color.WHITE);
			}
		}
	}
	
	public void keyTyped(KeyEvent e){}
	
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_D)
        {
            this.clientSouris.envoiServeur("souris clickD");
        }
        
        if (e.getKeyCode() == KeyEvent.VK_G)
        {
            this.clientSouris.envoiServeur("souris clickG");
        }
    }

    public void keyReleased(KeyEvent e){}
	
	public static void main(String[] a)
	{
		new FenetreControl("localhost");
	}
}


