package Game;

import java.util.ArrayList;

/*
 * Interface de lectura y escritura de archivo
 */

public interface IOFiles_Interface <T> {
    //escribe archivos 
    public boolean WriteFile (T t);

    //lee archivo
    public ArrayList<T> ReadFile();
}
