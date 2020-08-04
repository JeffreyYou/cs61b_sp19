import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);

    }
    @Test
    public void testisPalindrome(){
        palindrome.isPalindrome("cat");
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("abcd"));
        assertTrue(palindrome.isPalindrome("mooooom"));
        assertTrue(palindrome.isPalindrome("abcdcba"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("1"));
    }

    @Test
    public void testisPalindromeOverload(){
        CharacterComparator ccc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",ccc));
        assertTrue(palindrome.isPalindrome("acdb",ccc));
        assertTrue(palindrome.isPalindrome("achdb",ccc));
        assertFalse(palindrome.isPalindrome("abcc",ccc));
        assertFalse(palindrome.isPalindrome("fladke",ccc));
    }
    @Test
    public void testisPalindromeOverloadN(){
        CharacterComparator ccc = new OffByN(4);
        assertTrue(palindrome.isPalindrome("acge",ccc));
        assertFalse(palindrome.isPalindrome("abfd",ccc));

    }

}