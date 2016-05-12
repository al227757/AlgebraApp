package estructuras;

import java.util.Arrays;

/**
 * Created by Shurberto on 10/05/2016.
 */
public class Termino {

    private float coeficiente;
    private int[] monomio;

    public Termino(int N){
        this.coeficiente = 0;
        this.monomio = new int[N];
    }

    public Termino(float coeficiente, int[] monomio){
        this.coeficiente = coeficiente;
        this.monomio = monomio;
    }

    public void setCoeficiente(float coeficiente){
        this.coeficiente = coeficiente;
    }

    public float getCoeficiente(){
        return this.coeficiente;
    }

    public void setMonomio(int[] monomio){
        this.monomio = monomio;
    }

    public int[] getMonomio(){
        return this.monomio;
    }

    //Que tienen el mismo monomio
    public boolean equals(Termino t1) {
        for( int i = 0; i < this.monomio.length; i++ ){
            if( this.monomio[i] != t1.getMonomio()[i] ){
                return false;
            }
        }
        return true;
    }

    public boolean divideA(Termino dividendo){
        for ( int i=0 ; i < this.monomio.length ; i++ ){
            if ( this.monomio[i] > dividendo.getMonomio()[i] ){
                return false;
            }
        }
        return true;
    }

    public String toString(){
        String cadena = "" + coeficiente + "*[";
        int N = monomio.length;
        for( int i = 0 ; i < N-1 ; i++ ){
            cadena = cadena + this.monomio[i] + ",";
        }
        cadena = cadena + this.monomio[N-1] + "]";
        return cadena;
    }


}
