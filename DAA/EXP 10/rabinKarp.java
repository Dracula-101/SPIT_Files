import java.util.ArrayList;
import java.util.Scanner;

public class rabinKarp {
    static ArrayList<Integer> locations = new ArrayList<Integer>();

    // d is the number of characters in the input alphabet
    static void search(String pat, String txt, int q) {
        int alphabets = 256;
        int patternLen = pat.length();
        int textLen = txt.length();
        int i, j;
        int patHash = 0; // hash value for pattern
        int textHash = 0; // hash value for txt
        int h = 1;
        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < patternLen - 1; i++)
            h = (h * alphabets) % q;
        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < patternLen; i++) {
            patHash = (alphabets * patHash + pat.charAt(i)) % q;
            textHash = (alphabets * textHash + txt.charAt(i)) % q;
        }
        // Slide the pattern over text one by one
        for (i = 0; i <= textLen - patternLen; i++) {
            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            for (j = 0; j < patternLen; j++) {
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            // if p == t and pat[0...M-1] = txt[i, i+1,...i+M-1]
            if (j == patternLen) {
                locations.add(i);
                System.out.println("Pattern found at index " + i);
            }
        }
        // Calculate hash value for next window of text: Remove
        // leading digit, add trailing digit
        if (i < textLen - patternLen) {
            textHash = (alphabets * (textHash - txt.charAt(i) * h) + txt.charAt(i + patternLen)) % q;
            // We might get negative value of t, converting to positive
            if (textHash < 0)
                textHash = (textHash + q);
        }
    }

    /* Driver program to test above function */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter the text: ");
        String txt = sc.next();
        System.out.print("\nEnter the pattern: ");
        String pat = sc.next();
        int q = 101; // A prime number
        search(pat, txt, q);
        System.out.print("\nPattern found at following locations: \n");
        for (int i = 0; i < txt.length(); i++) {
            System.out.print(txt.charAt(i));
        }
        System.out.print("\n");
        for (int i = 0; i < txt.length() && locations.size() != 0; i++) {
            if (i == locations.get(0)) {
                System.out.print("^");
                locations.remove(0);
            } else
                System.out.print(" ");
        }
        sc.close();
    }
}
