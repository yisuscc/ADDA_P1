package Ejercicios;

public record Trio(Integer a , Integer b, Integer c) {
	public static Trio of (Integer a , Integer b, Integer c) {
		return new Trio(a, b, c);
	}
	public static Trio nexTrio(Trio T, Integer maxA, Integer maxB ,Integer maxC) {
		// Inicializamos 
		Integer nC = T.c;
		Integer nB = T.b;
		Integer nA = T.a;
		nC = nC+1;
		 if (nC%(maxC+1)==0){
			 nC =0;
			 nB = nB+1;
			 if (nB%(maxB+1)==0) {
				//TODO 
				
			}
			 
			 
		 }else {
			
		 }
		 return Trio.of(nA, nB, nC);
	}

}
