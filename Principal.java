package examen;

import java.io.File;
import java.util.Scanner;
//CLASE PRINCIPAL DONDE SE PONEN EN FUNCIONAMIENTO LOS METODOS
public class Principal {
	static Scanner sc= new Scanner(System.in);
	public static void main(String[] args) {
		//SE DEFINEN VARIABLES COMO VACIOS
		String comando="";
		String opcion="";
		//BUCLE WHILE DONDE EN CASO DE QUE EL COMANDO INTRODUCIDO SEA DIFERENTE A SALIR SEGUIRA FUNCIONANDO EL PROGRAMA
		while(!comando.equals("salir")){
		System.out.println("Microsoft Windows [Versión 10.0.22000.318]\r\n"
				+ "(c) Microsoft Corporation. Todos los derechos reservados.");
		System.out.println("Escriba salir para salir de la consola");
		System.out.println("C:\\Users\\jesus>");
		opcion=sc.nextLine();
		//ARRAY DE STRING CON SPLIT
		String[] opcionSplit = opcion.split(" ");
		
		System.out.println(opcion);
		if(opcionSplit.length>0) {
		System.out.println(opcionSplit[0]);
		
		comando=opcionSplit[0];
		//METODO MUEVE CON TRY/CATCH PARA CONTROLAR ERRORES
		//ESTE METODO MOVERA UN ARCHIVO DE UN FICHERO A OTRO ELIMINANDOLO EN EL DE ORIGEN
		// EL CONTROL DE ERRORE SE REPITE A LO LARGO DE CADA METODO
		if("mueve".equals(opcionSplit[0])) {
			try {
				
			 Metodos.mueve(opcionSplit[1], opcionSplit[2]);
			System.out.println("mueve archivo");
			
			}catch(Exception e) {
				System.out.println("Comando mueve erroneo, por favor asegurese aue tiene el formato: mueve rutaOrigen rutaDestino");
			}
		//METODO COPIA QUE COPIARA UN ARCHIVO A TRAVES DE UNA RUTA
		}else if("copia".equals(opcionSplit[0])){
			try {
				
				Metodos.copiar(opcionSplit[1],opcionSplit[2]);
				System.out.println("copia archivo");
				
			}catch(Exception e){
				
				System.out.println("Comando copia erroneo, por favor asegurese que tiene el formato: copia rutaOrigen rutaDestino");
			}
			
		//METODO ELIMINA EN EL FICHERO DE ORIGEN	
		}else if("elimina".equals(opcionSplit[0])) {
			try {
			Metodos.eliminar(opcionSplit[1]);
			 
			System.out.println("elimina archivo");
			}catch(Exception e) {
				
				System.out.println("Comando elimina erroneo, por favor asegurese que tiene el formato: elimina rutaOrigen");
			}
		//METODO LISTA QUE MUESTRA LOS FICHEROS QUE SALEN INTRODUCIENDO LA RUTA POR PANTALLA
		}else if("lista".equals(opcionSplit[0])) {
			try {
			Metodos.listar(opcionSplit[1]);
			System.out.println("lista archivo");
			
			}catch(Exception e) {
				System.out.println("Comando lista, por favor asegurese que tiene el formato: lista rutaOrigen");
			}
		//METODO LISTA ARBOL MOSTRANDO LA LISTA DE ARCHIVOS CON UN DESPLAZAMIENTO PARA MAYOR VISION
		}else if("listaArbol".equals(opcionSplit[0])) {
			try {
			File dir = new File(opcionSplit[1]);
			Metodos.listarArbol(dir);
			System.out.println("lista Arbolarchivo");
			}catch(Exception e) {
				System.out.println("Comando listaArbol, por favor asegurese que tiene el formato: listaArbol rutaOrigen");
			}
		//METODO COMPARA TEXTO DE DIFERENTES FICHEROS INTRODUCIENDO DOS RUTAS
		}else if ("comparaTXT".equals(opcionSplit[0])) {
			try {
			Metodos.compararTXT(opcionSplit[1], opcionSplit[2]);
			System.out.println("comparaTXT fichero ");
			}catch(Exception e){
				System.out.println("Comando comparaTXT erroneo, por favor asegurese que tiene el formato: comparaTXT rutaOrigen rutaDestino");
			}
		//MUESTRA TEXTO	DES FICHERO O ARCHIVO QUE SE INTRODUZCA POR PANTALLA
		}else if ("muestraTXT".equals(opcionSplit[0])) {
			try {
			Metodos.mostrarTXT(opcionSplit[1]);
			System.out.println("muestraTXT fichero");
			
			}catch(Exception e) {
				System.out.println("Comando muestraTXT erroneo, por favor asegurese que tiene el formato: muestraTXT rutaOrigen");
			}
			
		//MUESTRA XML HACIENDO DISTINCION ETRE CON ETIQUETA Y SIN ETIQUETA	TOMANDO COMO BASE UN XML
		}else if ("muestraXML".equals(opcionSplit[0])) {
			try {
				Metodos.muestraXML(opcionSplit[1], opcionSplit[2]);
				System.out.println("muestraXML fichero");
				
			}catch(Exception e) {
				System.out.println("Comando muestraXML erroneo, por favor asegurese que tiene el formato: muestraXML ruta Origen"
						+ "/sinEtiquetas o /conEtiquetas");
			}
			
		}
		}
	}
}
}