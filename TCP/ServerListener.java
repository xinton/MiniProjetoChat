import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import model.MaquinaCliente;


public class ServerListener extends Thread{
	private List<MaquinaCliente> maquinasClientes = new ArrayList<MaquinaCliente>();
	private MaquinaCliente maquinaCliente;
	Socket s;

	public ServerListener(MaquinaCliente maquinaCliente, List<MaquinaCliente> maquinasClientes) {
		this.maquinaCliente = maquinaCliente;
		this.s = maquinaCliente.getSocketCliente();
		this.maquinasClientes = maquinasClientes;
	}

	public void run(){
		try {
			String msg = "";
			do{
				DataInputStream dataIn = new DataInputStream(s.getInputStream());
				/**
				 * @todo isso aqui tá uma verdadeira gambiarraaa, vou pensar em um melhor jeito pra isso depois
				 */
				String comando = dataIn.readUTF();
				String arrayComando[] = comando.split(" ");
				String op = arrayComando[0];
				switch(op){
				case "bye":
					msg = "fim";
					s.close();
					break;

				case "list":
					msg = listarUsuarios();
					break;

				case "rename":
					msg = this.renameUsuario(arrayComando[1]);
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

	/**
	 * @author mayer
	 * @todo Planejar um jeito de jogar esses metodos para o servidor e não para a thread
	 * @return String
	 */
	public String listarUsuarios(){
		String mensagem = "Usuarios Online:";

		for(MaquinaCliente maquinaCliente: this.maquinasClientes) {
			mensagem += "\n" + maquinaCliente.getNome();
		}

		return mensagem;
	}

	/**
	 * @author mayer
	 * @return String
	 */
	public String renameUsuario(String novoNome){

		if (!this.isNomeExistente(novoNome)) {
			this.maquinaCliente.setNome(novoNome);
			return "Usuário Renomeado com sucesso!";
		}
		
		return "Nome de usuário já existente!";
	}
	/**
	 * @author mayer
	 * @param novoNome
	 * @return boolean
	 */
	private boolean isNomeExistente(String novoNome){

		for(MaquinaCliente maquinaCliente: this.maquinasClientes) {
			
			if (maquinaCliente.getNome().equals(novoNome)) {
				return true;
			}
		}
		
		return false;
	}
}
