package Ejercicios;

import us.lsi.common.Comparator2;
import us.lsi.geometria.Punto2D;

public record ParDePuntos2(Punto2D p1, Punto2D p2) {
	//a este le da igual si los puntos son nulos

	public Punto2D getP1() {
		return p1;
	}

	public Punto2D getP2() {
		return p2;
	}
public  Punto2D daElMenor() {
	Punto2D pp1 = this.getP1();
	Punto2D pp2 = this.getP2();
	Punto2D Pmenor;
	if( pp1 != null) {
		 Pmenor = Comparator2.isLENull(pp1, pp2)? pp1 : pp2;
	}else if(pp2 != null) {
		Pmenor = pp2;
	}
	else {
		Pmenor = null;
	}
	
	return Pmenor;
}

public static ParDePuntos2 create(Punto2D pp1, Punto2D pp2) {
	// TODO Auto-generated method stub
	return new ParDePuntos2(pp1, pp2);
}
}
