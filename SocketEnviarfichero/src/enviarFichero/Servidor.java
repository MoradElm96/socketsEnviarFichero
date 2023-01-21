package enviarFichero;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws IOException {
	    //variables y objetos para la conexion y puerto
		ServerSocket servidor = null;
		Socket socket = null;
		DataInputStream flujoEntrada;
		DataOutputStream flujoSalida;
		final int PUERTO = 5000;

		
		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado");

		// se queda a la espera del cliente
		socket = servidor.accept();
		System.out.println("Cliente conectado");

		// obtener nombre del archivo por parte del cliente
		InputStream is = socket.getInputStream();
		flujoEntrada = new DataInputStream(is);
		String nombreArchivo = flujoEntrada.readUTF();
		System.out.println("Se va a abrir el fichero "+ nombreArchivo);

		
		// crea un flujo de salida hacia el cliente
		OutputStream os = socket.getOutputStream();
		flujoSalida = new DataOutputStream(os);

		
		//leemos el fichero linea a linea
		try {

			FileReader fr = new FileReader(nombreArchivo);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();

			while ((linea != null)) {	
				//enviamos la linea leida al cliente
				flujoSalida.writeUTF(linea);
				linea = br.readLine();
			}

			fr.close();
			
		} catch (FileNotFoundException e) {
			// si el fichero no existe enviamos un codigo de error al cliente
			flujoSalida.writeUTF("404: File not found");
		}
		
		

		//cerramos las conexiones
		socket.close();
		servidor.close();

	}

}
