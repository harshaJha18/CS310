package cs310;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.LinearProbingHashST;
import edu.princeton.cs.algs4.SequentialSearchST;

public class TestPerf {
    private ST<String,Integer> st;
    private SequentialSearchST<String,Integer> seqSt;
    private SeparateChainingHashST<String,Integer> sepSt;
    private LinearProbingHashST<String,Integer> linSt;
    
    public TestPerf() {
        sepSt = new SeparateChainingHashST<String, Integer>();
        st = new ST<String, Integer>();
        seqSt = new SequentialSearchST<String, Integer>();
        linSt = new LinearProbingHashST<String, Integer>();
    }
    
    public long testRunLinear(String[] keys) {
        long startTime = System.currentTimeMillis();
        for (String k : keys) {
            Integer v = linSt.get(k);
            if(v == null)
                linSt.put(k,1);
            else {
                linSt.put(k,v+1);
            }
        }
        long endTime = System.currentTimeMillis();
        long tm = endTime-startTime;
        return tm;
    }
    
    public long testRunSt(String[] keys) {
        long startTime = System.currentTimeMillis();
        for (String k : keys) {
            Integer v = st.get(k);
            if(v == null)
                st.put(k,1);
            else {
                st.put(k,v+1);
            }
        }
        long endTime = System.currentTimeMillis();
        long tm = endTime-startTime;
        return tm;
    }
    
    public long testRunSeparate(String[] keys) {
        long startTime = System.currentTimeMillis();
        for (String k : keys) {
            Integer v = sepSt.get(k);
            if(v == null)
                sepSt.put(k,1);
            else {
                sepSt.put(k,v+1);
            }
        }
        long endTime = System.currentTimeMillis();
        long tm = endTime-startTime;
        return tm;
    }
    
    
    public long testRunSequential(String[] keys) {
        long startTime = System.currentTimeMillis();
        for (String k : keys) {
            Integer v = seqSt.get(k);
            if(v == null)
                seqSt.put(k,1);
            else {
                seqSt.put(k,v+1);
            }
        }
        long endTime = System.currentTimeMillis();
        long tm = endTime-startTime;
        return tm;
    }
    
   // This is for questions 4-6, to count the words, unique words etc.
    public void printToFile(String[] keys) {
          Out fileo = new Out("uniqueWords.txt");
        for (String s : linSt.keys())
            fileo.println(s + " " + linSt.get(s));
        fileo.close();
    }

    public static void main(String[] args) {
        // Parse into words
        TestPerf pf = new TestPerf();
        In filen = new In(args[0]);
        String[] line = filen.readAllStrings();
        filen.close();
        long t;
        t = pf.testRunSt(line);
        StdOut.println("Symbol table Search Time " + t);
        t = pf.testRunSeparate(line);
         StdOut.println("Separate Chaining Search Time " + t);
        t = pf.testRunLinear(line);
         StdOut.println("Linear Probing Search Time " + t);
        t = pf.testRunSequential(line);
        StdOut.println("Sequential Search Time " + t);
        // Spill out the contents of one table
        // pf.printToFile(line);
    }
}

