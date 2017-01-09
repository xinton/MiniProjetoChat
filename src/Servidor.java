import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import model.MaquinaCliente;

public class Servidor {
	private static List<MaquinaCliente> maquinasClientesOnline = new ArrayList<MaquinaCliente>();
	public static void main(String[] args) {
		
		//Cria o DatagramSocket para aguardar mensagens, neste momento o m�todo fica bloqueando
        //at� o recebimente de uma mensagem
		
		try {
			ServerSocket ss = new ServerSocket (6500);			
			do{
				Socket socket = ss.accept();
				MaquinaCliente maquinaCliente = new MaquinaCliente(socket);
				maquinasClientesOnline.add(maquinaCliente);
				ServerListener serverListener = new ServerListener(maquinaCliente, maquinasClientesOnline);
				serverListener.start();
				
			}while(true);
			
			//ss.close();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
