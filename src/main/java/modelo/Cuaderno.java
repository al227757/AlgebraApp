package modelo;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import estructuras.OrdenLexicografico;
import estructuras.Polinomio;
import estructuras.Termino;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
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
    ////NUEVO
    public Termino lcm(Termino t1, Termino t2){
        int [] monomio1=t1.getMonomio();
        int [] monomio2=t2.getMonomio();
        int longitud=monomio1.length;
        int [] exponentes = new int[longitud];
        for(int i=0; i<longitud; i++){
            if(monomio1[i]>monomio2[i]){
                exponentes[i]=monomio1[i];
            }else{
                exponentes[i]=monomio2[i];
            }
        }
        return(new Termino(1,exponentes));

    }

    public Polinomio sPolinomio(Polinomio p1, Polinomio p2){
        Termino termino_comun = lcm(p1.leadingTerm(this.orden),p2.leadingTerm(this.orden));
        Termino coeficiente1=this.dividir(termino_comun,p1.leadingTerm(this.orden));
        Termino coeficiente2=this.dividir(termino_comun,p2.leadingTerm(this.orden));
        Polinomio aux1=this.multiplicar(p1,coeficiente1);
        Polinomio aux2=this.multiplicar(p2,coeficiente2);
        return this.restar(aux1,aux2);
    }

    public List<Polinomio> groebnerBase(List<Polinomio> F){
        List<Polinomio> G_prima = new ArrayList<Polinomio>();
        List<Polinomio> G = new ArrayList<Polinomio>(F);
        List<Polinomio> resultDivision;
        Polinomio resto;
        Polinomio sPol;
        while(G.size()!=G_prima.size()){
            G_prima=new LinkedList<Polinomio>(G);
            for(int i=0;i<G_prima.size();i++){
                for(int j=i;j<G_prima.size();j++){
                    if(i!=j){
                        System.out.println("            i: "+i+" j: "+j); //////////TEST
                        sPol=this.sPolinomio(G_prima.get(i),G_prima.get(j));

                        System.out.println("            S-POL "+sPol); //////////TEST

                        resultDivision=dividir(sPol,G_prima);

                        System.out.println("             RESULT DIVISION "+resultDivision); //////////TEST
                        resto=resultDivision.get(resultDivision.size()-1);

                        if (resto.getTerminos().size() != 0){
                            if(resto.leadingTerm(this.orden).getCoeficiente()!=0){
                                G.add(resto);
                                System.out.println("                 ADDED "+resto); //////////TEST
                            }
                        }
                    }
                }
            }
            System.out.println("SIZE: "+G.size()+"---"+G_prima.size()); //////////TEST
        }
        return G;


    }
    public List<Polinomio> minimalGroebnerBase(List<Polinomio> F){
        List<Polinomio> baseGroebner = this.groebnerBase(F);
        List<Polinomio> baseMinimal = new LinkedList<Polinomio>(baseGroebner);
        for(int i=0;i<baseGroebner.size();i++){

            for(int j=i;j<baseGroebner.size();j++){
                if(j!=i){
                    int [] coeficientes1=baseGroebner.get(i).leadingTerm(this.orden).getMonomio();
                    int [] coeficientes2=baseGroebner.get(j).leadingTerm(this.orden).getMonomio();
                    boolean jDividei=true;

                    for(int k=0;k<coeficientes1.length;k++){
                        if(coeficientes2[k]<=coeficientes1[k]){
                            jDividei=jDividei&&true;
                        }else{
                            jDividei=jDividei&&false;
                        }
                    }
                    if(jDividei){
                        baseMinimal.set(i,null);
                    }
                }

            }

        }

        List<Polinomio> newBaseMinimal = new LinkedList<Polinomio>();
        for(Polinomio p:baseMinimal){
            if(p!=null){
                newBaseMinimal.add(p);
            }
        }
        
        return newBaseMinimal;
    }


}
