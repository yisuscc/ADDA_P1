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
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.common.Comparator2;
import us.lsi.common.Pair;
import us.lsi.common.Trio;
import us.lsi.geometria.ParDePuntos;
import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;
import us.lsi.iterables.IteratorFile;
import us.lsi.iterables.IteratorFilter;
import us.lsi.iterables.IteratorFusionOrdered;
import us.lsi.streams.Stream2;

public class Ej3 {
	public static List<Punto2D> ej3IterativoV1(String file1, String file2) {
		List<Punto2D> res = new ArrayList<>();
		// convertmos a iterator file
		// IteratorFile

		Function<String, Punto2D> funk = e -> {
			String[] p = e.split(",");
			Double x = Double.parseDouble(p[0].trim());
			Double y = Double.parseDouble(p[1].trim());
			return Punto2D.of(x, y);

		};
		// Hacemos el predicado
		Predicate<Punto2D> condicion = p -> {
			Boolean r = false;
			if (p.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				r = true;
			} else if (p.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
				r = true;
			}

			return r;
		};
		Iterator<String> f1 = new IteratorFile(file1);
		Iterator<String> f2 = new IteratorFile(file2);
		return acumIterativo(f1, f2, condicion, funk);
	}

	// private static Iterator<Punto2D> transformadorAPunto2d(Iterator<String>ItS){
	//
	// }
	private static List<Punto2D> acumIterativo(Iterator<String> i1, Iterator<String> i2, Predicate<Punto2D> prd,
			Function<String, Punto2D> conver) {
		List<Punto2D> res = new ArrayList<>();

		Punto2D p1 = (i1.hasNext()) ? conver.apply(i1.next()) : null;
		Punto2D p2 = (i2.hasNext()) ? conver.apply(i2.next()) : null;

		while ((p1 != null || p2 != null)) {
			// Punto2D p = null;

			if (p1 != null && (p2 == null || Comparator2.isLENull(p1, p2))) { // esta 1 condición define el orden
				// Le toca el turno a p1
				if (p1 != null && prd.test(p1)) {
					res.add(p1);

				}
				// else if (p2!= null&&prd.test(p2)) {
				// res.add(p2);
				// p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				// p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				// }else {
				// p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				// p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				// }
				p1 = (i1.hasNext()) ? conver.apply(i1.next()) : null;
			} else {// er toca el turno a p2
				if (p2 != null && prd.test(p2)) {
					res.add(p2);
					p2 = (i2.hasNext()) ? conver.apply(i2.next()) : null;
				}
				// else if (p1!= null&&prd.test(p1)) {
				// res.add(p1);
				// p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				// p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				// }else {
				// p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				// p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				// }
				p2 = (i2.hasNext()) ? conver.apply(i2.next()) : null;
			}

		}
		return res;
	}

	// versión recursiva final
	public static List<Punto2D> ej3RecursivoFinalV1(String file1, String file2) {
		List<Punto2D> ac = new ArrayList<>(); // ACUMULADOR

		Function<String, Punto2D> conver = e -> { // convierte a punto2d
			String[] p = e.split(",");
			Double x = Double.parseDouble(p[0].trim());
			Double y = Double.parseDouble(p[1].trim());
			return Punto2D.of(x, y);

		};
		// Hacemos el predicado
		// e predicate, creo que se pueden meter sin problemas
		// dentro del bucle recursivo
		Predicate<Punto2D> condicion = p -> {
			Boolean r = false;
			if (p.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				r = true;
			} else if (p.getCuadrante().equals(Cuadrante.PRIMER_CUADRANTE)) {
				r = true;
			}

			return r;
		};
		//		creamos los iteradores
		Iterator<String> i1 = new IteratorFile(file1);
		Iterator<String> i2 = new IteratorFile(file2);
		Punto2D p1 = (i1.hasNext()) ? conver.apply(i1.next()) : null;
		Punto2D p2 = (i2.hasNext()) ? conver.apply(i2.next()) : null;
		return bucleRecFinalAux(p1, p2, ac, i1, i2, conver, condicion);

	}

