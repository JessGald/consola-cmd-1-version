package examen;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//CLASE METODOS DONDE SE ENCUENTRAN LA MAYOR PARTE DE LOS METODOS
public class Metodos {
	static	int espacios=0;
	//METODO COPIAR CON ATRIBUTO ORIGEN Y OTRO DESTINO
	//ESTE METODO LEE LOS ARCHIVOS Y LUEGO LOS COPIARA
	public static void copiar(String ori, String des) {
		try(FileInputStream input = new FileInputStream(ori);
				FileOutputStream output = new FileOutputStream(des)){

			byte array[] = new byte[input.available()];

			input.read(array);
			output.write(array);

			//EN CASO DE ERROR SALTA EXCEPCION
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	//ESTE METODO APROVECHA LOS METODOS ALIMIENAR Y COPIAR
	//ELIMINA EL ARCHIVO Y LO CREA EN OTRO DANDO LA IMPRESION DE QUE LOS MUEVE
		public static void mueve(String ori, String des) {
			copiar( ori,  des);
	
			eliminar( ori);
	}
	//METODO ELIMINAR CON UN SOLO ATRIBUTO
	//EN CASO DE QUE DETECTE ARCHIVO ESTE SERA ELIMINADO SI NO SALDRA EL SEGUNDO MENSAJE POR PANTALLA
		public static void eliminar(String ruta) {

		    File fichero = new File(ruta);

		    if (fichero.delete())
		        System.out.println("El fichero ha sido borrado satisfactoriamente");
		    else
		        System.out.println("El fichero no pudo ser borrado");
		}
		
		// ESTE METODO REVISA EL FICHERO BUSCANDO ARCHIVOS, EN CASO DE QUE EL FICHERO SEA NULO SALDRÍA EL MENSAJE DESCRITO ABAJO 
		//AL IMITAR EL SISTEMA DE LA CONSOLA DE WINDOWS DE LE DEBE INDICAR CON <DIR> PARA QUE LISTE LOS ARCHIVOS
		public static void listar(String ruta) {
			String linea = "";
			File directorio = new File(ruta);
			String fecha;
			
			
			File[] ficheros = directorio.listFiles();
			
			if (ficheros == null) {
				
				  System.out.println("No hay ficheros en el directorio especificado");
			// SE LE HAN IMPLEMENTADO VARIAS FUNCIONES PARA QUE REFLEJE LA FECHA DE CREACION DE ARCHIVO CON LAS CLASES DATE Y GREGORIAN CALENDAR
			
			} else { 
				  for (int x=0;x<ficheros.length;x++) {
					  long ms = ficheros[x].lastModified();
					  Date d = new Date(ms);
					  Calendar c = new GregorianCalendar(); 
					  c.setTime(d);
					  fecha = Integer.toString(c.get(Calendar.DATE))+"/";
					  fecha = fecha+Integer.toString(c.get(Calendar.MONTH))+"/";
					  fecha = fecha+Integer.toString(c.get(Calendar.YEAR))+" ";
					  fecha = fecha+Integer.toString(c.get(Calendar.HOUR_OF_DAY))+":";
					  fecha = fecha+Integer.toString(c.get(Calendar.MINUTE))+":";
					  fecha = fecha+Integer.toString(c.get(Calendar.SECOND))+"";
					  //ESTE IF PERMITE QUE INTRODUCIENDO <DIR> SE PONGA EN FUNCIONAMIENTO EL PROGRAMA
					  if(ficheros[x].isDirectory()) {
						  linea=fecha+" <DIR> "+ficheros[x].getName();
					  } else {
						  linea=fecha+" "+ficheros[x].getName();
					  }
				
				    System.out.println(linea);
				  }
				}
			}

		
		//MUESTRA TEXTO INTRODUCIENDO UNA RUTA
		//BUFFEREDREADER LEE Y GUARDA EL TEXTO
		public static void mostrarTXT(String ruta) throws Exception {		
			  
			  File fichero = new File(ruta);	  

			  @SuppressWarnings("resource")
			BufferedReader objeto = new BufferedReader(new FileReader(fichero));
		//MIENTRAS QUE LA CADENA TENGA TEXTO LA MOSTRARA POR PANTALLA
			  String cadena;
			  while ((cadena = objeto.readLine()) != null)
			    System.out.println(cadena);
			  
			
		}
		
		//CON ESTE METODO SE MUESTRAN LOS DATOS DEL XML SEA CON ETIQUETA O SIN
		public static void muestraXML(String ruta, String tipo) {
		//LA CLASE GESTIONAR SAX
			File file=new File(ruta);
			examen.GestionarSAX gestor=new GestionarSAX(file,tipo);

			//SE ABRE XML
			gestor.abrirXML();

			System.out.println(gestor.RecorrerSAX());
		}
		
		
	    //LISTA LOS ARCHIBOS DEJANDO ESPACIOS CON UN ATRIBUTO DE LA CLASE FILE
		public static void listarArbol(File dir) {
			
				
			File[] listaDeFicheros = dir.listFiles();
			
			String espaciosEnBlancos=" ";
			//Depende de cuantas veces haya entrado o salido tendra x cantidad de espacios
			for (int i = 0; i < espacios; i++) {
				espaciosEnBlancos=espaciosEnBlancos+"     ";
			}
			
			try {
				for (File fichero : listaDeFicheros) {
					if(fichero.isDirectory()) {  
						System.out.println(espaciosEnBlancos +fichero.getName());
						espacios++;
						listarArbol(fichero);
						
					} else { 
						System.out.println(espaciosEnBlancos +fichero.getName());					
					}
				}
				//Cada vez que sale un directorio se le quitan los espacios.
				espacios--;
				System.out.println("");
				 
			} catch (NullPointerException e) {
			}
		}
		
		//COMPRA TEXTO ENTRE DOS ARCHIVO DE DOS RUTAS
		public static void compararTXT(String ruta1, String ruta2) throws IOException {
			
			
			File fichero1 = new File(ruta1);
			File fichero2 = new File(ruta2);
			// SI FICHERO 1 O FICHERO 2 NO EXISTEN
			if(!fichero1.exists() || !fichero2.exists()){
				System.out.println("uno de los ficheros no introducidos no existe");
				throw new FileNotFoundException();
			}
			//LOS BUFFEREDREADER LEEN Y GUARDAN LOS TEXTOS
			try(BufferedReader br1 = new BufferedReader(new FileReader(ruta1)); 
					BufferedReader br2 = new BufferedReader(new FileReader(ruta2)) ){
			
				String linea1 = br1.readLine();
				String linea2 = br2.readLine();
			//MIENTRAS QUE LINEA 1 O LINEA 2 NO ESTEN VACIOS IMPRIMIRAN LOS DATOS POR PERSONA
				while (linea1 != null || linea2!=null) {
									
					if(linea1==null) linea1="";
					if(linea2==null) linea2="";
					
					if(!linea1.equals(linea2)) {		
						System.out.println(fichero1.getName() +": " +linea1);
						System.out.println(fichero2.getName() +": " +linea2);
					}
						
					linea1 = br1.readLine();
					linea2 = br2.readLine();
				}
				
			}catch (IOException e) {
				throw new IOException();
			}
		}
}
