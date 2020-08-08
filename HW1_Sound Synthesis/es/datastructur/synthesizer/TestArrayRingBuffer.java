package es.datastructur.synthesizer;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //test capacity()
        ArrayRingBuffer<String> arb = new ArrayRingBuffer<>(10);
        int expect = 10;
        int actual = arb.capacity();
        Assert.assertEquals(expect,actual);

        //test fillCount()
        arb.enqueue("a");
        arb.enqueue("b");
        arb.enqueue("c");
        arb.enqueue("d");
        expect = 4;
        actual = arb.fillCount();
        Assert.assertEquals(expect,actual);

        //test peek()
        String expectContent = "a";
        String actualContent = arb.peek();
        Assert.assertEquals(expectContent,actualContent);

        //test dequeue()
        arb.dequeue();
        arb.dequeue();
        expectContent = "c";
        actualContent = arb.peek();
        Assert.assertEquals(expectContent,actualContent);
    }
}
