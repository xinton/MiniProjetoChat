import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


public class Listener extends Thread{

	Socket s;
	public Listener(Socket s) {
		this.s = s;
	}
	
	public void run(){
		try {
			DataInputStream dataIn = new DataInputStream(s.getInputStream());
			System.out.println("Pronto pra receber Mensagens!");
			String inMsg = "";
			do{
				inMsg = dataIn.readUTF();
				if(!inMsg.equals("fim")){					
					System.out.println("In: "+inMsg);
					System.out.print("Out: ");
				}else{break;}				
			}while(!s.isClosed());
			System.out.println("Chat Finalizado");
			s.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
