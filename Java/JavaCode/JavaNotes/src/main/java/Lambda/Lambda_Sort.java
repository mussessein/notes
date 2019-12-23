package Lambda;

import java.util.Arrays;
import java.util.Comparator;

/*
使用Lambda进行排序
 */
public class Lambda_Sort {
    public static void main(String[] args) {

        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};


       Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                return o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(players));

        // Lambda排序
        Arrays.sort(players,(String s1,String s2)->(s1.compareTo(s2)));
        System.out.println(Arrays.toString(players));

        /*
        通过名称长度排序
         */

        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });


        Arrays.sort(players,(String s1,String s2)->(s1.length()-s2.length()));


    }



}
