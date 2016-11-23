import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ThreadLine extends Thread{
	private InputStream in;
	
	public ThreadLine(InputStream in){
		this.in= in;
	}
	
	public void run(){
		DataInputStream tin = new  DataInputStream(in);
		String msg = null;
		do{
			try {
				msg = tin.readUTF();
				System.out.println("Recebida: " + msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}while(!msg.equals("fim"));
		
	}
	
	
	
	
	

}
