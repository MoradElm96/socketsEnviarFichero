package servidorCliente;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor {

	public static void main(String[] args) {
		int puerto = 6000;
		ServerSocket servidor = null;
		try {
			
			servidor = new ServerSocket(puerto);
			System.out.println("escucho en el puerto 6000");
			Socket cliente1 = servidor.accept();

			InputStream is = cliente1.getInputStream();
			DataInputStream flujo_entrada = new DataInputStream(is);

			OutputStream aux = cliente1.getOutputStream();
			DataOutputStream flujo_salida = new DataOutputStream(aux);

			flujo_salida.writeUTF("Bienvenido al servidor");

			String respuesta = flujo_entrada.readUTF();
			System.out.println("Fichero que buscamos :" + respuesta);

			File archivo = new File(respuesta);
			if (!archivo.exists()) {
				flujo_salida.writeInt(202);
				FileReader fr = new FileReader("/home/alumnotd/eclipse-workspace/servidor/src/servidor/"+archivo);
				BufferedReader br = new BufferedReader(fr);
				String linea = br.readLine();

				while (linea != null) {
					flujo_salida.writeUTF(linea);
					System.out.println(linea);
					linea=br.readLine();
				}

			} else {
				flujo_salida.writeInt(404);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
