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
 ** Clase que se encarga de crear por fuerza bruta las posibles combinaciones de texto y crea sus respectivos hashes
 *asi que compara con el hash generado para asi encontrar el mensaje original
 */
public class NuevoThread extends Thread {




	// ---------------------------------------------------------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------------------------------------------------------
	/**
	 * Atributo que representa la letra de este thread
	 */
	private String letra;
	/**
	 * Atributo que representa el algoritmo que se esta usando 
	 * */
	private String algoritmo;
	/**
	 * Atributos que representan cada caracter en cada posicion
	 */
	private char c1;
	private char c2;
	private char c3;
	private char c4;
	private char c5;
	private char c6;
	private char c7;


	//---------------------------------------------------------------------------------------------------------
	// CONSTRUICTOR
	//------------------------------------------------------------------------------------------------------
	/**
	 * Metodo constructor del thread que hace fuerza bruta en un rango especifico
	 * @param pLetra del primer caracter 
	 * @param pAlgoritmo  que fue usado para crear el hash 
	 */
	public NuevoThread(String pLetra,String pAlgoritmo) {
		this.letra = pLetra;
		c1= letra.charAt(0);;
		c2= ' ';
		c3= ' ';
		c4= ' ';
		c5= ' ';
		c6= ' ';
		c7= ' ';
		algoritmo = pAlgoritmo;

	}
	// ----------------------------------------------------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------------------------------------------------
	/*
	 * Metodo que activa el thread para que empieze a realizar comparaciones
	 */
	public void run() {
		try {
			fuerza_bruta();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que realiza las comparaciones enrtre el hash creado y el hash real 
	 * <post>: si encuentra un hash igual texto respuesta es el texto encontrario dlc null
	 * @throws InterruptedException
	 */
	public synchronized void fuerza_bruta( ) throws InterruptedException
	{
		while (Main.encontrado == false)
		{



			char[] ch = {c1, c2, c3, c4, c5, c6,c7};

			String posible_t = new String(ch);
			String[] filtro = posible_t.split(" ");	
			String posible_texto = filtro[0];
			String posible_hash = Main.generar_codigo(posible_texto, algoritmo);
			//System.out.println("PALABRA= "+ posible_texto);
			//System.out.println("REAL hash= "+Main.hashG);
			//System.out.println("POSIBLE hash= "+posible_hash);
			if(Main.hashG.equals(posible_hash))
			{
				Main.textoRespuesta = posible_texto;
				Main.encontrado = true;
			}
			actualizarCadena();
			yield();
		}
	}

	/*
	 * Metodo encargado de actualizar la cadena para realizar la siguiente comparacion
	 * <pre>: la cadena esta conformada por caracteres entre a-z
	 * <post>: la cadena a sido actualizada para la siguiente iteracion. 
	 */
	public void actualizarCadena()
	{
		if(c2 == 'z' && c3 == 'z'&& c4 == 'z'&& c5 == 'z'&& c6 == 'z'&& c7 == 'z')
		{
			stop();
		}
		else if(c2 != ' ' && c3 == 'z'&& c4 == 'z'&& c5 == 'z'&& c6 == 'z'&& c7 == 'z')
		{
			c2++;
			c3='a';
			c4='a';
			c5='a';
			c6='a';
			c7='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 == 'z'&& c5 == 'z'&& c6 == 'z'&& c7 == 'z')
		{
			c3++;
			c4='a';
			c5='a';
			c6='a';
			c7='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 == 'z'&& c6 == 'z'&& c7 == 'z')
		{
			c4++;
			c5='a';
			c6='a';
			c7='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 != ' '&& c6 == 'z'&& c7 == 'z')
		{
			c5++;
			c6='a';
			c7='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 != ' '&& c6 != ' '&& c7 == 'z')
		{
			c6++;
			c7='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 != ' '&& c6 != ' '&& c7 != 'z')
		{
			c7++;

		}
		else if(c2 == 'z' && c3 == 'z'&& c4 == 'z'&& c5 == 'z'&& c6 == 'z'&& c7 == ' ')
		{

			c2='a';
			c3='a';
			c4='a';
			c5='a';
			c6='a';
			c7='a';
		}
		else if(c2 != ' ' && c3 == 'z'&& c4 == 'z'&& c5 == 'z'&& c6 == 'z'&& c7 == ' ')
		{

			c2++;
			c3='a';
			c4='a';
			c5='a';
			c6='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 == 'z'&& c5 == 'z'&& c6 == 'z'&& c7 == ' ')
		{

			c3++;
			c4='a';
			c5='a';
			c6='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 == 'z'&& c6 == 'z'&& c7 == ' ')
		{

			c4++;
			c5='a';
			c6='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 != ' '&& c6 == 'z'&& c7 == ' ')
		{

			c5++;
			c6='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 != ' '&& c6 != 'z'&& c7 == ' ')
		{

			c6++;

		}
		else if(c2 == 'z' && c3 == 'z'&& c4 == 'z'&& c5 == 'z'&& c6 == ' '&& c7 == ' ')
		{

			c2='a';
			c3='a';
			c4='a';
			c5='a';
			c6='a';

		}
		else if(c2 != ' ' && c3 == 'z'&& c4 == 'z'&& c5 == 'z'&& c6 == ' '&& c7 == ' ')
		{
			c2++;
			c3='a';
			c4='a';
			c5='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 == 'z'&& c5 == 'z'&& c6 == ' '&& c7 == ' ')
		{
			c3++;
			c4='a';
			c5='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 == 'z'&& c6 == ' '&& c7 == ' ')
		{
			c4++;
			c5='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != ' '&& c5 != 'z'&& c6 == ' '&& c7 == ' ')
		{
			c5++;
		}
		else if(c2 == 'z' && c3 == 'z'&& c4 == 'z'&& c5 == ' '&& c6 == ' '&& c7 == ' ')
		{
			c2='a';
			c3='a';
			c4='a';
			c5='a';
		}
		else if(c2 != ' ' && c3 == 'z'&& c4 == 'z'&& c5 == ' '&& c6 == ' '&& c7 == ' ')
		{
			c2++;
			c3='a';
			c4='a';

		}
		else if(c2 != ' ' && c3 != ' '&& c4 == 'z'&& c5 == ' '&& c6 == ' '&& c7 == ' ')
		{
			c3++;
			c4='a';
		}
		else if(c2 != ' ' && c3 != ' '&& c4 != 'z'&& c5 == ' '&& c6 == ' '&& c7 == ' ')
		{
			c4++;
		}

		else if(c2 == 'z' && c3 == 'z')
		{
			c2='a';
			c3='a';
			c4='a';

		}
		else if(c2 != ' ' && c3 == 'z')
		{
			c2++;
			c3='a';
		}
		else if(c2 != ' ' && c3 != 'z')
		{
			c3++;

		}
		else if(c2 == 'z' )
		{
			c2='a';
			c3='a';

		}
		else if(c2 != 'z' )
		{
			c2++;

		}
		else {
			c2 = 'a';
		}

	}
	public String darLetra() {
		return letra;
	}
}
