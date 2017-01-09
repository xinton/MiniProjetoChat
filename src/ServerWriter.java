import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ServerWriter extends Thread{

	Socket s;
	String msg;
	public ServerWriter(Socket s,String msg) {
		this.s = s;
		this.msg = msg;
	}

	public void run(){
		try {
			DataOutputStream dataOutput = new DataOutputStream(s.getOutputStream());
			//executa alguma coisa... no caso, um eco.
			dataOutput.writeUTF(msg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}