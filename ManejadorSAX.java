package examen;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ManejadorSAX extends DefaultHandler{

	int UltimoElemento;
	String cadenaResultado="";
	boolean operacion;
	// CONSTRUCTOR CON ATRIBUTO BOOLEANO 
	public ManejadorSAX(boolean oper) {
		UltimoElemento=0;
		operacion=oper;
	}

	//SE SOBRECARGAN LOS EVENTOS DE LA CLASE DefaultHandler
	//SE INDICA QUE HACER CUANDO ENCUENTRE stratElement(empezar)
	//endElement(acabar) Y CARACTERES DE TEXTO NORMALES (characters)
	@Override
	public void startElement(String uri,String localName, String qName, Attributes atts) throws SAXException{
			String etiqueta ="";
		if (operacion) {
			etiqueta =qName+":";
		} 
		if(qName.equals("pelicula")) {
			
			cadenaResultado=cadenaResultado + etiqueta+atts.getValue(atts.getQName(0))+": ";
			//UltimoElemento=1;	
			
		}else if(qName.equals("actor")) {
			UltimoElemento=2;
			cadenaResultado=cadenaResultado +"      "+etiqueta;
			
		}else {
			UltimoElemento=2;
			cadenaResultado=cadenaResultado + etiqueta;
			
		}
		

		

	}

	//SE PONE LINEA DISCONTINUA A LA SALIDA CUANDO DETECTA EL FINAL DE UN ELEMENTO
	//PARA SEPARAR LA INFO DE CADA ELEMENTO


	public void endElement(String uri,String localName, String qName)throws SAXException{

		/*if(qName.equals("titulo")) {
			cadenaResultado=cadenaResultado+"\n---------------------\n";
		}*/

	}

	//EL TEXTO SE GUARDA EN LA VARIABLE CORRESPONDIENTE CUANDO DETECTA LOS DIFERENTES ELEMENTOS DEL XML

	public void characters(char[] ch,int start, int length) throws SAXException {
		if (UltimoElemento==2) {
			for(int i=start;i<length+start;i++) {
				cadenaResultado=cadenaResultado+ch[i];
			}
		}

	}



	
}
