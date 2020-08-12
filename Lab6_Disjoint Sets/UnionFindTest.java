import org.junit.Test;
import org.junit.Assert;

public class UnionFindTest {
    @Test
    public void testBasics(){
        UnionFind myUnion = new UnionFind(12);
        //test union() method
        myUnion.union(0 , 1);
        Assert.assertEquals(myUnion.find(0), 1);
        myUnion.union(2 , 3);
        myUnion.union(0 , 3);
        Assert.assertEquals(myUnion.find(0), 3);
        //test union by rank(number of the elements)
        myUnion.union(4 , 5);
        myUnion.union(0 , 4);
        Assert.assertEquals(myUnion.find(4), 3);

    }
}
