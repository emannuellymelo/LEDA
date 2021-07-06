package recursao;

import java.util.Arrays;


public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		int indice = array.length-1;
		if(indice >= 1) {
			result += (array[indice] + array[indice-1]);
			array[indice-1] = result;
			return calcularSomaArray(Arrays.copyOfRange(array, 0, indice));
		}
		
		result = array[0];
		
		return result;
	}
	public long calcularFatorial(int n) {
		long result = 1;
		if(n == 0) {
			result *= 1;
		} else {
			result = n * calcularFatorial(n-1);
		}
		System.out.println(n + "! = "+ result);
		return result;
	}

	public int calcularFibonacci(int n) {
		int result = 1;
		if(n == 1 || n == 2) {
			result *= 1;
		} else {
			result = calcularFibonacci(n-1) + calcularFibonacci(n-2);
		}
		
		return result;
	}

	public int countNotNull(Object[] array) {
		int result = 0;
		result = countNotNull(array, 0);
		
		return result;
	}
	
	private int countNotNull(Object[] array, int indice) {
		int result = 0;
		
		if(array[indice] != null) {
			result ++;
		}
		if(indice < array.length-1) {
			indice ++;
			result += countNotNull(array, indice);
		}
		
		return result;
	}

	public long potenciaDe2(int expoente) {
		int result = 1;
		if(expoente == 0) {
			result *= 1;
		} else {
			result = (int) (2 * potenciaDe2(expoente-1));
		}
		return result;
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		double result = 0;
		result = termoInicial;
		if(n == 1) {
			result *= 1;
		} else {
			result = razao + progressaoAritmetica(termoInicial, razao, n-1);
		}
		
		return result;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		double result = 1;
		result = termoInicial;
		if(n == 1) {
			result *= 1;
		} else {
			result = razao * progressaoGeometrica(termoInicial, razao, n-1);
		}
		
		return result;
	}
	
	
}
