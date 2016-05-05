package algebra.polinomio;

import java.util.ArrayList;
import java.util.List;

public class ejecutable {
	
	public static void main(String[] args) {
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(0);
		lista.add(2);
		Termino term1 = new Termino(3,lista);
		
		List<Integer> lista2 = new ArrayList<Integer>();
		lista2.add(1);
		lista2.add(1);
		lista2.add(2);
		Termino term2 = new Termino(5,lista2);
		
		List<Integer> lista3 = new ArrayList<Integer>();
		lista3.add(1);
		lista3.add(12);
		lista3.add(2);
		Termino term3 = new Termino(0,lista3);
		
		List<Integer> lista4 = new ArrayList<Integer>();
		lista3.add(1);
		lista3.add(0);
		lista3.add(2);
		Termino term4 = new Termino(12,lista3);
		
		List<Integer> lista5 = new ArrayList<Integer>();
		lista3.add(1);
		lista3.add(0);
		lista3.add(2);
		Termino term5 = new Termino(22,lista3);
		
		Polinomio pol1=new Polinomio();
		pol1.add(term1);
		pol1.add(term2);
		pol1.add(term3);
		pol1.add(term4);
		pol1.add(term5);
		
		
		List<Integer> lista6 = new ArrayList<Integer>();
		lista6.add(2);
		lista6.add(3);
		lista6.add(0);
		Termino term6 = new Termino(3,lista6);
		
		List<Integer> lista7 = new ArrayList<Integer>();
		lista7.add(1);
		lista7.add(1);
		lista7.add(2);
		Termino term7 = new Termino(4,lista7);
		
		List<Integer> lista8 = new ArrayList<Integer>();
		lista8.add(2);
		lista8.add(2);
		lista8.add(0);
		Termino term8 = new Termino(1,lista8);
		
		List<Integer> lista9 = new ArrayList<Integer>();
		lista9.add(1);
		lista9.add(0);
		lista9.add(2);
		Termino term9 = new Termino(-10,lista9);
		
		Polinomio pol2=new Polinomio();
		pol2.add(term6);
		pol2.add(term7);
		pol2.add(term8);
		pol2.add(term9);
		
		System.out.println("Polinomio1= " + pol1);
		System.out.println("Polinomio2= " + pol2);
		pol1.ordenar();
		pol2.ordenar();
		System.out.println("Polinomio1 ordenado= " + pol1);
		System.out.println("Polinomio2 ordenado= " + pol2);
		
		pol1.agrupar();
		pol2.agrupar();
		System.out.println("Polinomio1 agrupado= " + pol1);
		System.out.println("Polinomio2 agrupado= " + pol2);
		
		System.out.println("Suma= " + Polinomio.sumar(pol1, pol2) );
		System.out.println("Resta= " + Polinomio.restar(pol1, pol2) );

		
	}

}
