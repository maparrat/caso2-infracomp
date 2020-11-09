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
import java.util.concurrent.TimeUnit;

/**
 ** Clase que se encarga de recibir por consola el mensaje a encritar y el
 * algoritmo de hash para esto, ademas se encarga de generar codigo y de
 * identificar su entrada por fuerza bruta
 */
public class Main {

	// ---------------------------------------------------------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------------------------------------------------------

	/**
	 * Atributo que representa el si ya ha sido encontrado el mensaje de hash
	 * identico
	 */
	public static boolean encontrado = false;

	/**
	 * Atributo que representa el texto que el metodo encontro en fuerza bruta
	 */
	public static String textoRespuesta = null;

	/**
	 * Atributo que representa el hash generado
	 */
	public static String hashGenerado = null;
	/**
	 * Alfabeto que se va a considerar para las contrasenias
	 */
	public static char[] ALFABETO = "abcdefghijklmnñopqrstuvwxyz".toCharArray();
	/**
	 * Tamanio máximo de caracteres que tendrá una contrasenia
	 */
	public static final int MAX_TAMANIO = 7;

	// -----------------------------------------------------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------------------------------------------------

	/**
	 * Metodo generar_codigo. Recibe una cadena de texto y una cadena con el nombre
	 * de un algoritmo. Retorna el codigo criptogrqfico de hash correspondiente.}
	 * <b>pre: </b> El algoritmo debe pertenecer a [ MD5, SHA256, SHA384, SHA512]
	 * <b>pre: </b> El texto debe ser de maximo 7 letras y deben estar entre a y z (27 letras)
	 * 
	 * @param texto     que se decea cifrar por hash correspondiente
	 * @param algoritmo con el que se desea cifrar
	 * @return Retorna el codigo criptografico de hash correspondiente.
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
	 * Recibe un codigo criptografico de hash y una cadena con el nombre de un
	 * algoritmo. Retorna la cadena que se usa para generar dicho codigo (null si no
	 * encuentra una respuesta). 
	 * <b>pre: </b> El algoritmo debe pertenecer a [ MD5, SHA256,SHA384, SHA512] 
	 * <b>pre: </b> El algoritmo debe ser el mismo con el que se cifro hash_texto_cifrado
	 * @param hash_texto_cifrado que se usa para decifrar el mensage original
	 * @param algoritmo          que se uso para cifrar elm hash_texto_cifrado
	 * @return
	 * @throws InterruptedException
	 */
	public static String identificar_entrada(String hash_texto_cifrado, String algoritmo) throws InterruptedException {

		Thread[] threads = new Thread[MAX_TAMANIO];

		for (int i = 0; i < MAX_TAMANIO; i++) {
			NuevoThread thread_nuevo = new NuevoThread(algoritmo, i);
			threads[i] = thread_nuevo;
			thread_nuevo.start();
		}

		for (Thread thread : threads) {
			thread.join();
		}
		return textoRespuesta;

	}

	// ---------------------------------------------------------------------------------------------------
	// Main
	// ------------------------------------------------------------------------------------------
	/**
	 * Metodo encargado de solicitar por consola el texto a cifrar y el algotimo,
	 * ademas este se encarga de llamar a los metodos generar_codigo e
	 * identificar_entrada, ademas debe reportar el tiempo del algoritmo
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		Scanner myObj = new Scanner(System.in);

		System.out.println("Ingrese el texto a cifrar: ");

		String texto_a_cifrar = myObj.nextLine();
		
		System.out.println("Ingrese el algoritmo deseado: ");
		
		System.out.println("(Escribir uno de los siguientes algoritmos: MD5, SHA256, SHA384, SHA512)");

		String algoritmo = myObj.nextLine();

		if (algoritmo.contains("SHA")) {
			algoritmo = "SHA-" + algoritmo.split("SHA")[1];
		}

		System.out.println("Hash generado para el mensaje: ");

		String hash_generado = generar_codigo(texto_a_cifrar, algoritmo);

		hashGenerado = hash_generado;

		System.out.println(hash_generado);

		System.out.println("- - - - - - - - - - - - ");

		System.out.println("Mensaje decifrado a partir del hash y el algoritmo: ");

		long tiempo_inicial = System.currentTimeMillis();
		;

		System.out.println(identificar_entrada(hash_generado, algoritmo));

		long tiempo_final = System.currentTimeMillis();

		System.out.println("- - - - - - - - - - - - - - - - - ");

		long tiempo_total = (tiempo_final - tiempo_inicial);

		System.out.println("Tiempo tomado: " + TimeUnit.MILLISECONDS.toSeconds(tiempo_total) + "."
				+ TimeUnit.MILLISECONDS.toMillis(tiempo_total) + "   segundos ");

		myObj.close();

	}

}
