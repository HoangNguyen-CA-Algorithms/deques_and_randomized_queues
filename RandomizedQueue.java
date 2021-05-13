/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int end;
    private int size;

    private Item[] reformatQ(int size){

        Item[] array = (Item[]) new Object[size];

        int index = 0;
        for (int i = 0; i <= end; i++){
            if (q[i] != null){ // add every non-null object to new array
                array[index] = q[i];
                index++;
            }
        }

        return array;
    }


    private void resize(){

        if (size < q.length/4){ // halve array capacity
            this.q = reformatQ(q.length / 2);
            this.end = this.size;
        }

        if (end == q.length-1) { //double array capacity
            this.q = reformatQ(q.length * 2);
            this.end = this.size;

        }


    }



    // construct an empty randomized queue
    public RandomizedQueue(){
        q = (Item[]) new Object[1];
        size = 0;
        end = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null) throw new IllegalArgumentException();
        resize();
        q[end++] = item;
        size++;
    }

    //remove and return a random item
    public Item dequeue(){
        if (size == 0) throw new NoSuchElementException();

        int random = StdRandom.uniform(end);
        while (q[random] == null){
            random = StdRandom.uniform(end);
        }

        Item item = q[random];
        q[random] = null;

        size--;
        resize();

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (size == 0) throw new NoSuchElementException();

        int random = StdRandom.uniform(end);
        while (q[random] == null){
            random = StdRandom.uniform(end);
        }

        return q[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ListIterator();

    }

    private class ListIterator implements Iterator<Item> {
        int index;
        Item[] randomQ;

        public ListIterator(){
            super();
            Item[] array = reformatQ(size);

            index = 0;
            StdRandom.shuffle(array);
            randomQ = array;
        }

        public boolean hasNext() {
            return index < randomQ.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            Item item = randomQ[index];
            index++;
            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> q = new RandomizedQueue<>();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        System.out.println(q.sample() + " " + q.sample() + " " + q.sample()+ " " + q.sample()  );



        System.out.println(q.dequeue() + " " + q.dequeue()  );



        System.out.println(q.dequeue() + " " + q.dequeue());

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        for (int i: q){
            System.out.print(i + " ");
        }

    }

}