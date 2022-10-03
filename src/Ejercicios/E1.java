package Ejercicios;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class E1 {
	
	 // versión del eninciado 
	public static Map<Integer,List<String>>ejercicioA ( Integer varA, String varB,
			Integer varC,String varD, Integer varE){
		UnaryOperator<EnteroCadena> nx = elem ->
		{ String aux ;
		if(elem.a()%3==0) {
			aux = elem.s()+elem.a().toString();
		}else {
			aux = elem.s().substring(elem.a()%elem.s().length());
		}
			return  EnteroCadena.of(elem.a()+2,aux);
		};
		return Stream.iterate(EnteroCadena.of(varA, varB), elem->elem.a()<varC,nx)
				.map(elem->elem.s()+varD)
				.filter(nom->nom.length()<varE)
				.collect(Collectors.groupingBy(String::length));
	}
	// version iterativa
	public static  Map<Integer, List<String>> ej1Iterativo ( Integer varA, String varB,
			Integer varC,String varD, Integer varE) {
		// creamos el diccionario 
		Map<Integer, List<String>> d = new HashMap<>();
		//  creamos la semilla 
		EnteroCadena s = new EnteroCadena(varA, varB);
		// creamos el predicado 
		Predicate<EnteroCadena> prdct = x->{
			return x.a()< varC;
		};
		// creamos la funcion de siguiente elemento
		// por legibilidad la pongo con ifs en lugar de operador ternario ?
		// bueno mejor no
		Function<EnteroCadena, EnteroCadena>  funk = x-> {
			return new EnteroCadena(x.a(), x.a()%3==0?x.s()+x.a().toString():x.s().substring(x.a()%x.s().length()));

		};
		//		creamos el bucle iterativo
		while (prdct.test(s)) {
			String valor = s.s()+varD;
			Integer clave = valor.length();
			if(clave<varE){if(d.containsKey(clave))  { // existe ya la clave
				List<String>ls = d.get(clave);
				ls.add(valor);
				d.put(clave, ls);
			}else {
				// no existe la clave 
				List<String> ls = new LinkedList<>();
				ls.add(valor);
				d.put(clave, ls);
			}
			}
			 s= funk.apply(s);

		}

		return d; 	
	}

	// versión recirsiva final
	public static  Map<Integer, List<String>> ej1Recursivo ( Integer varA, String varB,
			Integer varC,String varD, Integer varE){
		EnteroCadena s = new EnteroCadena(varA, varB);
		Map<Integer, List<String>> d = new HashMap<>();
		Function<EnteroCadena, EnteroCadena>  funk = x-> {
			return new EnteroCadena(x.a(), x.a()%3==0?x.s()+x.a().toString():x.s().substring(x.a()%x.s().length()));

		};
		return ej1RecursivoAux(varA, varB, varC, varD, varE, d, s, funk);

		

	}
	public static  Map<Integer, List<String>> ej1RecursivoAux ( Integer varA, String varB,
			Integer varC,String varD, Integer varE,  Map<Integer, List<String>> d, EnteroCadena s,Function<EnteroCadena, EnteroCadena> funk){
		String valor = s.s()+varD;
		Integer clave = valor.length();
		if(clave<varE) {
			if(d.containsKey(clave))  { // existe ya la clave
				List<String>ls = d.get(clave);
				ls.add(valor);
				d.put(clave, ls);
				EnteroCadena ns = funk.apply(s);
				ej1RecursivoAux(varA, varB, varC, varD, varE, d, ns, funk);
			}else {
				// no existe la clave 
				List<String> ls = new LinkedList<>();
				ls.add(valor);
				d.put(clave, ls);
				EnteroCadena ns = funk.apply(s);
				ej1RecursivoAux(varA, varB, varC, varD, varE, d, ns, funk);
			}

		}
		return d;

	}




}
