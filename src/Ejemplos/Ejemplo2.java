package Ejemplos;

import java.util.stream.Stream;

public class Ejemplo2 {
	public static String soluciónRecursivaNoFinal(Integer a,Integer b) {
		String r = null;
		if (a<5||b<5) {
			r = String.format("(%d)", a*b);
		}
		else {
			r= String.format("%d", a+b) + soluciónRecursivaNoFinal(a/2, b-2);
		}
		return r; 
	}

	//Secuencia: los valores de a y de b 
	// estado inicaal los valores iniciales de a y b 
	// estado final cuando a y b valgan menos de 5

	//acumulador  String resultado 
	// estado inicial acumulador: ""
	public static String recursividadFinal(Integer a,Integer b) {
		return recFinal("", a, b);
	}
	private static String recFinal(String ac,Integer a,Integer b) {
		String r = null ;
		if (a<5||b<5) {
			r = ac +String.format("(%d)", a*b);
		}
		else {
			r=  recFinal(ac+String.format("%d", a+b),a/2, b-2);
		}
return r; 
	}
	public static String soluciónIterativa(Integer a, Integer b) {
		String ac ="";
		while(!(a<5||b<5)) {
			ac = ac + String.format("(%d)", a*b);
			a /=2;
		}
		return ac+String.format("%d", (a+b),a/2, b-2);
	}
	public static record Tupla(String ac, Integer a, Integer b) {
		// Constructor
		public static Tupla of (String ac, Integer a, Integer b) {
			return new Tupla(ac, a, b);
		}

		// Inicializador
		public static  Tupla first(Integer a, Integer b) {
			return of ("",a ,b);
		}
		
		// actualizador 
		public Tupla next() {
			return of(ac+String.format("%d", a+b),a/2,b-2);
		}
		
	}
public static String  solucionFuncional(Integer a, Integer b) {
	Tupla t = Stream.iterate(Tupla.first(a, b),e-> e.next()).filter(e-> e.a()<5 ||e.b <5).findFirst().get();
	return  t.ac() +String.format("(%d)", t.a()*t.b());
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
