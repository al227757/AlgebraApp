package main;

import estructuras.*;
import modelo.Cuaderno;

import java.util.ArrayList;
import java.util.List;

public class ConsolExe {
    public static void main(String[] args) {
        Cuaderno cuaderno = new Cuaderno();

        Polinomio dividendo = new Polinomio();
        int[] l10 = {2,1,0};
        dividendo.addTermino(new Termino(1,l10));
        int[] l11 = {1,2,0};
        dividendo.addTermino(new Termino(1,l11));
        int[] l12 = {0,2,0};
        dividendo.addTermino(new Termino(1,l12));

        List<Polinomio> divisores = new ArrayList<Polinomio>();
        Polinomio divisor1 = new Polinomio();
        Polinomio divisor2 = new Polinomio();
        int[] l13 = {1,1,0};
        int[] l14 = {0,0,0};
        divisor1.addTermino(new Termino(1,l13));
        divisor1.addTermino(new Termino(-1,l14));
        divisor2.addTermino(new Termino(1,l12));
        divisor2.addTermino(new Termino(-1,l14));
        divisores.add(divisor1);
        divisores.add(divisor2);

        List<Polinomio> division = cuaderno.dividir(dividendo, divisores);
        for (int i = 0; i < division.size(); i++){
            if ( i != division.size()-1 ){
                System.out.println("Cociente "+i+" --> "+ division.get(i));
            }else{
                System.out.println("Resto --> "+division.get(i));
            }

        }
    }
}
