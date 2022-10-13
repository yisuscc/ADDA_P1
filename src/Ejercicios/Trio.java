package Ejercicios;

import java.util.LinkedList;
import java.util.List;

public record Trio(Integer a , Integer b, Integer c) {
	public static Trio of (Integer a , Integer b, Integer c) {
		return new Trio(a, b, c);
	}
	public Trio nextTrio( Integer maxA, Integer maxB ,Integer maxC) {
		// Inicializamos 
		Integer nC = this.c();
		Integer nB = this.b();
		Integer nA = this.a();

		if(this.hasNext(maxA, maxB, maxC)) {
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
	public static Trio nextTrioStatic(Trio T, Integer maxA, Integer maxB ,Integer maxC) {
		// Inicializamos 
		Integer nC = T.c();
		Integer nB = T.b();
		Integer nA = T.a();
		if(T.hasNext(maxA, maxB, maxC)) {
			nC = nC+1;
			if (nC%(maxC+1)==0){
				nC =0;
				nB++;
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
		if(T.c()<=(maxC)||T.a() <maxA  || T.b()<maxB ) {
			r = true; 
		}
		return r;
	}
	
	public static List<Trio> fullSequence(Integer maxA, Integer maxB ,Integer maxC){
		List<Trio> res = new LinkedList<>();
		for(Integer i =0;i<= maxA;i++) {
			for(Integer j =0;j<= maxB;j++) {
				for(Integer k =0;k<= maxC;k++) {
					res.add(Trio.of(i, j, k));
				}
			}
		}
		return res;
	}

}
