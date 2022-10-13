package Ejercicios;

import java.io.IOException;
import java.lang.invoke.StringConcatException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import us.lsi.common.Comparator2;
import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;
import us.lsi.iterables.IteratorFile;
import us.lsi.iterables.IteratorFilter;
import us.lsi.iterables.IteratorFusionOrdered;

public class Ej3 {
	public static List<Punto2D> ej3IterativoV1(String file1, String file2){
		List<Punto2D> res = new ArrayList<>();
		// convertmos a iterator file 
		//IteratorFile

		Function<String, Punto2D>funk = e-> {
			String[] p = e.split(",");
			Double x = Double.parseDouble(p[0].trim());
			Double y = Double.parseDouble(p[1].trim());
			return Punto2D.of(x,y);

		};
		// Hacemos el predicado 
		Predicate<Punto2D> condicion = p->{
			Boolean r = false;
			if (p.getCuadrante()== Cuadrante.TERCER_CUADRANTE) {
				r = true;
			}else if(p.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
				r = true;
			}
			
			return r;
		};
		Iterator<String> f1 = new IteratorFile(file1);
		Iterator<String> f2 = new IteratorFile(file2);
		return acumIterativo(f1, f2,condicion, funk);
	}
	//private static Iterator<Punto2D> transformadorAPunto2d(Iterator<String>ItS){
	//	
	//}
	private static List<Punto2D> acumIterativo(Iterator<String>i1, Iterator<String>i2,
			Predicate<Punto2D> prd, 
			Function<String, Punto2D>conver){
		List<Punto2D>res = new ArrayList<>();

		Punto2D p1 = (i1.hasNext())?conver.apply(i1.next()):null;
		Punto2D p2 = (i2.hasNext())?conver.apply(i2.next()):null;

		while((p1 != null || p2!= null)) {
			//Punto2D p = null; 

			if (p2 == null || Comparator2.isLENull(p1, p2)) { // esta 1 condición define el orden 
				
				if(prd.test(p1)) {
					res.add(p1);
					p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				}else if (prd.test(p2)) {
					res.add(p2);
					p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				}else {
					p1 = (i1.hasNext())?conver.apply(i1.next()):null;
					p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				}
			
			}else {// caso contrario
				if(prd.test(p2)) {
					res.add(p2);
					p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				}else if (prd.test(p1)) {
					res.add(p1);
					p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				}else {
					p1 = (i1.hasNext())?conver.apply(i1.next()):null;
					p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				}

			}

		}
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

		
		//System.out.println( ej3Funcional("ficheros/alumnos/PI1Ej3DatosEntrada1A.txt", "ficheros/alumnos/PI1Ej3DatosEntrada1B.txt"));
		System.out.println( ej3IterativoV1("ficheros/alumnos/PI1Ej3DatosEntrada1A.txt", "ficheros/alumnos/PI1Ej3DatosEntrada1B.txt"));
	}

}
