package Ejercicios;

import java.util.ArrayList;
import java.util.List;

public class TestTrio {
public static void testIteradores(Integer a , Integer b, Integer c){
	 Trio T = Trio.of(0, 0, 0);
	
	System.out.println(T);
		
	while(T.hasNext(a, b, c)) {
		System.out.println(T);
		T = T.nextTrio( a, b, c);
	}
	
}
	public static void main(String[] args) {
		testIteradores(10, 20, 4);

	}

}
