package Ejercicios;

public record Trio(Integer a , Integer b, Integer c) {
	public static Trio of (Integer a , Integer b, Integer c) {
		return new Trio(a, b, c);
	}
	public static Trio nextTrio(Trio T, Integer maxA, Integer maxB ,Integer maxC) {
		// Inicializamos 
		Integer nC = T.c();
		Integer nB = T.b();
		Integer nA = T.a();
		nC = nC+1;
		if(nC<maxC||nA < maxA || nB<maxB) {
			nC = nC+1;
			if (nC%(maxC+1)==0){
				nC =0;
				nB = nB+1;
				if (nB%(maxB+1)==0) {
					nB = 0;
					nA++;
					if((nA%(maxA+1)==0)) {
						nC= 0;
						nB = 0;
						nA = 0;

					}

				}


			}
		}
		return Trio.of(nA, nB, nC);
	}
	public  Boolean hasNext(Integer maxA, Integer maxB ,Integer maxC) {
		 Trio T = this;
		Boolean r = false ; 
		if(T.c()<maxC||T.a() < maxA || T.b()<maxB) {
			r = true; 
		}
		return r;
	}

}
