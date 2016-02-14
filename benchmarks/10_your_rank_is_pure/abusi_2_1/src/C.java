import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class C
{

  public static List<String> loadLines(File file) throws IOException {
    if (!file.exists()) {
      return null;
    }
    BufferedReader reader = null;
    reader = new BufferedReader(new FileReader(file));
    ArrayList<String> list = new ArrayList<String>();
    String s = null;
    while ((s = reader.readLine()) != null) {
      list.add(s);
    }
    if (reader != null)
      try {
        reader.close();
      } catch (IOException e) {
      }
    reader = null;
    return list;
  }
  static BigInteger map[][];
  
  public static void main(String[] args) throws Exception {
    List<String> lines = loadLines(new File(args[0]));
    ListIterator<String> iter = lines.listIterator();
    if (iter.hasNext())
      iter.next();
    int id = 1;
    map = new BigInteger[501][];
    for (int i=2; i<=500; i++) {
      map[i] = new BigInteger[i];
      map[i][1] = BigInteger.ONE;
      for (int j=2; j<i-1; j++) map[i][j] = BigInteger.ZERO;
      map[i][i-1] = BigInteger.ONE;
    }
    C = new BigInteger[501][];
    for (int i=1; i<=500; i++) {
      C[i] = new BigInteger[i+1];
      C[i][0] = BigInteger.ONE;
      for (int j=1; j<i; j++) C[i][j] = BigInteger.ZERO;
      C[i][i] = BigInteger.ONE;
    }
    power = new BigInteger[501];
    power[0] = BigInteger.ONE;
    power[1] = BigInteger.ONE;
    for (int i=2; i<501; i++) power[i] = BigInteger.ZERO;
       
    
    while (iter.hasNext()){
      String line = iter.next();
      int N = Integer.parseInt(line);
      
      BigInteger count = BigInteger.ZERO;
      for (int i=1; i<N; i++) {
        count = count.add(getMap(N, i));
      }
      count = count.mod(BigInteger.valueOf(100003));
      System.out.println("Case #"+id+": "+count);
      id++;
    }
    //for (int i=0; i<26; i++) {
      //System.err.println("power["+i+"]=" + power[i]);
    //}
  }

  private static BigInteger getMap(int n, int k) {
    //System.err.println(n+"----"+k);
    if (!map[n][k].equals(BigInteger.ZERO)) return map[n][k];
    for (int m=1; m<k; m++){
      if (k-m-1> n-k-1) continue;
      map[n][k] = getC(n-k-1, k-m-1).multiply(getMap(k,m)).add(map[n][k]);
      //System.err.println("subMAP" + map[n][k]);
    }
    //System.err.println("MAP" + map[n][k]);
    return map[n][k];
  }

  static BigInteger C[][];
  static BigInteger power[];
  private static BigInteger getC(int n, int k) {
    //System.err.println(n+"-"+k);
    if (!C[n][k].equals(BigInteger.ZERO)) return C[n][k];
    C[n][k] = getPower(n).divide(getPower(k)).divide(getPower(n-k));
    //System.err.println("CCC" + n + "-" + k + ":" + C[n][k]);
    return C[n][k];
  }

  private static BigInteger getPower(int n) {    
    if (!power[n].equals(BigInteger.ZERO)) return power[n];
    //System.err.println(n);
    power[n] = getPower(n-1).multiply(BigInteger.valueOf(n));    
    return power[n];
  }
}
