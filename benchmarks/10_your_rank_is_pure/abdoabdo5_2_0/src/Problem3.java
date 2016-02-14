/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Queue;

/**
 *
 * @author abdelrahman
 */
public class Problem3 {
    static int[][] best;
    public static int solve(int number, int rank){
        if(rank > number-1) return 0;
        if(rank == 1)   return 1;
        if(best[number][rank]!=-1)  return best[number][rank];
        int sol = 0;
        int tmp = 0;
        for (int i = rank-1; tmp<number-rank && i >= 1; i--) {
            sol += solve(rank, i)*c(number-rank-1,rank-i-1);
            sol%=100003;
            tmp++;
        }
        return sol;
    }
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
          try {
            // TODO code application logic here
            FileOutputStream out1 = new FileOutputStream(new File("out1.txt"));
            OutputStreamWriter out = new OutputStreamWriter(out1, "UTF-8");
            Scanner scanner = new Scanner(new File("C-small-attempt0.in"));
            int noOfTestCases = scanner.nextInt();
            for (int testCases = 0; testCases < noOfTestCases; testCases++) {
                int n= scanner.nextInt();
                int sol = 0;
                best = new int[n+1][n+1];
                for (int i = 0; i < best.length; i++) {
                    Arrays.fill(best[i], -1);
                }
                for (int i = 1; i <= n; i++) {
                    sol += solve(n,i);
                    sol%=100003;
                }
                out.write("Case #"+(testCases+1)+": "+sol+"\n");
            }
            out.close();
        } catch (Exception ex) {

        }
    }

    private static int c(int n, int k) {
        BigInteger no = new BigInteger("1"),d1 = new BigInteger("1"),d2=new BigInteger(""+1);
        for (int i = 1; i <= n; i++) {
            no = no.multiply(new BigInteger(""+i));
        }
        for (int i = 1; i <= k; i++) {
            d1 = d1.multiply(new BigInteger(""+i));
        }
        for (int i = 1; i <= n-k; i++) {
            d2 = d2.multiply(new BigInteger(""+i));
        }
        BigInteger res = new BigInteger("0");
        res = no.divide(d1);
        res = res.divide(d2);
        res.mod(new BigInteger("100003"));
        return res.intValue();
    }
}
