package cs310;
import java.util.*;
import java.io.*;

/**
 * Class for a Path in the Dynamic Programming sequence alignment algorithm
 */
public class Path {
    public int row, col;          // the row and column this node represents
    public int cost;              // the matching cost from this point on
    public Path next;             // the next node in the optimal path

    public Path()
    {
        next = null;
    }
    
    public String toString() {
        return "(" + row + " , " + col + " , " + cost + ")";
    }
    
    // Print the path
    public void print(String a, String b) {
        System.out.println("Edit Distance " + cost);
        Path p = this;
        if (p == null) return;
        while(p.next != null) {
            int c = p.cost-p.next.cost; // How much did we "pay" for this entry
            if(p.next.row == p.row + 1 && p.next.col == p.col + 1)
                System.out.println(a.charAt(p.row) + " " + b.charAt(p.col) + " " + c);
            if(p.next.col == p.col) // gap in col
                System.out.println(a.charAt(p.row) + " - " + c);
            if(p.next.row == p.row) // gap in row
                System.out.println("- " + b.charAt(p.col) + " " + c);
            p=p.next;
        }
    }
}
