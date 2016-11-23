import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServerListener extends Thread{

	Socket s;
	public ServerListener(Socket s) {
		this.s = s;
	}

	public void run(){
		try {
			String msg = "";
			do{
				DataInputStream dataIn = new DataInputStream(s.getInputStream());
				String op = dataIn.readUTF();
				System.out.println("'"+op+"'");
				
				switch(op){
				case "bye":
					msg = "fim";
					s.close();
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
				
				DataOutputStream dataOutput = new DataOutputStream(s.getOutputStream());
				//executa alguma coisa... no caso, um eco.
				dataOutput.writeUTF(msg);
				
//				ServerWriter serverwriter = new ServerWriter(s,msg);
//				serverwriter.start();
			}while(!s.isClosed());			
			s.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
