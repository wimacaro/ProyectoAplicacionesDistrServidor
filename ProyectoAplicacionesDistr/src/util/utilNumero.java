package util;

public class utilNumero {
	public static boolean esNumerio(String numero){
		boolean x;
		try {
			Integer.parseInt(numero);
			x= true;
		} catch (Exception e) {
			x = false;
		}
		return x;
	}
}
