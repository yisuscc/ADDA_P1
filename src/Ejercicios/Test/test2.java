package Ejercicios.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import Ejercicios.E1;

public class test2 {
	public static void testEj1() {
	Path  filePath=Path.of("ficheros/alumnos/PI1Ej1DatosEntrada.txt");
		Consumer<String> con = x-> {
			String [] datos = x.split(",");
			//5,pera,10,pi�a,20
			Integer a = Integer.parseInt(datos[0]);
			String b = datos[1];
			Integer c= Integer.parseInt(datos[2]);
			String  d = datos[3];
			Integer e = Integer.parseInt(datos[4]);
			
			System.out.println("Versin del enunciado: " + Ejercicios.E1.ejercicioA(a, b, c, d, e));
			System.out.println("Versión Iterativa 1 : " + Ejercicios.E1.ej1Iterativo(a, b, c, d, e));
		};
		try {
			@SuppressWarnings("resource")
			// quien coño decidió que codificar los archivos en iso-8859-1
			// en lugar de utf-8 era buena idea 

			Stream<String> lineas = Files.lines(filePath);
			
			lineas.forEach(con);
		
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	public static void testEj2 (){
		String filePath = "ficheros/alumnos/PI1Ej2DatosEntrada.txt";
		
		Consumer<String> cnsmr = x -> {
			 String[] datos =  x.split(",");
			 Integer a  = Integer.parseInt(datos[0]);
			 Integer b  = Integer.parseInt(datos[1]);
			 String s = datos[2];
			 System.out.println("Cadena: " + s);
			 System.out.println("Versión recursiva final" + Ejercicios.E2.Ej2Rec(a, b, s));
		};
	
		try {
			
			Stream<String> lineas = Files.lines(Paths.get(filePath));
			lineas.forEach(cnsmr);
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		testEj1();
		//testEj2();
	}
}
