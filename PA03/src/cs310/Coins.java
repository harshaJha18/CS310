package cs310;
import java.util.*;
import java.io.*;

/**
 * Class to demonstrate Coin change DP algorithm
 */
public class Coins {
    // Can use a simple array if you want
    private ArrayList<Integer> denominations;
    
    public Coins(){
        denominations = new ArrayList<Integer>();
    }
    
    public void addDenomination(int coin) {
        int lastCoin;
        int sz = denominations.size();
        if(sz == 0)
            lastCoin=0;
        else {
            lastCoin = denominations.get(sz-1);
        }
        // Make sure denominations are sorted, optional
        if(coin < lastCoin) {
            System.out.println("Denominations not sorted!");
            System.exit(1);
        }
        denominations.add(coin);
    }
    
    public void makeChange(int sum)
    {
        // What's the minimum number of coins for each number from 1 to sum.
        // We start from 0 so we need one more index
        int [] coinsUsed = new int[sum+1];
        // What was the last coin used, so we can retrieve the solution
        int [] lastCoin = new int[sum+1];
        coinsUsed[ 0 ] = 0; lastCoin[ 0 ] = 1;

        for( int cents = 1; cents <= sum; cents++ ){
            int minCoins = cents;
            int newCoin  = 1;

            int differentCoins = denominations.size();
            for( int j = 0; j < differentCoins; j++ ) {
                if( denominations.get(j) > cents )   // Cannot use coin j
                    continue;
                if( coinsUsed[ cents - denominations.get(j) ] + 1 < minCoins ) {
                    minCoins = coinsUsed[ cents - denominations.get(j) ] + 1;
                    newCoin  = denominations.get(j);
                }
            }

            coinsUsed[ cents ] = minCoins;
            lastCoin[ cents ]  = newCoin;
        }
        printCoins(sum, coinsUsed[sum], lastCoin);
    }
    
    public void printCoins(int sum, int noCoins, int[] lastCoin)
    {
        // How many coins from each denomination. Tree Map to keep sorted
	// The reverse is because we go from large to small (optional)
        TreeMap<Integer,Integer> howMany = new TreeMap<Integer,Integer>(Collections.reverseOrder());
        for(int i=0;i<denominations.size();i++) // Keeps track on how many coins each
            howMany.put(denominations.get(i),0);
        System.out.println("For " + sum + " We use " + noCoins + " coins");
        // Backtrack
        int s = sum;
        while(s > 0) {
            int coin = lastCoin[s];
            int freq = howMany.get(coin);
            howMany.put(coin,freq+1);
            s -= coin;
        }
        // Print only non-zeros
       for (Map.Entry<Integer,Integer> entry : howMany.entrySet()) {
            if(entry.getValue() > 0)
                System.out.println(entry.getKey() + " X " + entry.getValue() );
        }
    }
    
    public static void main(String[] args) {
        Coins c = new Coins();
        // Read coins from file
        Scanner in = new Scanner(System.in);
        // First read sum
        int sum = in.nextInt();
        while(in.hasNextInt()){
            int coin = in.nextInt();
            c.addDenomination(coin);
        }
        c.makeChange(sum);
        //System.out.println(sum + " " + c.denominations);
    }
        
}
