public class ArrayDeque<T>{
    int size;
    private T[] array;
    double ratio;
    int nextFisrt;
    int nextLast;



    public ArrayDeque(){
        size = 0;
        array = (T[]) new Object[8];
        ratio = size/array.length;
        nextLast = 0;
        nextFisrt = 0;

    }
    /* calculate the element's positon of the kth item*/
    public int addOne(int k){
        return (k + 1)%array.length;
    }
    /*calcaulte the element's position before the kth time*/
    public int minusOne(int k){
        if(k == 0){
            return array.length-1;
        }else return k-1;
    }
    /*resize the array*/
    public void reSize(int capacity){
        T[] aTemp = (T[]) new Object[capacity];

        if(size == 0){
            array = aTemp;
            nextFisrt = 0;
            nextLast = 0;
        }
        //if there is no item added first, addOne(nextFirst)==0
        if(addOne(nextFisrt)<=minusOne(nextLast)){
            System.arraycopy(array,addOne(nextFisrt),aTemp,0,minusOne(nextLast)-addOne(nextFisrt)+1);
            nextFisrt = aTemp.length-1;
            /*nextLast dose not change*/

        }
        else{
            System.arraycopy(array,addOne(nextFisrt),aTemp,0,array.length-addOne(nextFisrt));
            System.arraycopy(array,0,aTemp,array.length-addOne(nextFisrt),minusOne(nextLast) + 1 );
            nextFisrt = aTemp.length-1;
            nextLast = array.length- addOne(nextFisrt) + nextLast;/* size */
            nextLast = size;
            array = aTemp;
        }


    }
    public void addFirst(T items){
        if (size == array.length){
            reSize(size*2);
        }
        if (size == 0){
            array[0] = items;
            nextFisrt = minusOne(nextFisrt);
            nextLast = addOne(nextLast);
            size = 1;
        }else {
            array[nextFisrt] = items;
            size +=1;
            nextFisrt = minusOne(nextFisrt);
        }

    }
    public void addLast(T items){
        if (size == array.length){
            reSize(size*2);
        }
        if (size == 0){
            array[0] = items;
            nextLast = addOne(nextLast);
            nextFisrt = minusOne(nextFisrt);
            size = 1;
        }else {
            array[nextLast] = items;
            nextLast = addOne(nextLast);
            size += 1;
        }
    }
    public boolean isEmpty(){
        if (size == 0) {
            return true;
        }else return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
            int i = 0;
            int j = nextFisrt;
            for(i = 0; i<size;i++){
                System.out.print(array[addOne(j)]);
                j = addOne(j);
            }
            System.out.println("");
    }

    public T removeFirst(){
        if(ratio<=0.25 && array.length>=16){
            reSize(array.length/2);
        }
        nextFisrt = addOne(nextFisrt);
        T waitReturn = array[nextFisrt];
        if(size != 0){
            size -= 1;
            return waitReturn;
        }else {

            return null;
        }

    }
    public T removeLast(){
        if (ratio<=0.25 && array.length>=16){
            reSize((array.length/2));
        }
        nextLast = minusOne(nextLast);
        T waitReturn = array[nextLast];
        if(size != 0){
            size -= 1;
            return waitReturn;
        }else {

            return null;
        }
    }
    public T getIndex(int index){
        if(size == 0 || index>= size){
            return null;
        }
        return array[(addOne(nextFisrt)+index)%array.length];
    }
    public ArrayDeque(ArrayDeque other){
         array = (T []) new Object[other.array.length];
         ratio = other.ratio;
         nextLast = other.nextLast;
         nextFisrt = other.nextFisrt;
         size = other.size;
         int j = nextFisrt;
         System.arraycopy(other.array,0,array,0,size);

    }
}