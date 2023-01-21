package enviarFichero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		final String HOST = "localhost";
		final int PUERTO = 5000;
		
		

		Socket socket = new Socket(HOST, PUERTO);		
		DataOutputStream flujoSalida;
		OutputStream os = socket.getOutputStream();

		System.out.println("Introduce el nombre del fichero");
		String nombreArchivo = sc.nextLine();

		flujoSalida = new DataOutputStream(os);
		flujoSalida.writeUTF(nombreArchivo);

		// recibimos las lineas leidas

		DataInputStream flujoEntrada;
		InputStream in = socket.getInputStream();
		flujoEntrada = new DataInputStream(in);

		
		
			
			String lineasFichero= flujoEntrada.readUTF();
			
			
			System.out.println("---Contenido del fichero: ---");
			try {
			while (lineasFichero != null) {
				System.out.println(lineasFichero);
				lineasFichero = flujoEntrada.readUTF();
			}
			
		} catch (EOFException ef) {
			
			System.out.println("Fin del fichero");
			
		}finally {
			socket.close();
		}
		

	}
}
