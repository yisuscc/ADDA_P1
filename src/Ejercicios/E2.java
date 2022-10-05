package Ejercicios;

public class E2 {
	
	
	//versión iterativa  while
	
	// versión recursiva no final
	public static Integer Ej2RecNF(Integer a, Integer b, String s) {
		return Ej2RecNFAux(a, b, s);
		
	}
	private static Integer Ej2RecNFAux(Integer a, Integer b, String s){
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
				 r= a+b+Ej2RecNFAux(a-1, b/2, s.substring(a%s.length(), b%s.length()));
			 }			 
			 
			 else {
				 r = a*b+Ej2RecNFAux(a/2, b-1, s.substring(b%s.length(), a%s.length()));
				
			 }
		
		}
		return r;
		
		
	}
	// version recursiva  final 
	public static Integer Ej2RecFinal(Integer a, Integer b, String s) {
		return Ej2RecFinalAux(a, b, s, 0);
		
}
private static Integer Ej2RecFinalAux(Integer a, Integer b, String s,Integer acum) {
	Integer r = null; 
	if(0<=a|| 0<=b){
		if(s.length()==0) {
			 r = a*a+b*b +acum;
		 }
		else if (a<2 || b<2){
			r = s.length()+a+b+ acum;
		}
		 else if (a%s.length()<b%s.length()) {
			 acum = a+b +acum;
			 r= Ej2RecFinalAux(a-1, b/2, s.substring(a%s.length(), b%s.length()),acum);
		 }			 
		 
		 else {
			 acum = a*b +acum;
			 r = Ej2RecFinalAux(a/2, b-1, s.substring(b%s.length(), a%s.length()),acum);
		 }
			
			
		 }
	return r;
	
}

	// versiónn funcional
	
	


}
