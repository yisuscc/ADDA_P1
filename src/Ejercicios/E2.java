package Ejercicios;

public class E2 {
	
	
	//versión iterativa  while
	
	// versión recursiva final
	public static Integer Ej2Rec(Integer a, Integer b, String s){
		if(a<0|| b<0) {
			throw new IllegalArgumentException("A y b tienen que ser positivos");
		}
		 Integer r;
		 if(s.length()==0) {
			 r = a*a+b*b;
		 }
		 else if (a<2 || b<2) {
			 r = a+b+Ej2Rec(a-1, b/2, s.substring(a%s.length(), b%s.length()));
		 }			 
		 
		 else {
			 r = a*b+Ej2Rec(a/2, b-12, s.substring(b%s.length(), a%s.length()));
		 }
		
		return r;
		
	}
	// version recursiva  no final 
	
	
	// versiónn funcional

}
