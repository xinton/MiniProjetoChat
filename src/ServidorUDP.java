import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorUDP {

	public static void main(String[] args) {
		
		//Cria o DatagramSocket para aguardar mensagens, neste momento o método fica bloqueando
        //até o recebimente de uma mensagem
		
		try {
			DatagramSocket ds = new DatagramSocket(6500);
		
		//Preparando o buffer de recebimento da mensagem
        byte[] msgInput = new byte[512];
        
        //Prepara o pacote de dados
        DatagramPacket dpkg1 = new DatagramPacket(msgInput, msgInput.length);
		ds.receive(dpkg1);
		
		String op = new String(dpkg1.getData(),0,dpkg1.getLength());
		System.out.println("'"+op+"'");
		String msg = "";
		switch(op){
			case "1":
				msg = "OLAR1";
				break;
				
			case "2":
				msg = "OLAR2";
				break;
				
			case "3":
				msg = "OLAR3";
				break;
			default:
				msg = "GZUIZ";
				break;
		}
		
		
		////		
		byte[] msgOutput = msg.getBytes();

		//Monta o pacote a ser enviado
		DatagramPacket dpkg2 = new DatagramPacket(msgOutput,msgOutput.length, dpkg1.getAddress(), dpkg1.getPort());

		// Cria o DatagramSocket que será responsável por enviar a mensagem
		//DatagramSocket ds = new DatagramSocket();
		//Envia a mensagem
		ds.send(dpkg2);
		
		//Fecha o DatagramSocket
		ds.close();  
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
