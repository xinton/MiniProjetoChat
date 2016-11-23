import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;

public class ClienteTCP {
public static void main(String[] args) throws SocketException {
	try {
		InetAddress address = InetAddress.getByName("localhost");
		
		Socket socket = new Socket(address,6500);
		
		//obtém o stream de saída e o encapsula
		DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream());

		//obtém o stream de entrada e o encapsula
		DataInputStream dataInput = new DataInputStream(socket.getInputStream());
		
		//executa alguma coisa... no caso, envia uma mensagem
		//e espera resposta.
		dataOutput.writeUTF("4");

		String response = dataInput.readUTF();
		System.out.println(response);
		
		socket.close();
	} catch(Exception e) {
		System.out.println("Erro: " + e.getMessage());
	}
		
	}
}
