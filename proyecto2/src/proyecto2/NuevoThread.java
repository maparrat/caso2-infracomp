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

import java.util.Arrays;

/**
 ** Clase que se encarga de crear por fuerza bruta las posibles combinaciones de
 * texto y crea sus respectivos hashes asi que compara con el hash generado para
 * asi encontrar el mensaje original
 */
public class NuevoThread extends Thread {

	// ---------------------------------------------------------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------------------------------------------------------
	/**
	 * Atributo que representa la letra de este thread
	 */

	/**
	 * Atributo que representa el algoritmo que se esta usando
	 */
	private String algoritmo;

	private Boolean encontrado;

	private String descifrado;

	private int cantidadCaracteres;

	// ---------------------------------------------------------------------------------------------------------
	// CONSTRUICTOR
	// ------------------------------------------------------------------------------------------------------
	/**
	 * Metodo constructor del thread que hace fuerza bruta en un rango especifico
	 * 
	 * @param pLetra     del primer caracter
	 * @param pAlgoritmo que fue usado para crear el hash
	 */
	public NuevoThread(String pAlgoritmo, int cantidadCaracteres) {
		this.algoritmo = pAlgoritmo;
		this.encontrado = false;
		this.cantidadCaracteres = cantidadCaracteres;
		this.descifrado = "";

	}

	// ----------------------------------------------------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------------------------------------------------
	/*
	 * Metodo que activa el thread para que empieze a realizar comparaciones
	 */
	public void run() {
		try {
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i <= cantidadCaracteres; i++) {
				sb.append(" ");
			}
			fuerza_bruta(sb, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que realiza las comparaciones enrtre el hash creado y el hash real
	 * <post>: si encuentra un hash igual texto respuesta es el texto encontrario
	 * dlc null
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void fuerza_bruta(StringBuilder cadena_actual, int letra_posicion_actual)
			throws InterruptedException {
		
		if (encontrado == false) {

			String hash_evaluado = Main.generar_codigo(cadena_actual.toString(), algoritmo);

			if (letra_posicion_actual == cadena_actual.length()) {

				if (Main.hashG.equals(hash_evaluado)) {
					this.encontrado = true;
					this.descifrado = cadena_actual.toString();
					Main.encontrado = encontrado;
					Main.textoRespuesta = descifrado;
				}

				return;
			}

			for (int i = 0; i < Main.ALFABETO.length && !encontrado; i++) {
				char letter = Main.ALFABETO[i];
				cadena_actual.setCharAt(letra_posicion_actual, letter);
				fuerza_bruta(cadena_actual, letra_posicion_actual + 1);
			}

			yield();
		}
	}
}
