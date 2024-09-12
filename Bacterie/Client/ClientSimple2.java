import java.net.*;
import java.io.*;
import java.awt.MouseInfo;
import java.awt.Point;

public class ClientSimple2
{
    private static int portNumber = 9000;
    private volatile boolean isRunning = true;
    private Socket toServer;
    private PrintWriter out;

    public ClientSimple2(String serveur)
    {
        try
        {
            System.out.println("Connexion au serveur...");
            toServer = new Socket(serveur, portNumber);
            System.out.println("Connecté...");
            
            out = new PrintWriter(toServer.getOutputStream(), true);
        } 
        catch (IOException e) 
        {
            System.out.println("Erreur de connexion");
        }
    }
    
    public void deconnexion()
    {
    	try
        {
            out.close();
			toServer.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    // Méthode pour démarrer le suivi
    public void startTracking()
    {
        isRunning = true;
        try
        {
            while (isRunning)
            {
                // Obtenir la position de la souris
                Point position = MouseInfo.getPointerInfo().getLocation();
                String message = "souris move:" + position.x + ":" + position.y;
                out.println(message);
                
                // Pause pour limiter la fréquence des envois
                Thread.sleep(10);
            }
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    // Méthode pour arrêter le suivi
    public void stopTracking()
    {
        isRunning = false;
    }
    
    public void envoiServeur(String msg)
    {
    	try
        {
            out.println(msg);
            Thread.sleep(100);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}

