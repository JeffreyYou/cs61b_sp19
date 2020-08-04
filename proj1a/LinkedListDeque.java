public class LinkedListDeque<T> {

    private IntNode Sentienl;
    T random;
    int size;

    /* the double ended queue*/
    public class IntNode{
        public IntNode prev;
        public T body;
        public IntNode next;
        IntNode(IntNode h, T b, IntNode t){
            prev = h;
            body = b;
            next = t;
        }
    }
    /* Creats an empty linked list deque*/
    public LinkedListDeque(){

        Sentienl = new IntNode(null, random , null);
        Sentienl.prev = Sentienl;
        Sentienl.next = Sentienl;
        size = 0;
    }
    /* Creats a linked list deque with the first element k*/
    public LinkedListDeque(T k){
        Sentienl = new IntNode(Sentienl, random , Sentienl);
        Sentienl.next = new IntNode(Sentienl, k ,Sentienl);
        Sentienl.prev = Sentienl.next;
        size = 1;
    }
    public void addFirst(T item){
        Sentienl.next = new IntNode(Sentienl, item , Sentienl.next);
        Sentienl.next.next.prev = Sentienl.next;
        if (Sentienl.prev == Sentienl){
            Sentienl.prev = Sentienl.next;
        }
        size += 1;
    }
    public void addLast(T item){

        Sentienl.prev = new IntNode(Sentienl.prev, item, Sentienl);
        Sentienl.prev.prev.next = Sentienl.prev;
        if(Sentienl.next == Sentienl){
            Sentienl.next = Sentienl.prev;
        }
        size += 1;
    }
    public boolean isEmpty(){
        if (Sentienl.prev == Sentienl){
            // same function
        //if(size == 0){
            return true;
        }
        else return false;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        int i;
        if (isEmpty() == true){
            System.out.println("");
        }else{
            IntNode p = Sentienl;
            System.out.print(p.next.body);
            p = p.next;
            while (p.next != Sentienl){
                System.out.print(" " + p.next.body);
                p = p.next;
            }
            System.out.println("");
        }
    }
    public T removeFirst(){
        if(isEmpty() == true){
            return null;
        }else{
            size -= 1;
            T a = Sentienl.next.body;
            Sentienl.next = Sentienl.next.next;
            Sentienl.next.prev = Sentienl;
            return a;
        }
    }
    public T removeLast(){
        if(isEmpty() == true){
            return null;
        }else{
            size -= 1;
            T a = Sentienl.prev.body;
            Sentienl.prev = Sentienl.prev.prev;
            Sentienl.prev.next = Sentienl;
            return a;
        }
    }
    public T getIndex(int index){
        if(index >= size){
            return null;
        }else{
            int i;
            IntNode p = Sentienl;
            for(i=0; i<=index; i++){
                p = p.next;
            }return p.body;
        }
    }
    //help method of getRecursive
    private T getRecursive(int index, IntNode current){
        if (index == 0){
            return current.body;
        }
        else {
            current = current.next;
            index -= 1;
            return getRecursive(index, current);
        }
    }
    public T getRecursive(int index){
        if(index >= size){
            return null;
        }else{
            return getRecursive(index, Sentienl.next);
        }
    }

    public LinkedListDeque(LinkedListDeque other){
        size = 0;
        Sentienl = new IntNode(null, random , null);
        Sentienl.prev = Sentienl;
        Sentienl.next = Sentienl;
        if (other.isEmpty() == true){
            return;
        }else{
            IntNode ptr = other.Sentienl;
            for(int i=0; i<other.size; i++){
                addLast(ptr.next.body);
                ptr = ptr.next;
            }
        }
    }

}