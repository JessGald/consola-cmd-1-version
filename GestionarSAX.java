package examen;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class GestionarSAX {

	SAXParserFactory factory=null;
	SAXParser parser=null;
	ManejadorSAX sh=null;
	File XML=null;
	boolean operacion=true;
	// SE ENCARGA DE GESTIONAR LA INFO DEL XML Y POSIBLITA QUE DISTINGA ENTRE CON ETIQUETA O SIN
	public  GestionarSAX(File fichero, String operaciones) {
		XML=fichero;
		operacion=("/conEtiquetas".equals(operaciones));
		
	}

	public void abrirXML() {
		try {


			//SE ABRE DOCUMENTO
			factory=SAXParserFactory.newInstance();
			//OBJETO SAXparser SE CREA PARA INTERPRETAR XML
			parser=factory.newSAXParser();
			//SE CREA INSTANCIA DE MANEJADOR QUE RECORRE EL DOCUMENTO XML
			sh=new ManejadorSAX(operacion);
		}catch(Exception e) {

			e.printStackTrace();

		}
	}
	// SALE EL PARSER PARA MANEJAR EL DOC XML
	//ESTE RECORRE EL XML Y CUANDO LLEGUE A UN FIN O UN COMIENZO
	//LO TRATARA


	public String RecorrerSAX(){
		try {
			parser.parse(XML, sh);
			System.out.println(sh.cadenaResultado);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al parsear con SAX");
		}
		return sh.cadenaResultado;
	}








}