	private static List<Punto2D> bucleRecFinalAux(Punto2D p1, Punto2D p2, List<Punto2D> res, Iterator<String> i1,
			Iterator<String> i2, Function<String, Punto2D> conver, Predicate<Punto2D> prd) {
		if ((p1 != null || p2 != null)) {
			// Punto2D p = null;

			if (p1 != null && (p2 == null || Comparator2.isLENull(p1, p2))) { // esta 1 condición define el orden
				// Le toca el turno a p1
				if (p1 != null && prd.test(p1)) {
					res.add(p1);

				}
				// else if (p2!= null&&prd.test(p2)) {
				// res.add(p2);
				// p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				// p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				// }else {
				// p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				// p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				// }
				p1 = (i1.hasNext()) ? conver.apply(i1.next()) : null;
			} else {// caso contrario
				if (p2 != null && prd.test(p2)) {
					res.add(p2);
					p2 = (i2.hasNext()) ? conver.apply(i2.next()) : null;
				}
				// else if (p1!= null&&prd.test(p1)) {
				// res.add(p1);
				// p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				// p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				// }else {
				// p1 = (i1.hasNext())?conver.apply(i1.next()):null;
				// p2 = (i2.hasNext())?conver.apply(i2.next()):null;
				// }
				p2 = (i2.hasNext()) ? conver.apply(i2.next()) : null;
			}
			bucleRecFinalAux(p1, p2, res, i1, i2, conver, prd);

		}

		return res;
	}

