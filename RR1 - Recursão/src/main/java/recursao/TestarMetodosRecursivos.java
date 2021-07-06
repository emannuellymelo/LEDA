package recursao;


public class TestarMetodosRecursivos {
	public static void main(String[] args) {
		// preencha esse metodo com codigo para testar a classe MetodosRecursivos.
		MetodosRecursivos testaRecursao = new MetodosRecursivos();
		int[] array = {1, 7, 10, 20, 50, 60};
		Object[] arrayComNulo = {1, null, "manu", 9, null};
		System.out.println(testaRecursao.calcularSomaArray(array));
		System.out.println(testaRecursao.calcularFatorial(10));
		System.out.println(testaRecursao.calcularFibonacci(7));
		System.out.println(testaRecursao.countNotNull(arrayComNulo));
		System.out.println(testaRecursao.potenciaDe2(10));
		System.out.println(testaRecursao.progressaoAritmetica(0, 2, 50));
		System.out.println(testaRecursao.progressaoGeometrica(1, 2, 3));
	}
}
