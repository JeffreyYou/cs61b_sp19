package es.datastructur.synthesizer;

//Note: This file will not compile until you complete task 1 (BoundedQueue).
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        buffer  = new ArrayRingBuffer<Double>((int)Math.round(SR/frequency));
        while (buffer.fillCount() != buffer.capacity()){
            buffer.enqueue((double)0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in buffer, and replace with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each
        //       other.

        //creat a content with different numbers
        //HashSet is the better implementation
        int n = buffer.capacity();
        int count = 0;
        double[] content = new double[n];
        while (count < n){
            boolean flag = true;
            double temp = Math.random() - 0.5;
            for (int j =0; j<n; j++ ){
                if (content[j] == temp){
                    flag = false;
                    break;
                }
            }
            if (flag){
                content[count] = temp;
                count ++;
            }
        }


        //dequeue everything in the buffer
        //replace with random numbers
        for (int j = 0; j< buffer.capacity(); j++){
            buffer.dequeue();
            buffer.enqueue(content[j]);

        }



    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double bufferFirst;
        double bufferSecond;
        double bufferOutput;

        bufferFirst = buffer.peek();

        //Dequeue the front sample
        buffer.dequeue();

        bufferSecond = buffer.peek();
        bufferOutput = (bufferFirst+bufferSecond)*0.5*DECAY;

        //enqueue a new sample
        buffer.enqueue(bufferOutput);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }
}
    // TODO: Remove all comments that say TODO when you're done.
