/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad de los Andes (Bogota - Colombia)
 * Departamento de Ingenieria de Sistemas y Computacion  
 *
 * Infraestructura computacional
 * Ejercicio: caso 2
 * Autor: Miguel Parra - 201814632
 * Autor: Juan David Monsalve - 201814295
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package proyecto2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;


/**
 ** Clase que se encarga de recibir por consola el mensaje a encritar  y el algoritmo de hash para esto,
 ** ademas se encarga de generar codigo y de identificar su entrada por fuerza bruta
 */
public class Main {

	// ---------------------------------------------------------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------------------------------------------------------
	/**
	 * Atributo que representa el si ya ha sido encontrado el mensaje de hash identico
	 */
	public static boolean encontrado = false;
	/**
	 * Atributo que representa el texto que el metopdo encontro en fuerza bruta
	 */
	public static String textoRespuesta = null;
	/**
	 * Atributo que representa hash generado
	 */
	public static String hashG = null;

	// ----------------------------------------------------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------------------------------------------------

	/**
	 * Metodo que imprime un arreglo de bytes y los imprime en consiola de forma ordenada
	 * @param contenido cadena de bytes que se desea mostrar en consola
	 */
	public static void imprimir(byte[] contenido) {
		int i = 0;

		for (; i < contenido.length - 1; i++) {
			System.out.print(contenido[i] + " ");
		}
		System.out.println(contenido[i] + " ");
	}


	/**
	 * Método generar_codigo. Recibe una cadena de texto y una cadena con el nombre de un algoritmo. 
	 * Retorna el código criptográfico de hash correspondiente.}
	 * <b>pre: </b> El algoritmo debe pertenecer a [ MD5, SHA256,SHA384, SHA512] 
	 * <b>pre: </b> El texto debe ser de maximo 7 letras y deben estar entre a y z 
	 * @param texto que se decea cifrar por hash correspondiente
	 * @param algoritmo con el que se desea cifrar 
	 * @return Retorna el código criptográfico de hash correspondiente. 
	 */
	public static String generar_codigo(String texto, String algoritmo) {
		String RTA = null;
		try {

			MessageDigest md = MessageDigest.getInstance(algoritmo);

			md.update(texto.getBytes());

			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			RTA = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return RTA;

	}

	/**
	 * Recibe un código criptográfico de hash y una cadena con el nombre de un
	 * algoritmo. Retorna la cadena que se usó para generar dicho código (null si no encuentra una respuesta).
	 * <b>pre: </b> El algoritmo debe pertenecer a [ MD5, SHA256,SHA384, SHA512] 
	 * <b>pre: </b> El algoritmo debe ser el mismo con el que se cifro hash_texto_cifrado  
	 * @param hash_texto_cifrado que se usa para decifrar el mensage original
	 * @param algoritmo que se uso para cifrar elm hash_texto_cifrado
	 * @return
	 * @throws InterruptedException 
	 */
	public static  String identificar_entrada(String hash_texto_cifrado, String algoritmo) throws InterruptedException {

		char letra = 'a';
		Thread[] threads = new Thread[27];

		NuevoThread thread_especial = new NuevoThread("ñ",algoritmo);
		threads[0]= thread_especial;
		
		thread_especial.start();

		for (int i = 0; i < 26; i++) {

			NuevoThread thread_nuevo = new NuevoThread(letra + "",algoritmo);
			threads[i+1]= thread_especial;
			thread_nuevo.start();
			letra++;
		}
		
		for (Thread thread : threads) {
		       thread.join();
		}
		return textoRespuesta;

	}
	//---------------------------------------------------------------------------------------------------
	// Main
	//------------------------------------------------------------------------------------------
	/**
	 *Metodo encargado de solicitar por consola el texto a cifrar y el algotimo, ademas este se encarga de 
	 *llamar a los metodos generar_codigo e identificar_entrada, ademas debe reportar el tiempo del algoritmo
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {

		Scanner myObj = new Scanner(System.in);

		System.out.println("Ingrese el texto a cifrar: ");

		String texto_a_cifrar = myObj.nextLine();

		System.out.println("Ingrese el algoritmo: ");

		String algoritmo = myObj.nextLine();

		if (algoritmo.contains("SHA")) {
			algoritmo = "SHA-" + algoritmo.split("SHA")[1];
		}

		System.out.println("Hash generado para el mensaje: ");
		
		String hash_generado = generar_codigo(texto_a_cifrar, algoritmo);
		
		hashG = hash_generado;
		
		System.out.println(hash_generado);

		System.out.println("- - - - - - - - - - - - ");

		System.out.println("Mensaje decifrado a partir del hash y el algoritmo: ");

		long tiempo_inicial = System.nanoTime();

		System.out.println(identificar_entrada(hash_generado, algoritmo));

		long tiempo_final = System.nanoTime();

		System.out.println("- - - - - - - - - - - - - - - - - ");

		double tiempo_total = (tiempo_final - tiempo_inicial)/1000000000;

		System.out.println("Tiempo tomado: " + tiempo_total + "   segundos ");

		myObj.close();

	}

}
