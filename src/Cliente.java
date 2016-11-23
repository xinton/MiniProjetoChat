
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {

	static Scanner kB;
	static boolean isRunning = true;

	public static void main(String[] args) {
		kB = new Scanner(System.in);
		try {
			Socket s = new Socket("localhost", 6500);
			//Socket s = new Socket("10.0.4.100", 6500);
			Listener lis = new Listener(s);
			lis.start();
			DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
			System.out.println("Nano Mesenger V1.0 (Digite 0 pra sair)");
			String outMsg = "";
			do{
				if(!s.isClosed()){
					System.out.print("Out: ");
					outMsg = kB.nextLine();
					dataOut.writeUTF(outMsg);
				}
				else{break;}
			}while(!outMsg.equals("fim"));
			s.close();
			System.out.println("Conversa Finalizada!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
