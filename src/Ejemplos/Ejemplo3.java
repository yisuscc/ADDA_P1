package Ejemplos;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.IntPair;

public class Ejemplo3 {
public static  Integer solRecSinMemoria(Integer a, Integer b) {
	Integer r ;
	if (a<2||b<2) {
		r = a*a+b;
	}
	else {
		r = solRecSinMemoria(a/2, b-1)+ solRecSinMemoria(a/3, b-2);
	}
	return r; 
}

public static  Integer solRecConMemoria(Integer a, Integer b) {

	return recMem(a, b, new HashMap<>()); 
}
private static Integer  recMem(Integer a, Integer b, Map<IntPair, Integer> m) {
	Integer r ; 
	IntPair key = IntPair.of(a, b);
	if (m.containsKey(key)) {
		r = m.get(key);
	}else {
		if (a<2||b<2) {
			r = a*a+b;
		}
		else {
			r = recMem(a/2, b-1,m)+ recMem(a/3, b-2,m);
		}
		m.put(key, r);
	}
	return r ;
}
public static  Integer sol(Integer a, Integer b) {
	Map<IntPair, Integer> m = new HashMap<>();
	Integer r;
	for (int i = 0;i<=a;i++) {
		for(int j =0 ; i<= b; j++) {
			if (i<2||j<2) {
				r = i+i+b;
			}
			else {
				r = m.get(IntPair.of(i/2,j-1))+ m.get(IntPair.of(i/3, j-2));
			}
			m.put(IntPair.of(i,j), r);
		}
	}
	return m.get(IntPair.of(a,b));
}


}
