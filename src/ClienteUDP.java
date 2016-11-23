import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ClienteUDP {
public static void main(String[] args) throws SocketException {
	DatagramSocket ds = new DatagramSocket();
	try {
		String msgInput = "1";
        //Prepara o pacote de dados
        DatagramPacket dpkg1 = new DatagramPacket(msgInput.getBytes(), msgInput.length(),
        		InetAddress.getLocalHost(),6500);
		ds.send(dpkg1);

        DatagramPacket resp = new DatagramPacket(
                new byte[1024],1024);
        ds.receive(resp);
        System.out.println(new String(resp.getData()));
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
