package Ejercicios.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class test2 {
	public static void testRecFinal() {
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
		testRecFinal();
	}
}
