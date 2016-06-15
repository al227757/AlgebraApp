package estructuras;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Polinomio {

    private List<Termino> terminos;

    public Polinomio(){
        this.terminos = new ArrayList<Termino>();
    }

    public Polinomio(Polinomio p){
        this.terminos = p.getTerminos();
    }

    public List<Termino> getTerminos(){
        return this.terminos;
    }

    public void setTerminos(List<Termino> terminos){
        this.terminos = terminos;
    }

    public void addTermino(Termino t){
        this.terminos.add(t);
    }

    public void addAllTerminos(List<Termino> terminos){
        this.terminos.addAll(terminos);
    }

    public void ordenar(Comparator<Termino> orden){
        terminos.sort(orden);
    }

    public Termino leadingTerm(Comparator<Termino> orden){
        terminos.sort(orden);
        return terminos.get(0);
    }

    private List<Termino> agruparRec(int index,List<Termino> lista){
        if( index == lista.size()-1 || lista.size() <= 1){
            return lista;
        }else{
            Termino nuevo,aux1,aux2;
            aux1 = lista.get(index);
            aux2 = lista.get(index+1);
            if( aux1.equals(aux2) ){
                nuevo = new Termino(aux1.getCoeficiente()+aux2.getCoeficiente(), aux1.getMonomio());
                lista.remove(index+1);
                lista.remove(index);
                if (nuevo.getCoeficiente() != 0f){
                    lista.add(index, nuevo);
                }
            }else if( aux1.getCoeficiente() == 0f ){
                lista.remove(index);
            }else{
                index = index+1;
            }
            return agruparRec(index,lista);
        }
    }

    public void agrupar(){
        this.terminos = agruparRec(0,this.terminos);
    }

    public String toString(){
        String aux = "";
        for( Termino t : this.getTerminos() ){
            aux += "+" + t;
        }
        return aux;
    }


}
