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
	 * Atributo que representa el algoritmo que se esta usando
	 */
	private String algoritmo;

	/**
	 * Atributo que representa la cantidad de caracteres del thread especificado
	 */
	private int cantidadCaracteres;

	// ---------------------------------------------------------------------------------------------------------
	// CONSTRUICTOR
	// ------------------------------------------------------------------------------------------------------
	/**
	 * Metodo constructor del thread que hace fuerza bruta en un rango especifico
	 * 
	 * @param cantidadCaracteres los caracteres que el thread actual va a manejar
	 *                           (numero entre 1 y 7)
	 * @param pAlgoritmo         algoritmo que fue usado para crear el hash
	 */
	public NuevoThread(String pAlgoritmo, int cantidadCaracteres) {
		this.algoritmo = pAlgoritmo;
		this.cantidadCaracteres = cantidadCaracteres;
	}

	// -----------------------------------------------------------------------------------------------------------
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
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que realiza las comparaciones enrtre el hash creado y el hash real
	 * para cada posible combiacion de caracteres con el lenght especificado.
	 * <post>: si encuentra un hash igual al hash dado, corresponde al texto encontrado y finaliza la ejecucion 
	 * @param cadena_actual representa la cadena evaluada actualmente, una de todas las posibles combinaciones
	 * @param letra_posicion_actual   representa la posicion de la letra actual (a-z)
	 * @throws InterruptedException
	 */
	public synchronized void fuerza_bruta(StringBuilder cadena_actual, int letra_posicion_actual) throws InterruptedException {

		if (Main.encontrado == false) {

			if (letra_posicion_actual == cadena_actual.length()) {

				String hash_evaluado = Main.generar_codigo(cadena_actual.toString(), algoritmo);

				if (Main.hashGenerado.equals(hash_evaluado)) {
					Main.encontrado = true;
					Main.textoRespuesta = cadena_actual.toString();
				}

				return;
			}

			for (int i = 0; i < Main.ALFABETO.length && !Main.encontrado; i++) {
				char letter = Main.ALFABETO[i];
				cadena_actual.setCharAt(letra_posicion_actual, letter);
				fuerza_bruta(cadena_actual, letra_posicion_actual + 1);
			}

			yield();
		}
	}
}
