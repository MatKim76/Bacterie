import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class ConnexionServeur extends JFrame implements ActionListener
{
	private JPanel panelBtn;
	
	private JButton btnCo;
	
	private JTable tableauServeur;
    private JScrollPane scrollPane;
	
	public ConnexionServeur( )
	{
		//para de base de la frame
		this.setSize(300,300);
		this.setLayout( new BorderLayout() );
		
		this.setTitle   ( "Controle" );
		this.setLocation( 400, 200 );
		
		this.panelBtn = new JPanel();
		this.panelBtn.setLayout(new GridLayout(2,1));
		
		this.btnCo = new JButton("Se connecter");
		this.btnCo.addActionListener(this);
		
		//Création du tableau de la liste des serveurs
        this.tableauServeur = new JTable(0, 1);
        this.tableauServeur.setDragEnabled(false);
        this.tableauServeur.getColumnModel().getColumn(0).setHeaderValue("Serveurs IUT disponibles :");
        this.tableauServeur.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tableauServeur.setDefaultEditor(Object.class, null);
        chercherServ();

        this.scrollPane = new JScrollPane(this.tableauServeur);
        this.scrollPane.setBounds(20, 20, 250, 150);
		
		this.panelBtn.add(this.scrollPane);
		this.panelBtn.add(this.btnCo);
		
		this.add(panelBtn);
		
		//mettre la frame visible
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void chercherServ()
	{
		//j'espère que ca pose pas trop de problème car lance au max 50 Thread
		ExecutorService executor = Executors.newFixedThreadPool(50);
		
		String s = "di-";
		String s2 = "c-";
		int num;

        ArrayList<String> lstServ = new ArrayList<String>();
        
        DefaultTableModel model = (DefaultTableModel) tableauServeur.getModel();
        model.setRowCount(0);

        executor.execute(new RechercheServeur("di-722-tr", model));

		for(num = 715; num < 730; num++)
		{
			for(int pc = 0; pc < 30; pc++)
			{
				String serv1 = s + num + "-" + String.format("%02d", pc);
				String serv2 = s2 + serv1;
				
				
				executor.execute(new RechercheServeur(serv1, model));
                executor.execute(new RechercheServeur(serv2, model));
			}
		}
		

		System.out.println("Fin recherche serveur");
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == this.btnCo)
		{
			//vérif de serveur
            String serveur = "";
            if( serveur.equals("") )
            {
                if( this.tableauServeur.getSelectedRow() == -1)
                {
                    return;
                }
                else
                {
                    serveur = (String) this.tableauServeur.getValueAt(this.tableauServeur.getSelectedRow(), 0);
                    new FenetreControl(serveur);
                }
            }
		}
	}
	
	public static void main(String[] a)
	{
		new ConnexionServeur();
	}
}
