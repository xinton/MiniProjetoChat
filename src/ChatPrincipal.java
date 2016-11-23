import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatPrincipal {
	
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(6515);
			Socket socket = server.accept();
			//DataInputStream input = new DataInputStream(socket.getInputStream());
			ThreadLine thr = new ThreadLine(socket.getInputStream());
			thr.start();
			DataOutputStream output = new DataOutputStream(socket.getOutputStream());
			String in = "";
			Scanner resposta = new Scanner(System.in);
			do {
//				in = input.readUTF();
//				System.out.println("mesagem recebida " +in);
				in = resposta.nextLine();
				output.writeUTF(in);
				
					
			}while(!in.equals("fim"));
			System.out.println("fim da conversa");
			
			socket.close();
			server.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

}
