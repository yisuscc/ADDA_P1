package Ejemplos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.geometria.Punto2D;
import us.lsi.geometria.Punto2D.Cuadrante;

public class Ejemplo1 {
	// Enunciado  funcional
	public static Map<Punto2D.Cuadrante,Double> solucionFuncional(List<Punto2D> l){
		return l.stream()
		.collect(Collectors.groupingBy(Punto2D::getCuadrante,
		Collectors.<Punto2D,Double>reducing(0.,p->p.x(),(z,w)->z+w)));
		}
	
	
	public static Map<Punto2D.Cuadrante,Double> solucionIterativa(List<Punto2D> l){
		//Secuencia: indice sobre la Lista de puntos 
		// Estado inicial de la secuencia; 0
		// Estado final de la secuencia : l.size()
		
		
		//Acumulador : Map<Punto2D.Cuadrante,Double>
		//Estado Inicial: Map vacío.
		
		
		// INicializacion 
		Integer e = 0;
		Map<Punto2D.Cuadrante,Double> ac = new HashMap<>();
		
		// bucle  cuya condicion de parada es cuando se cumpla el estado final de la secuencia
		while (e<l.size()) {
			// Actualizar acumulador 
			Punto2D p = l.get(e);
			Cuadrante  key = p.getCuadrante();
			if(ac.containsKey(key)) {
				Double valorAnterior = ac.get(key);
				ac.put(key, valorAnterior+p.x());
			}
			else {
				ac.put(key, p.x());
			}
			// actualizar secuencia
			e++;
		}
		
		return ac; 
		
	}
	
// 
	public static Map<Punto2D.Cuadrante,Double> solucionRecursivaFinal(List<Punto2D> l){
		// Primera llamada a rec final con valores iniciales
		return recFinal(l,0, new HashMap<>());
	}
	private static Map<Punto2D.Cuadrante,Double> recFinal(List<Punto2D> l,Integer e, Map<Punto2D.Cuadrante,Double> ac){
		if (e<l.size()) {
			// Actualizar acumulador 
			Punto2D p = l.get(e);
			Cuadrante  key = p.getCuadrante();
			if(ac.containsKey(key)) {
				Double valorAnterior = ac.get(key);
				ac.put(key, valorAnterior+p.x());
			}
			else {
				ac.put(key, p.x());
			}
			// llamada recursiva actualizando secuencia y acumulador
			ac = recFinal(l, e+1, ac);
		}
		return ac; 
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
