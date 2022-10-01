package Ejercicios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Ej3 {


	public static String ej4Recsm(Integer a, Integer b, Integer c) {
		String s;

		if (a<2&& b<= 2||c<2) {
			s = "(" +a.toString() + "+" +b.toString()+"+"+c.toString()+ ")";
		}else if (a<3||b<3&&c<=3) {
			s= "(" +c.toString() + "-" +b.toString()+"-"+a.toString()+ ")";
		}
		else if ((b%a== 0)&&((a%2==0)||(b%3==0))) {
			s= "(" +ej4Recsm(a-1, b/a, c-1)+ "*" +ej4Recsm(a-2, b/2, c/2)+ ")";
		}
		else {
			s= "(" +ej4Recsm(a/2, b-2, c/2)+ "/" +ej4Recsm(a/3, b-1, c/3)+ ")";
		}

		return s;
	}

	public static String ej4RecCm(Integer a, Integer b, Integer c) {
		//he creado un record trio, porque no consigo importar el de repositorio 
		Map<Trio, String> d = new HashMap();
return null;
	}
	private static String ej4RecCm(Integer a, Integer b, Integer c,Map<Trio,String> d ) {
		Trio  clave = new Trio(a, b, c);
		String valor ;

		if (d.containsKey(clave)) {
			valor = d.get(clave);
		}else {
			if (a<2&& b<= 2||c<2) {
				valor = "(" +a.toString() + "+" +b.toString()+"+"+c.toString()+ ")";
				d.put(clave, valor);
			}else if (a<3||b<3&&c<=3) {
				valor= "(" +c.toString() + "-" +b.toString()+"-"+a.toString()+ ")";
				d.put(clave, valor);
			}
			else if ((b%a== 0)&&((a%2==0)||(b%3==0))) {
				valor= "(" +ej4Recsm(a-1, b/a, c-1)+ "*" +ej4Recsm(a-2, b/2, c/2)+ ")";
				d.put(clave, valor);
			}
			else {
				valor= "(" +ej4Recsm(a/2, b-2, c/2)+ "/" +ej4Recsm(a/3, b-1, c/3)+ ")";
				d.put(clave, valor);
			}
		}
		return valor;

	}
	public static String ej4iterCM(Integer a, Integer b, Integer c) {
		// por limpiza del codigo lo voy a hacer con for 
		Map<Trio, String> d = new HashMap();
		Trio  claveFinal = new Trio(a, b, c);
		for (Integer i =0;i<=a;i++) {
			for (Integer j=0; j<=b;j++) {
				for(Integer k=0;k<=c;k++) {
					Trio  clave = new Trio(i, j, k);
					String valor ;
					if (i<2&& j<= 2||k<2) {
						valor = "(" +i.toString() + "+" +j.toString()+"+"+k.toString()+ ")";
						d.put(clave, valor);
					}else if (i<3||j<3&&k<=3) {
						valor= "(" +k.toString() + "-" +j.toString()+"-"+i.toString()+ ")";
						d.put(clave, valor);
					}
					else if ((j%i== 0)&&((i%2==0)||(j%3==0))) {
						Trio  auxA = new Trio(i-1, j/i, k-1);
						Trio  auxB = new Trio(i-2, j/2, k/2);
						valor= "(" +d.get(auxA)+ "*" +d.get((auxB))+ ")";
						d.put(clave, valor);
					}
					else {
						Trio  auxC = new Trio(i/2, j-2, k/2);
						Trio  auxD = new Trio(i/3, j-1, k/3);
						valor= "(" +d.get(auxC)+ "/" +d.get(auxD)+ ")";
						d.put(clave, valor);
					}
					
				}
				
			}
			
		}
		return d.get(claveFinal);
		
		

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
