package cs310;
import edu.princeton.cs.algs4.*;
import java.util.*;

/**
 * Class to demonstrate Coin change DP algorithm
 */
public class MoveToFront {
    ArrayList<Character> seq;
    public MoveToFront() {
        seq = new ArrayList<Character>();
        for(char i=0;i<255;i++)
            seq.add(i);
    }
    public static void decode() {
        MoveToFront mtf = new MoveToFront();
        for (int i = 0; !BinaryStdIn.isEmpty(); i++) {
            // Read an index
            char c = BinaryStdIn.readChar();
  //          System.out.println((int)c);
            char now = mtf.seq.get(c);
            StdOut.printf("%c",now);
            mtf.seq.remove(c);
            mtf.seq.add(0,now);
        }
    }
    // Read from stdin
    public static void encode() {
        MoveToFront mtf = new MoveToFront();
        for (int i = 0; !BinaryStdIn.isEmpty(); i++) {
            // Read a character and move to front
            char c = BinaryStdIn.readChar();
            // Search in sequence
            ListIterator l = mtf.seq.listIterator();
            while(l.hasNext()) {
                Character n = (Character)l.next();
                if(n==c) {
                    StdOut.printf("%c", l.previousIndex());
                    l.remove();
                    mtf.seq.add(0,c); // Add to beginning of list
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        MoveToFront mtf = new MoveToFront();
        if(args.length != 1) {
            System.out.println("Usage: MoveToFront <+,->");
            return;
        }
        String one = args[0];
        if(one.equals("-"))
            mtf.encode();
        else if(one.equals("+"))
            mtf.decode();
        else
            System.out.println("Usage: MoveToFront <+,->");
    }
        
}
