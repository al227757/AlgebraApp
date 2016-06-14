package estructuras;

import java.util.Comparator;

public class OrdenGraduadoLexicografico implements Comparator<Termino> {
    public int compare(Termino t0, Termino t1) {

        int N = t0.getMonomio().length;
        int gradoTotal0=0;
        int gradoTotal1=0;
        for ( int i = 0 ; i < N ; i++ ){
            gradoTotal0+=t0.getMonomio()[i];
            gradoTotal1+=t1.getMonomio()[i];
        }
        if(gradoTotal0==gradoTotal1){
            for ( int i = 0 ; i < N ; i++ ){
                if ( t0.getMonomio()[i] != t1.getMonomio()[i] ){
                    return t1.getMonomio()[i] - t0.getMonomio()[i];
                }
            }
            return 0;
        }else{
            return gradoTotal1-gradoTotal0;
        }

    }
}