package Ejercicios;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.Pair;

public class E2 {




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

	//versión iterativa  while
	public static Integer Ej2Iterativo(Integer a, Integer b, String s) {
		//Integer acum= 0;
		Integer r;
		Trio inicio = Trio.of(0, 0, 0);
		Trio res  = Trio.of(a,b,s.length());
		Map<Trio, Integer> d = new HashMap<>();
		// con for queda mucho mas bonito y mejor 
		Integer i = 0;  // se encarga de a
		Integer j =0; // se encarga de b
		Integer k = 0; // se encarga de s


		while(i<=a){
			while(j<=b){
				while(k<=s.length()) {

					if(k==0) {
						r = i*i+j*j;
						d.put(Trio.of(i, j, k), r);
					}
					else if (i<2 || i<2){
						r = k+i+j;
						d.put(Trio.of(i, j, k), r);
					}
					else if (i%k<j%k) {
						Integer n = j%k- i%k;
						r= i+j+d.get(Trio.of(i-1, j/2, n));
						d.put(Trio.of(i, j, k), r);
					}			 

					else {
						 Integer n = i%k- j%k;
						r = i*j+d.get(Trio.of(i/2, j-1, n));
						d.put(Trio.of(i, j, k), r);

					}
					k++;

				}
				j++;
			}
			i++;
		}
		return d.get(res);

	}
	// versiónn funcional




}
