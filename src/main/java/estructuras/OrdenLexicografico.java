package estructuras;

import java.util.Comparator;

public class OrdenLexicografico implements Comparator<Termino> {
    public int compare(Termino t0, Termino t1) {
        int N = t0.getMonomio().length;
        for ( int i = 0 ; i < N ; i++ ){
            if ( t0.getMonomio()[i] != t1.getMonomio()[i] ){
                return t1.getMonomio()[i] - t0.getMonomio()[i];
            }
        }
        return 0;
    }
}
