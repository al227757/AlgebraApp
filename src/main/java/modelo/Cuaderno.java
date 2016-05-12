package modelo;

import estructuras.OrdenLexicografico;
import estructuras.Polinomio;
import estructuras.Termino;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cuaderno {

    private Comparator<Termino> orden;
    private int numVariables;

    public Cuaderno(){
        this.orden = new OrdenLexicografico();
        this.numVariables = 3;
    }

    public Cuaderno(int N, Comparator<Termino> orden){
        this.numVariables = N;
        this.orden = orden;
    }

    public void setOrden(Comparator<Termino> orden){
        this.orden = orden;
    }

    public void setNumVariables(int N){
        this.numVariables = N;
    }

    public int getNumVariables(){
        return this.numVariables;
    }

    public void polinomioBonito(Polinomio p){
        p.ordenar(this.orden);
        p.agrupar();
    }

    public Termino sumar(Termino t1, Termino t2){
        if ( t1.equals(t2) ){
            return new Termino(t1.getCoeficiente() + t2.getCoeficiente(), t1.getMonomio());
        } else {
            return null;
        }
    }

    public Polinomio sumar(Polinomio p1, Polinomio p2){
        Polinomio aux = new Polinomio();
        aux.addAllTerminos(p1.getTerminos());
        aux.addAllTerminos(p2.getTerminos());
        polinomioBonito(aux);
        return aux;
    }
    
    public Polinomio restar(Polinomio minuendo, Polinomio sustraendo){
        Polinomio sust = new Polinomio();
        Termino auxT;
        for ( Termino t : sustraendo.getTerminos() ){
            auxT = new Termino( -1*t.getCoeficiente(), t.getMonomio());
            sust.addTermino(auxT);
        }
        Polinomio aux = sumar(minuendo, sust);
        return aux;
    }
    
    public Termino multiplicar(Termino t1, Termino t2){
        float newCoeficiente = t1.getCoeficiente() * t2.getCoeficiente();
        int[] newMonomio = new int[this.numVariables];
        for ( int i = 0 ; i < this.numVariables ; i++ ){
            newMonomio[i] = t1.getMonomio()[i] + t2.getMonomio()[i];
        }
        return new Termino(newCoeficiente,newMonomio);
    }

    public Polinomio multiplicar(Polinomio p, Termino t){
        Polinomio aux = new Polinomio();
        for ( Termino termPol : p.getTerminos() ){
            aux.addTermino( multiplicar(t,termPol) );
        }
        polinomioBonito(p);
        return aux;
    }

    public Polinomio multiplicar(Polinomio p1, Polinomio p2){
        Polinomio aux = new Polinomio();
        for ( Termino term : p1.getTerminos() ){
            aux.addAllTerminos( multiplicar(p2,term).getTerminos() );
        }
        polinomioBonito(aux);
        return aux;
    }

    public Termino dividir(Termino dividendo, Termino divisor){
        if ( divisor.divideA(dividendo) ){
            Termino aux = new Termino(this.numVariables);
            aux.setCoeficiente( dividendo.getCoeficiente() / divisor.getCoeficiente() );
            for (int i = 0; i < this.numVariables; i++){
                aux.getMonomio()[i] = ( dividendo.getMonomio()[i] - divisor.getMonomio()[i] );
            }
            return aux;
        }else{
            return null;
        }
    }

    public List<Polinomio> dividir(Polinomio dividendo, List<Polinomio> divisores){
        List<Polinomio> cocientes = new ArrayList<Polinomio>();
        for ( int i = 0 ; i < divisores.size() ; i++ ){
            cocientes.add(new Polinomio());
        }
        Polinomio resto = new Polinomio();
        Polinomio p = new Polinomio(dividendo);
        while ( p.getTerminos().size() != 0){
            int i = 0;
            boolean divisionoccurred = false;
            while ( i < divisores.size() && !divisionoccurred ){
                Termino pLT = p.leadingTerm(this.orden);
                Termino dLT = divisores.get(i).leadingTerm(this.orden);
                if ( dLT.divideA(pLT) ){
                    Termino divLT = dividir( pLT, dLT );
                    cocientes.get(i).addTermino( divLT );
                    p = restar( p, multiplicar( divisores.get(i), divLT ) );
                    divisionoccurred = true;
                }else{
                    i++;
                }
            }
            if (!divisionoccurred){
                resto.addTermino(p.leadingTerm(this.orden));
                p.getTerminos().remove(p.leadingTerm(this.orden));
            }
        }
        cocientes.add(resto);
        return cocientes;
    }
}
