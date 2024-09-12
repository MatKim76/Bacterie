import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class RechercheServeur implements Runnable
{
	private static int port = 12121;

	private String serv;
	//private ArrayList<String> serverList;
	private DefaultTableModel model;

	//public RechercheServeur(String serv, ArrayList<String> serverList)
	public RechercheServeur(String serv, DefaultTableModel model)
	{
		this.serv = serv;
		//this.serverList = serverList;
		this.model = model;
	}

	@Override
	public void run()
	{
		try
		{
			Socket socket = new Socket(this.serv, port);
			//this.serverList.add(this.serv);
			model.addRow(new Object[]{this.serv});
			socket.close();
			
			
		} catch (Exception e) {}
	}
}