	// vo y a intentar la funcional para aclararme las ideas
	public static List<Punto2D> ej3Funcional(String file1, String file2) {
		// Funtion que convierte string a punto2d
		Function<String, Punto2D> funk = e -> {
			String[] p = e.split(",");
			Double x = Double.parseDouble(p[0].trim());
			Double y = Double.parseDouble(p[1].trim());
			return Punto2D.of(x, y);

		};
		// Hacemos el predicado
		Predicate<Punto2D> condicion = p -> {
			Boolean r = false;
			if (p.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
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
		} //
		// convertimos a punto2d
		return lineas.map(funk).filter(condicion).sorted().toList();

	}
	
	public static List<Punto2D> ej3FuncionalV2 (String file1, String file2){
		
		
		Function<String, Punto2D> conver = e -> {
			String[] p = e.split(",");
			Double x = Double.parseDouble(p[0].trim());
			Double y = Double.parseDouble(p[1].trim());
			return Punto2D.of(x, y);

		};
		Iterator<Punto2D> i1 = Stream2.file(file1).map(conver).iterator();
		Iterator<Punto2D> i2 = Stream2.file(file2).map(conver).iterator();
		Punto2D p1 = (i1.hasNext()) ? i1.next() : null;
		Punto2D p2 = (i2.hasNext()) ? i2.next() : null;
		
		Predicate<Punto2D> condicion = p -> {
			Boolean r = false;
			if (p.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
				r = true;
			}

			return r;
		};
		Predicate<ParDePuntos2>frenos = pp-> pp.getP1() != null || pp.getP2() !=null;
//		Function<ParDePuntos, Punto2D>daElMenor = pp ->{
//			Punto2D pp1 = pp.getP1();
//			Punto2D pp2 = pp.getP2();
//			Punto2D Pmenor;
//			if( pp1 != null) {
//				 Pmenor = Comparator2.isLENull(pp1, pp2)? pp1 : pp2;
//			}else if(pp2 != null) {
//				Pmenor = pp2;
//			}
//			else {
//				Pmenor = null;
//			}
//			
//			return Pmenor;
//		};
		UnaryOperator<ParDePuntos2> next = pp ->{
			Punto2D pp1 = pp.getP1();
			Punto2D pp2 = pp.getP2();
			if(pp1!=null ||pp2 !=null) {
			if(pp1 != null && (pp2 == null || Comparator2.isLENull(pp1, pp2))) {
				pp1 = (i1.hasNext()) ? i1.next() : null;
			}else if (pp2!=null) {
				 pp2 = (i2.hasNext()) ? i2.next() : null;
				
			}}
			return ParDePuntos2.create(pp1, pp2);
		};
	
		
		return Stream.iterate(ParDePuntos2.create(p1, p2),frenos,next).map(p-> p.daElMenor()).filter(condicion).toList();
	}
	
//
//	public static List<Punto2D> ej3FuncionalV43434(String file1, String file2) {
//		Function<String, Punto2D> conver = e -> {
//			String[] p = e.split(",");
//			Double x = Double.parseDouble(p[0].trim());
//			Double y = Double.parseDouble(p[1].trim());
//			return Punto2D.of(x, y);
//
//		};
//		// Hacemos el predicado
//		Predicate<Punto2D> condicion = p -> {
//			Boolean r = false;
//			if (p.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
//				r = true;
//			}
//
//			return r;
//		};
//		//		Iterator<String> i1 = new IteratorFile(file1);
//		//		Iterator<String> i2 = new IteratorFile(file2);
//		Iterator<Punto2D> i1 = Stream2.file(file1).map(conver).iterator();
//		Iterator<Punto2D> i2 = Stream2.file(file2).map(conver).iterator();
//		// se vueve redundante
//		Punto2D p1 = (i1.hasNext()) ? i1.next() : null;
//		Punto2D p2 = (i2.hasNext()) ? i2.next() : null;
//		Punto2D Pmenor = Comparator2.isLENull(p1, p2)? p1 : p2;
//		Pair<ParDePuntos, Punto2D> inicio = Pair.of(ParDePuntos.create(p1, p2), Pmenor);
//		UnaryOperator<Pair<ParDePuntos, Punto2D>> siguiente = x -> {
//			ParDePuntos s = x.first(); //Sig de siguiente
//			Punto2D p1Sig = s.getP1();
//			Punto2D p2Sig = s.getP2();
//			Punto2D PmenorSig ;
//			if ((p1Sig != null || p2Sig != null)){
//				if (p1Sig != null && (p2Sig == null || Comparator2.isLENull(p1Sig, p2Sig))) {
//					// esta 1 condición define el orden
//					// Le toca el turno a p1
//
//					if(p1!= null)
//						p1Sig = (i1.hasNext()) ? i1.next() : null;
//
//					// }
//
//				} else {// er toca el turno a p2
//					if(p2 !=null) {
//						p2Sig = (i2.hasNext()) ? i2.next() : null;
//					}
//
//				}
//				
//				if( p1 != null) {
//					 PmenorSig = Comparator2.isLENull(p1, p2)? p1 : p2;
//				}else if(p2 != null) {
//					PmenorSig = p2;
//				}
//				else {
//					PmenorSig = null;
//				}
//			}
//			
//			return Pair.of(ParDePuntos.create(p1Sig, p2Sig), Pmenor);
//		};
//
//		Predicate<Pair<ParDePuntos,Punto2D>> pr = u -> u.first().getP1() != null || u.first().getP2() != null;
//
//
//
//		List<Punto2D> res = new ArrayList<>();
//		// primero concatenamos en zip
//
//		return Stream.iterate(inicio, pr, siguiente).map(p -> p.second()).filter(condicion).distinct().collect(Collectors.toList());
//
//	}
	//	public static List<Punto2D> ej3FuncionalV3(String file1, String file2) {
	//		Function<String, Punto2D> conver = e -> {
	//			String[] p = e.split(",");
	//			Double x = Double.parseDouble(p[0].trim());
	//			Double y = Double.parseDouble(p[1].trim());
	//			return Punto2D.of(x, y);
	//
	//		};
	//		// Hacemos el predicado
	//		Predicate<Punto2D> condicion = p -> {
	//			Boolean r = false;
	//			if (p.getCuadrante() == Cuadrante.PRIMER_CUADRANTE || p.getCuadrante() == Cuadrante.TERCER_CUADRANTE) {
	//				r = true;
	//			}
	//
	//			return r;
	//		};
	//
	//		Iterator<Punto2D> i1 = Stream2.file(file1).map(conver).iterator();
	//		Iterator<Punto2D> i2 = Stream2.file(file2).map(conver).iterator();
	//		Punto2D p1 = (i1.hasNext()) ? i1.next() : null;
	//		Punto2D p2 = (i2.hasNext()) ? i2.next() : null;
	//		Punto2D Pmayor = p1.compareTo(p2) < 0 ? p1 : p2;
	//		
	//		UnaryOperator<Punto2D> siguiente = x -> {
	//		Punto2D P = x;
	//		
	//			if (p1 != null && (p2 == null || Comparator2.isLENull(p1, p2))) {
	//				// esta 1 condición define el orden
	//				// Le toca el turno a p1
	//					P = p1;
	//				p1 = (i1.hasNext()) ? i1.next() : null;
	//
	//				// }
	//
	//			} else {// er toca el turno a p2
	//				P = p2;
	//				p2 = (i2.hasNext()) ? i2.next() : null;
	//			}
	//			
	//			return P;
	//
	//		};
	//
	//   Predicate<Punto2D> pr = u -> p1 != null || p2 != null;
	//		
	//		 
	//	
	//		List<Punto2D> res = new ArrayList<>();
	//		// primero concatenamos en zip
	//
	//		return Stream.iterate(Pmayor, pr, siguiente).filter(condicion).distinct().collect(Collectors.toList());
	//
	//	}

	

}
