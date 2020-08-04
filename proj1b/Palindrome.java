public class Palindrome {
    public Deque<Character> wordToDeque(String word) {

        Deque<Character> PaDeque = new LinkedListDeque();
        int l = word.length();
        int i;
        for (i = 0; i < l; i++) {
            //Character.toString(word.charAt(i));
            PaDeque.addLast(word.charAt(i));
        }
        return PaDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> isPaDeque = wordToDeque(word);
        int l = isPaDeque.size();
        int i, j, k;
        int testTime = l/2 + l % 2;
        i = 0;
        j = l - 1;
        if (l == 0 || l == 1) {
            return true;
        }
        for (k = 0; k < testTime; k++) {
            if (isPaDeque.get(i) == isPaDeque.get(j)) {
                i++;
                j--;
            } else return false;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> isPaDequeOverload = wordToDeque(word);

        int l = isPaDequeOverload.size();
        int i, j, k;
        int testTime = l/2 ;
        i = 0;
        j = l - 1;
        if (l == 0 || l == 1) {
            return true;
        }
        for (k = 0; k < testTime; k++) {
            if (cc.equalChars(isPaDequeOverload.get(i) , isPaDequeOverload.get(j))) {
                i++;
                j--;
            } else return false;
        }

        return true;
    }


}