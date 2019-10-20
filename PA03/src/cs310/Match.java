package cs310;
import java.util.*;
import java.io.*;

/**
 * Class to demonstrate Coin change DP algorithm
 */

public class Match {
    
    public Match(){}
    
// return the optimal match between the strings a and b
// return null if either string is null or if either string is length 0
    static Path match(String a, String b){
        if (a == null || b == null || a.length() == 0 || b.length() == 0) return null;
        int gapPenalty = 2, len = a.length(), wid = b.length();
        // Need one more row and column for boundary conditions
        Path[][] opt = new Path[len+1][wid+1];
        // Fill gap penalty
        int gap=0;
        for(int i=len;i>=0;i--) {
            opt[i][wid]=new Path();
            opt[i][wid].cost = gap;
            opt[i][wid].row = i;
            opt[i][wid].col = wid;
            gap += gapPenalty;
        }
        gap=gapPenalty;
        // start from wid-1 b/c wid has already been filled above
        for(int i=wid-1;i>=0;i--) {
            opt[len][i]=new Path();
            opt[len][i].cost = gap;
            opt[len][i].row = len;
            opt[len][i].col = i;
            gap += gapPenalty;
        }
        // Now start the matching
        for(int i=len-1;i>=0;i--)
            for(int j=wid-1;j>=0;j--){
                // Match, mismatch or gap
                opt[i][j]=new Path();
                opt[i][j].row = i;
                opt[i][j].col = j;
                int match = (a.charAt(i) == b.charAt(j)) ? 0 : 1;
                int val1 = opt[i+1][j+1].cost + match; // mis/match
                int val2 = opt[i+1][j].cost + gapPenalty; // Gap in i
                int val3 = opt[i][j+1].cost + gapPenalty; // Gap in j
                // find 3-way minimum
                int min=val1;
                if(val2 < min) min = val2;
                if(val3 < min) min = val3;
                if(min == val1) {
                    opt[i][j].cost = val1;
                    opt[i][j].next = opt[i+1][j+1];
                    }
                else
                if(min == val2){
                    opt[i][j].cost = val2;
                    opt[i][j].next = opt[i+1][j];
                }
                else {
                    opt[i][j].cost = val3;
                    opt[i][j].next = opt[i][j+1];
                }
            }
        return opt[0][0];
    }
    
     // method to test Match (see Checklist)
public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        Match m = new Match();
        Path p = m.match(a,b);
        p.print(a,b);
    }
}

