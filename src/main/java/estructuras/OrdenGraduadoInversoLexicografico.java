package estructuras;

import java.util.Comparator;

/**
 * Created by Paco on 14/06/2016.
 */
public class OrdenGraduadoInversoLexicografico implements Comparator<Termino> {
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
                if ( t0.getMonomio()[N-i] != t1.getMonomio()[N-i] ){
                    return t1.getMonomio()[N-i] - t0.getMonomio()[N-i];
                }
            }
            return 0;
        }else{
            return gradoTotal1-gradoTotal0;
        }

    }
}
