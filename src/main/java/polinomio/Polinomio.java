package algebra.polinomio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Polinomio {

	List<Termino> terminos;
	
	public Polinomio(){
		terminos = new ArrayList<Termino>();
	}
	@Override
	public String toString(){
		String aux="";
		for(Termino t:this.terminos){
			aux+="+"+t;
		}
		return aux;
	}
//	public Polinomio(String s) throws Exception{//Ni puto caso a este metodo
//		terminos = new ArrayList<Termino>();
//		
//		char[] charArray = s.toCharArray();
//		Termino term;
//		
//		boolean finaliza=false;
//		
//		while(!finaliza){
//			
//		}
//		
//		
//	}
	public void add(Termino term){
		terminos.add(term);
	}
	
	public void ordenar(){
		terminos.sort(new Comparator<Termino>(){//Los ordena
			@Override
			public int compare(Termino t0, Termino t1) {
				int N = Variables.N;
				for(int i=0;i<N;i++){
					if(t0.monomio.get(i)!=t1.monomio.get(i)){
						return t1.monomio.get(i)-t0.monomio.get(i);
					}
				}
				return 0;
			}
		});
		
	}
	public void agrupar(){
		this.terminos = agruparRec(0,this.terminos);
	}
	
	private List<Termino> agruparRec(int index,List<Termino> lista){
		if(index==lista.size()-1){
			return lista;
		}else{
			Termino nuevo,aux1,aux2;
			aux1=lista.get(index);
			aux2=lista.get(index+1);
			if(aux1.equals(aux2)){
				nuevo=Termino.sumar(aux1, aux2);
				lista.remove(index+1);
				lista.remove(index);
				lista.add(index, nuevo);
			}else if(aux1.coeficiente==0){
				lista.remove(index);
			}else{
				index=index+1;
			}
			return agruparRec(index,lista);
		}
	}
	public static Polinomio concatenar(Polinomio pol1, Polinomio pol2){
		Polinomio aux=new Polinomio();
		aux.terminos.addAll(pol1.terminos);
		aux.terminos.addAll(pol2.terminos);
		return aux;
	}
	public static Polinomio sumar(Polinomio sumando1, Polinomio sumando2){
		Polinomio aux=Polinomio.concatenar(sumando1, sumando2);
		aux.ordenar();
		aux.agrupar();
		return aux;
	}
	
	public static Polinomio restar(Polinomio minuendo, Polinomio sustraendo){
		for(Termino t:sustraendo.terminos){
			t.coeficiente=-1*t.coeficiente;
		}
		Polinomio aux=Polinomio.concatenar(minuendo, sustraendo);
		aux.ordenar();
		aux.agrupar();
		return aux;
	}
	
}
