package cs310;
import java.util.*;
import java.io.*;

/**
 * Class to demonstrate Coin change DP algorithm
 */
public class EditDistance {
    // read 2 strings from standard input.
    // compute and print the edit distance between them and output an optimal
    // alignment and associated penalties.
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        Match m = new Match();
        Path p = m.match(a,b);
        p.print(a,b);
    }
    
}
