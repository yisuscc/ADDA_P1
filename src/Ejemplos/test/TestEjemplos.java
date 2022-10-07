package Ejemplos.test;

import java.util.List;
import java.util.stream.Collectors;

import Ejemplos.Ejemplo1;
import us.lsi.geometria.Punto2D;
import us.lsi.streams.Stream2;

public class TestEjemplos {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testEjemplo1();

	}
	public static void testEjemplo1() {
		String file = "ficheros/profesores/Ejemplo1DatosEntrada.txt";
		 List<Punto2D> ls = Stream2.file(file).map(s ->parsePuntos(s)).collect(Collectors.toList());
		 System.out.println("Solucion funcional: " + Ejemplo1.solucionFuncional(ls));
		 System.out.println("Solucion Recursiva: " + Ejemplo1.solucionRecursivaFinal(ls));
		 System.out.println("Solucion Iterativa: " + Ejemplo1.solucionIterativa(ls));
		 
	}
	private static  Punto2D parsePuntos (String s) {
		String[] v = s.split(",");
		Double x = Double.parseDouble(v[0]);
		Double y = Double.parseDouble(v[1]);
		return  Punto2D.of(x, y);
	}

}
