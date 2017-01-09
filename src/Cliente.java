
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {

	static Scanner kB;
	static boolean isRunning = true;

	public static void main(String[] args) {
		kB = new Scanner(System.in);
		try {
			//InetAddress address = InetAddress.getByName("198.162.0.105");
			//Socket s = new Socket(address, 1501);
			//Socket s = new Socket("localhost", 6500);
			Socket s = new Socket("192.168.0.105", 6500);
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
