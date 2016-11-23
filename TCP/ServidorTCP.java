import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServidorTCP {

	public static void main(String[] args) {
		
		//Cria o DatagramSocket para aguardar mensagens, neste momento o método fica bloqueando
        //até o recebimente de uma mensagem
		
		try {
			ServerSocket ss = new ServerSocket (6500);			
			do{
				Socket socket = ss.accept();
				
				ServerListener serverlis = new ServerListener(socket);
				serverlis.start();
				
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
