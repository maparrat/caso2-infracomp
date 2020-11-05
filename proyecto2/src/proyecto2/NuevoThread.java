package proyecto2;

public class NuevoThread extends Thread {





	private String letra;
	private String algoritmo;

	private char c1;
	private char c2;
	private char c3;
	private char c4;
	private char c5;
	private char c6;
	private char c7;



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

	public void run() {
		try {
			fuerza_bruta(letra);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void fuerza_bruta( String letra) throws InterruptedException
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
