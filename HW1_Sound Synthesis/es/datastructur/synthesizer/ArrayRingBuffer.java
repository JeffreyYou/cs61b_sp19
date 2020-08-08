package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */

    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        //Java's Erasure
        rb = (T[]) new Object[capacity];

    }
    // increment first,last
    public int rightOne(int x){
        return (x+1)%capacity();
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (fillCount == capacity()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        last = rightOne(last);
        fillCount += 1;

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (fillCount == 0){
            throw new RuntimeException("Ring buffer underflow");
        }
        T temp = rb[first];
        first = rightOne(first);
        fillCount -= 1;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (fillCount == 0){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity(){
        return rb.length;
    }

    @Override
    public int fillCount(){
        return fillCount;
    }


    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public Iterator<T> iterator(){
        return new arrayRIngBufferIterator();
    }


    private class arrayRIngBufferIterator implements Iterator<T>{
        private int index ;
        private int currpos;
        arrayRIngBufferIterator(){
            index = 0;
            currpos = first;
        }
        public boolean hasNext(){
            return index < fillCount;
        }
        public T next(){
            T temp = rb[currpos];
            index ++;
            currpos = rightOne(currpos);
            return temp;
        }
    }

    @Override
    public boolean equals(Object other){

        if (other == null || other.getClass() != this.getClass()){
            return false;
        }
        if (other == this){
            return true;
        }

        ArrayRingBuffer<T> test = (ArrayRingBuffer<T>) other;
        if ( test.capacity() != this.capacity() && test.fillCount() != this.fillCount()){
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> otherIterator = test.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (thisIterator.next() != otherIterator.next()) {
                return false;
            }
        }
        return true;


    }

}
