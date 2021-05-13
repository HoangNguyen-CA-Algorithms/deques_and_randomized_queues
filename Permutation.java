/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> q = new RandomizedQueue<>();
        while (!StdIn.isEmpty()){
            q.enqueue(StdIn.readString());
        }

        Iterator<String> it = q.iterator();
        int i = 0;
        while ( i < k){
            if (!it.hasNext()) throw new RuntimeException();
            System.out.println(it.next());
            i++;
        }
    }
}
