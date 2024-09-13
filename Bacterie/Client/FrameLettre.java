

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.PrintWriter;

public class FrameLettre extends JFrame implements ActionListener
{
    private JTextField[] lstText;
	private char[] lstLettres;

	private JButton btnConfirmer;
	private Map<Character, Integer> keycodeMap;

    private ClientSimple2 client;

    public FrameLettre(ClientSimple2 client) 
	{
		this.client = client;
        
        this.lstText = new JTextField[26];
		this.lstLettres = new char[26];
		
		this.keycodeMap = new HashMap<>();
        
        // Ajouter les lettres et leurs keycodes
        keycodeMap.put('a', 24);
        keycodeMap.put('z', 25);
        keycodeMap.put('e', 26);
        keycodeMap.put('r', 27);
        keycodeMap.put('t', 28);
        keycodeMap.put('y', 29);
        keycodeMap.put('u', 30);
        keycodeMap.put('i', 31);
        keycodeMap.put('o', 32);
        keycodeMap.put('p', 33);
        keycodeMap.put('q', 38);
        keycodeMap.put('s', 39);
        keycodeMap.put('d', 40);
        keycodeMap.put('f', 41);
        keycodeMap.put('g', 42);
        keycodeMap.put('h', 43);
        keycodeMap.put('j', 44);
        keycodeMap.put('k', 45);
        keycodeMap.put('l', 46);
        keycodeMap.put('m', 47);
        keycodeMap.put('w', 52);
        keycodeMap.put('x', 53);
        keycodeMap.put('c', 54);
        keycodeMap.put('v', 55);
        keycodeMap.put('b', 56);
        keycodeMap.put('n', 57);


        // Configurer la fenêtre principale
        setTitle("Clé de Code des Lettres");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(27, 2)); // 26 lignes et 2 colonnes
        
		int i = 0;
        // Ajouter les lettres et les JTextField
        for (Map.Entry<Character, Integer> entry : keycodeMap.entrySet()) {
            
            // Créer un label pour la lettre et le keycode
            JLabel label = new JLabel(entry.getKey() + " (Keycode: " + entry.getValue() + "):");
            
            // Créer un JTextField pour la lettre
            JTextField textField = new JTextField(10);
            textField.setText(Character.toString(entry.getKey()));
            
            // Ajouter le label et le JTextField à la fenêtre
            add(label);
            add(textField);

			this.lstLettres[i] = entry.getKey();
			this.lstText[i] = textField;

			i++;
        }
        
		this.btnConfirmer = new JButton("Confirmer");
		this.btnConfirmer.addActionListener(this);

		this.add(this.btnConfirmer);

        
        // Afficher la fenêtre
        setVisible(true);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String s = "";
        for(int i = 0; i < this.lstLettres.length; i++)
        {
            String lettre = this.lstText[i].getText();
            s += "keycode " + keycodeMap.get(this.lstLettres[i]) + " = " + lettre + " " + lettre.toUpperCase() + " " +lettre + " " + lettre.toUpperCase() + ":";
        }
        
        client.envoiServeur(s);
	}
}
