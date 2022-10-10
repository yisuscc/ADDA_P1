package Ejercicios;

import java.io.IOException;
import java.lang.invoke.StringConcatException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;
import us.lsi.iterables.IteratorFile;

public class Ej3 {
	public static List<Punto2D> ej3Iterativo(String file1, String file2){
		List<Punto2D> res = new ArrayList<>();
		// convertmos a iterator file 
		//IteratorFile







		return res; 
	}
	// vo y a intentar la funcional para aclararme las ideas 
	public static List<Punto2D> ej3Funcional(String file1, String file2){
		// Funtion que convierte string a punto2d 
		Function<String, Punto2D>funk = e-> {
			String[] p = e.split(",");
			Double x = Double.parseDouble(p[0].trim());
			Double y = Double.parseDouble(p[1].trim());
			return Punto2D.of(x,y);

		};
		// Hacemos el predicado 
		Predicate<Punto2D> condicion = p->{
			Boolean r = false;
			if (p.getCuadrante()== Cuadrante.PRIMER_CUADRANTE || 
					p.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				r = true;
			}
			return r;
		};
		Stream<String> lineas = null;
		// Stream de cadena con ambas lineas 
		try {
		 lineas = Stream.concat(Files.lines(Paths.get(file1)), Files.lines(Paths.get(file2)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//
		//convertimos a punto2d
		return lineas.map(funk).filter(condicion).sorted().toList();
		

	}


	public static void main(String[] args) {
	
System.out.println( ej3Funcional("ficheros/alumnos/PI1Ej3DatosEntrada1A.txt", "ficheros/alumnos/PI1Ej3DatosEntrada1B.txt"));
	}

}
