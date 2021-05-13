/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node{ // use doubly linked list
        Item item;
        Node next;
        Node prev;

        public Node(Item item){
            this.item = item;
        }
    }

    // construct an empty deque
    public Deque(){
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return first == null;
    }

    // return the number of items on the deque
    public int size(){
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException();

        Node node = new Node(item);
        if (size == 0){
            this.first = node;
            this.last = node;
            this.size++;
        }else{
            this.first.prev = node.next;
            node.next = this.first;

            this.first = node;
            this.size++;
        }


    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException();

        Node node = new Node(item);
        if (size == 0){
            this.first = node;
            this.last = node;
            this.size++;
        }else{
            this.last.next = node;
            node.prev = this.last;

            this.last = node;
            this.size++;
        }



    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(size == 0) throw new NoSuchElementException();

        if (size == 1){
            Item item = this.first.item;
            this.first = null;
            this.last = null;

            this.size--;
            return item;
        }else{
            Item item = this.first.item;
            this.first = this.first.next;
            this.first.prev = null;

            this.size--;
            return item;
        }

    }

    // remove and return the item from the back
    public Item removeLast(){
        if(size == 0) throw new NoSuchElementException();

        if (size == 1){
            Node last = this.last;
            this.first = null;
            this.last = null;

            this.size--;
            return last.item;
        }else{
            Item item = this.last.item;
            this.last = this.last.prev;
            this.last.next = null;

            this.size--;
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){

        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }




    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addLast(5);

        System.out.println(deque.removeFirst());
        deque.removeFirst();
        deque.removeFirst();

        deque.addLast(3);
        deque.addLast(2);

        deque.removeLast();
        deque.removeLast();
        deque.removeLast();

    }
}