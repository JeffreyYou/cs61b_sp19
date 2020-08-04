import org.junit.Test;
import static org.junit.Assert.*;


public class TestOffByN {
    static Palindrome palindrome = new Palindrome();
    @Test
    public void testisPalindromeOverloadN(){
        CharacterComparator ccc = new OffByN(4);
        assertTrue(palindrome.isPalindrome("acge",ccc));
        assertFalse(palindrome.isPalindrome("abfd",ccc));

    }
}
