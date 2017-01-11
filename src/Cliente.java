
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
			Socket s = new Socket("localhost", 6500);
			//Socket s = new Socket("192.168.0.105", 6500);
			Listener lis = new Listener(s);
			lis.start();
			DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
			System.out.println("Mini Chat (Digite 'bye' pra sair)");
			String outMsg = "";
			System.out.print("Out: ");
			/*do{
				if(!s.isClosed()){				
					outMsg = kB.nextLine();
					dataOut.writeUTF(outMsg);
				}
				else{break;}
			}while(!outMsg.equals("fim"));*/
			do{						
				outMsg = kB.nextLine();
				if (!s.isClosed()){
					dataOut.writeUTF(outMsg);
				} else {break;}
				
			}while(!s.isClosed());
			//s.close();
			//System.out.println("Chat Finalizado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}