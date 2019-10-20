package cs310;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SymbolGraph;
import edu.princeton.cs.algs4.BreadthFirstPaths;

/**
 *  The {@code DegreesOfSeparation} class provides a client for finding
 *  the degree of separation between one distinguished individual and
 *  every other individual in a social network.
 *  As an example, if the social network consists of actors in which
 *  two actors are connected by a link if they appeared in the same movie,
 *  and Kevin Bacon is the distinguished individual, then the client
 *  computes the Kevin Bacon number of every actor in the network.
 *  <p>
 *  The running time is proportional to the number of individuals and
 *  connections in the network. If the connections are given implicitly,
 *  as in the movie network example (where every two actors are connected
 *  if they appear in the same movie), the efficiency of the algorithm
 *  is improved by allowing both movie and actor vertices and connecting
 *  each movie to all of the actors that appear in that movie.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class DegreesOfSeparationBFS {
    
    // this class cannot be instantiated
    private DegreesOfSeparationBFS() { }
    
    // Print out a diagram of the bacon numbers (code from BaconHistogram)
    public static void printBaconDiagram(SymbolGraph sg, BreadthFirstPaths bfs)
    {
        Graph G = sg.graph();
        // compute histogram of Kevin Bacon numbers - 100 for infinity
        int MAX_BACON = 100;
        int[] hist = new int[MAX_BACON + 1];
        for (int v = 0; v < G.V(); v++) {
            int bacon = Math.min(MAX_BACON, bfs.distTo(v));
            hist[bacon]++;
            
            // to print actors and movies with large bacon numbers
            if (bacon/2 >= 7 && bacon < MAX_BACON)
                StdOut.printf("%d %s\n", bacon/2, sg.nameOf(v));
        }
        
        // print out histogram - even indices are actors
        for (int i = 0; i < MAX_BACON; i += 2) {
            if (hist[i] == 0) break;
            StdOut.printf("%3d %8d\n", i/2, hist[i]);
        }
        StdOut.printf("Inf %8d\n", hist[MAX_BACON]);
        // NH: Print newline to separate from next section (optional...)
        StdOut.printf("\n");
    }
    
    // Read actor's name from standard input, print bacon relationship
    public static void printDegreesOfSeparation(SymbolGraph sg, BreadthFirstPaths bfs)
    {
        // NH: Notice that unlike original degrees of separation, we want to calculate
        // the bacon number in addition to the path.
        while (!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (sg.contains(sink)) {
                int t = sg.indexOf(sink);
                
                // NH: First difference from original: Calculate path length
                if (bfs.hasPathTo(t)) {
                    int d = bfs.distTo(t);
                    // Remember the distance is half of d! Symbol graph has both movies and actors
                    // You need to know the structure of a symbol graph to know that!
                    StdOut.println(sink + " has a bacon number of " + d/2 + ".");
                    // Now print the path. Youy have to print it in reverse order to what's in the
                    // original code. Think why!
                    Stack<Integer> path = new Stack<Integer>();
                    for (int v : bfs.pathTo(t)) {
                        path.push(v);
                    }
                    // Now print. Every other vertex is an actor
                    boolean actor = true;
                    while(path.size() > 1){
                        int v_ac = path.pop();
                        int v_m = path.pop();
                        int v_next = path.peek();
                        if(actor) {
                            StdOut.printf("%s was in the movie %s with %s\n", sg.nameOf(v_ac), sg.nameOf(v_m), sg.nameOf(v_next));
                        }
                    }
                }
              else {
                 StdOut.println("Not connected");
              }
           }
           else {
             StdOut.println("   Not in database.");
         }
        }
    }
    /*
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String filename  = args[0];
        String delimiter = args[1];
        String source    = args[2];
        
        // First prepare graph based on input parameters
        SymbolGraph sg = new SymbolGraph(filename, delimiter);
        Graph G = sg.graph();
        if (!sg.contains(source)) {
            StdOut.println(source + " not in database.");
            return;
        }
        
        int s = sg.indexOf(source);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
        
        // Print the Bacon diagram
        printBaconDiagram(sg, bfs);
        
        // Get degrees of separation
        printDegreesOfSeparation(sg, bfs);
    }
}

/******************************************************************************
 *  Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/



