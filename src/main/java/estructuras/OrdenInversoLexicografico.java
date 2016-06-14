package estructuras;

import java.util.Comparator;

/**
 * Created by Paco on 14/06/2016.
 */
public class OrdenInversoLexicografico implements Comparator<Termino> {
    public int compare(Termino t0, Termino t1) {
        int N = t0.getMonomio().length;
        for ( int i = 0 ; i < N ; i++ ){
            if ( t0.getMonomio()[N-i] != t1.getMonomio()[N-i] ){
                return t1.getMonomio()[N-i] - t0.getMonomio()[N-i];
            }
        }
        return 0;
    }
}
