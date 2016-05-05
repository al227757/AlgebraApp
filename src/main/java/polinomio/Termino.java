package algebra.polinomio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Termino{
	static int N = Variables.N;
	int coeficiente;
	List<Integer> monomio;
	
	public Termino(int coeficiente, List<Integer> monomio){
			this.coeficiente=coeficiente;
			this.monomio=new ArrayList<Integer>();
			for(int i=0;i<N;i++){
				this.monomio.add(monomio.get(i));
			}
	}
	@Override
	public String toString(){
		String cadena=""+coeficiente+"*[";
		for(int i=0;i<N-1;i++){
			cadena=cadena+this.monomio.get(i)+",";
		}
		cadena=cadena+this.monomio.get(N-1)+"]";
		return cadena;
		
	}
	
	public static Termino sumar(Termino s1, Termino s2){
		if(s1.monomio.equals(s2.monomio)){
			return new Termino(s1.coeficiente+s2.coeficiente, s1.monomio);
		}else{
			return null;
		}
	}
	
	public static Termino restar(Termino s1, Termino s2){
		if(s1.monomio.equals(s2.monomio)){
			return new Termino(s1.coeficiente-s2.coeficiente, s1.monomio);
		}else{
			return null;
		}
	}


	public boolean equals(Termino t1) {
		
		for(int i=0;i<N;i++){
			if(this.monomio.get(i)!=t1.monomio.get(i)){
				return false;
			}
		}
		return true;
	}
	public static Termino multiplicar(Termino t1, Termino t2){
		int newCoeficiente=t1.coeficiente*t2.coeficiente;
		List<Integer> newMonomio=new ArrayList<Integer>();
		for (int i=0;i<N;i++){
			newMonomio.add(t1.monomio.get(i)+t2.monomio.get(i));
		}
		return new Termino(newCoeficiente,newMonomio);
	}
	
	
}
