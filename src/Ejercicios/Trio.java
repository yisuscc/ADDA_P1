package Ejercicios;

public record Trio(Integer a , Integer b, Integer c) {
	public static Trio of (Integer a , Integer b, Integer c) {
		return new Trio(a, b, c);
	}

}
