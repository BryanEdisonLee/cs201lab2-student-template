import java.util.ArrayList;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        //counter
        int i = 0;
        Node <E> counter = head;
        while (head != null && counter != null){
            counter = counter.getNext();
            i++;
        }

        //store nodes and positions
        ArrayList<Node<E>> nodes = new ArrayList<>(i);
        int[] positions = new int[i];
        Node<E> current = head;
        int pos = 0;
        while (current != null) {
            nodes.add(current);
            positions[pos] = pos;
            current = current.getNext();
            pos++;
        }
        
        //moving the int array to an arraylist so i can use sort
        ArrayList<Integer> positionsList = new ArrayList<>();
        for (int j = 0; j < positions.length; j++) {
            positionsList.add(positions[j]);
        }
        //sorting the positions list based on the nodes
        positionsList.sort((a, b) ->nodes.get(a).getElement().compareTo(nodes.get(b).getElement()));

        //swapping the nodes based on the sorted positions
        for (int left = 0; left < positionsList.size() / 2; left++) {
        int first = positionsList.get(left);
        int last = positionsList.get(positionsList.size() - 1 - left);
        Node<E> temp = nodes.get(first);
        nodes.set(first, nodes.get(last));
        nodes.set(last, temp);
        }
        //build the linked list, handle empty nodes
        if (nodes.isEmpty()) return;
        for (int j = 0; j < nodes.size() - 1; j++) {
            nodes.get(j).setNext(nodes.get(j + 1));
        }
        nodes.get(nodes.size() - 1).setNext(null);
        head = nodes.get(0);       
        tail = nodes.get(nodes.size() - 1);
    }
   
}

