package Ejemplos.test;

import java.util.List;
import java.util.stream.Collectors;

import Ejemplos.Ejemplo1;
import Ejemplos.Ejemplo2;
import Ejemplos.Ejemplo3;
import us.lsi.common.IntPair;
import us.lsi.geometria.Punto2D;
import us.lsi.streams.Stream2;

public class TestEjemplos {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testEjemplo1();
		//testEjemplo2();
		testEjemplo3();

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
	
	public static void testEjemplo2() {
		String file= "ficheros/profesores/Ejemplo2DatosEntrada.txt";
		List<IntPair>ls = Stream2.file(file).map(IntPair::parse).toList();
		for(IntPair par: ls) {
			System.out.println("Version Funcional:  "+ Ejemplo2.solucionFuncional(par.first(),par.second()));
		}
	}
		
		public static void testEjemplo3() {
			String file= "ficheros/profesores/Ejemplo2DatosEntrada.txt";
			List<IntPair>ls = Stream2.file(file).map(IntPair::parse).toList();
			for(IntPair par: ls) {
				System.out.println("Version Funcional:  "+ Ejemplo3.solRecConMemoria(par.first(),par.second()));
			}
	}

}
