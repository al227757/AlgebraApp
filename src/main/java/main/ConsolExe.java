package main;

import estructuras.*;
import modelo.Cuaderno;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsolExe {
    public static void main(String[] args) {
        Cuaderno cuaderno = new Cuaderno();

        Polinomio dividendo = new Polinomio();
        int[] l10 = {2,1,0};
        dividendo.addTermino(new Termino(2,l10));
        int[] l11 = {1,2,0};
        dividendo.addTermino(new Termino(3,l11));
        int[] l12 = {0,2,0};
        dividendo.addTermino(new Termino(1,l12));

        List<Polinomio> divisores = new ArrayList<Polinomio>();
        Polinomio divisor1 = new Polinomio();
        Polinomio divisor2 = new Polinomio();
        int[] l13 = {1,1,0};
        int[] l14 = {0,1,0};
        divisor1.addTermino(new Termino(2,l13));
        divisor1.addTermino(new Termino(-3,l14));
        divisor2.addTermino(new Termino(1,l12));
        divisor2.addTermino(new Termino(-2,l14));
        divisores.add(divisor1);
        divisores.add(divisor2);

        System.out.println("Dividendo: "+ dividendo);
        System.out.println("divisor1: "+ divisor1);
        System.out.println("divisor2: "+ divisor2);

        System.out.println("Spolinomio(divisor1,divisor2): "+ cuaderno.sPolinomio(divisor1,divisor2));

        List<Polinomio> division = cuaderno.dividir(dividendo, divisores);
        for (int i = 0; i < division.size(); i++){
            if ( i != division.size()-1 ){
                System.out.println("Cociente "+i+" --> "+ division.get(i));
            }else{
                System.out.println("Resto --> "+division.get(i));
            }

        }

        System.out.println("------------GROBNER----------");
        Polinomio h1=new Polinomio();
        int[] h11 = {2,2,0};
        int[] h12 = {0,1,0};
        int[] h13 = {0,3,0};
        h1.addTermino(new Termino(1,h11));
        //h1.addTermino(new Termino(-1,h12));
        //h1.addTermino(new Termino(2,h13));
        Polinomio h2=new Polinomio();
        int[] h21 = {1,3,0};
        int[] h22 = {1,0,0};
        h2.addTermino(new Termino(1,h21));
        h2.addTermino(new Termino(1,h22));

        List<Polinomio> f = new LinkedList<Polinomio>();
        f.add(h1);
        f.add(h2);

        List<Polinomio> grobner = cuaderno.groebnerBase(f);
        System.out.println("Grobner base"+grobner);

        System.out.println("------------DIVISION----------");

        System.out.println(cuaderno.dividir(h1,f));


        ///// EJEMPLO 1 /////

        System.out.println("EJEMPLO1");
        Polinomio pol1=new Polinomio();
        Polinomio pol2=new Polinomio();
        int[] term1 = {2,0,0};
        int[] term2 = {1,1,0};
        int[] term3 = {0,2,0};
        pol1.addTermino(new Termino(1,term1));
        pol2.addTermino(new Termino(1,term2));
        pol2.addTermino(new Termino(-1,term3));

        List<Polinomio> base = new LinkedList<Polinomio>();
        base.add(pol1);
        base.add(pol2);

        List<Polinomio> grobner1 = cuaderno.groebnerBase(base);
        System.out.println("Grobner base"+grobner1);
        System.out.println("Minimal base"+cuaderno.minimalGroebnerBase(base));

        ///// EJEMPLO 2 /////

        System.out.println("EJEMPLO2");
        Polinomio pol3=new Polinomio();
        Polinomio pol4=new Polinomio();

        int[] mon1 = {3,0,0};
        int[] mon2 = {1,1,0};

        int[] mon4 = {2,1,0};
        int[] mon5 = {0,2,0};
        int[] mon6 = {1,0,0};

        pol3.addTermino(new Termino(1,mon1));
        pol3.addTermino(new Termino(-2,mon2));


        //pol4.addTermino(new Termino(1,mon4));
        pol4.addTermino(new Termino(-2,mon5));
        pol4.addTermino(new Termino(1,mon6));


        List<Polinomio> base2 = new LinkedList<Polinomio>();
        base2.add(pol3);
        base2.add(pol4);


        List<Polinomio> grobner2 = cuaderno.groebnerBase(base2);
        System.out.println("Grobner base"+grobner2);
    }
}
