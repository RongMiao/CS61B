


public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque ad = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            ad.addLast(word.charAt(i));
        }
        return ad;
    }


    private boolean is_palindrome(Deque<Character> deque) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }

        if (deque.removeFirst() != deque.removeLast()) {
            return false;
        }
        return is_palindrome(deque);
    }

    private boolean is_palindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        }

        if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return false;
        }
        return is_palindrome(deque, cc);
    }

    public boolean isPalindrome(String word) {
        if (word == null || word.length() == 1) {
            return true;
        }
        Deque deque = wordToDeque(word);
        /*
        int i = 0;
        for (i = 0; i < deque.size() / 2; i++) {
            if (deque.get(i) != deque.get(deque.size() - i - 1)) {
                break;
            }
        }
        if (i != deque.size() / 2) {
            return false;
        } else {
            return true;
        }*/
        return is_palindrome(deque);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null || word.length() == 1) {
            return true;
        }
        Deque deque = wordToDeque(word);
        return is_palindrome(deque, cc);
    }
}