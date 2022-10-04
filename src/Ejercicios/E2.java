package Ejercicios;

public class E2 {
	
	
	//versión iterativa  while
	
	// versión recursiva no final
	public static Integer Ej2Rec(Integer a, Integer b, String s) {
		return Ej2RecAux(a, b, s);
		
	}
	private static Integer Ej2RecAux(Integer a, Integer b, String s){
//		if(a<0|| b<0) {
//			throw new IllegalArgumentException("A y b tienen que ser positivos");
//		}
		Integer r = null; 
		if(0<=a|| 0<=b){
			if(s.length()==0) {
				 r = a*a+b*b;
			 }
			else if (a<2 || b<2){
				r = s.length()+a+b;
			}
			 else if (a%s.length()<b%s.length()) {
				 r= a+b+Ej2Rec(a-1, b/2, s.substring(a%s.length(), b%s.length()));
			 }			 
			 
			 else {
				 r = a*b+Ej2Rec(a/2, b-1, s.substring(b%s.length(), a%s.length()));
				
			 }
		
		}
		return r;
		
		
	}
	// version recursiva  no final 
	
	
	// versiónn funcional
	
	
	public static void main(String[] args) {
	System.out.println("Prueba" + Ej2Rec(50, 75, "algoritmo"));
	}

}
