package servidorCliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class mainCliente {

	public static void main(String[] args) {
	
		try {
			Scanner sc = new Scanner(System.in);
			Socket sCliente = new Socket("localhost", 6000);
			
			InputStream is=sCliente.getInputStream();
			DataInputStream flujo_entrada = new	 DataInputStream(is);
			
			OutputStream aux = sCliente.getOutputStream();
			DataOutputStream flujo_salida= new DataOutputStream(aux);
			
			
			String cadena;
			cadena=flujo_entrada.readUTF();
			System.out.println("servidor: "+cadena);
			

			System.out.print("Escribe el archivo que deses encontrar :");
			String respuesta= sc.next();
			flujo_salida.writeUTF(respuesta);
			
			
			int existe;
			existe=flujo_entrada.readInt();
			System.out.println("servidor: "+existe);
			
			if (existe == 202) {
				try {
					String textoFichero = flujo_entrada.readUTF();
					while(true) {
						
					//textoFichero = flujo_entrada.readUTF();
					System.out.println(textoFichero);
					
					textoFichero = flujo_entrada.readUTF();
					
					}
					
				} catch (EOFException e) {
					System.out.println("error"+e);
				}
				
				sCliente.close();
				
			}else {
				sCliente.close();
			}
			
		}catch (Exception e) {
			System.out.println("error : "+ e);
		}

	}

}
