package chap16_Array;

import java.util.List;

/**
 * Created by ping.wu on 2017/4/14.
 */
public class ArrayOfGenerics {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        List<String>[] ls;
        List[] la = new List[10];
        ls = (List<String>[])la;
    }
}
