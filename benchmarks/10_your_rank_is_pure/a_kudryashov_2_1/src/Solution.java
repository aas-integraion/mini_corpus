import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.StringTokenizer;


public class Solution implements Runnable {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		new Thread(new Solution()).start();
	}

	public void run() {
		try {
			solve();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Koval - gondon");
		}
	}

	private String next() throws Exception {
		while (tok == null || !tok.hasMoreTokens()) {
			tok = new StringTokenizer(cin.readLine());
		}
		return tok.nextToken();
	}
	
	private int nextInt() throws Exception {
		return Integer.parseInt(next());		
	}
	
	BufferedReader cin;
	PrintWriter cout;
	StringTokenizer tok;
		
	BigInteger[][] c, cc;
	BigInteger[] facts;
	
	private void solve() throws Exception {
		cin = new BufferedReader(new FileReader("input.txt"));
		cout = new PrintWriter("output.txt");
		c = new BigInteger[1000][1000];
		cc = new BigInteger[1000][1000];
		facts = new BigInteger[1000];
		int t = nextInt();
		for (int i = 0; i < t; i++) {
			cout.println("Case #" + (i + 1) + ": " + calc(nextInt()));
		}

		cin.close();
		cout.close();
		
	}
	
	BigInteger fact(int n) {
		if (n == 0)
			return BigInteger.ONE;
		if (facts[n] != null)
			return facts[n];
		BigInteger result = fact(n - 1).multiply(BigInteger.valueOf(n));
		return facts[n] = result;
	}
	
	BigInteger get(int n, int k) {
		if (k == 1)
			return BigInteger.ONE;
		if (c[n][k] != null)
			return c[n][k];
		BigInteger result = BigInteger.ZERO;
		result = result.add(get(k, k - 1));
		int dst = n - k - 1;
		for (int i = 1; i <= k - 2; i++) {
			result = result.add(get(k, i).multiply(c(dst, k - i - 1)));
		}
		return c[n][k] = result.mod(BigInteger.valueOf(100003));
	}
	
	BigInteger c(int n, int k) {
		if (n < k)
			return BigInteger.ZERO;
		if (cc[n][k] != null)
			return cc[n][k];			
		return cc[n][k] = fact(n).divide(fact(k).multiply(fact(n - k))).mod(BigInteger.valueOf(100003));
	}

	private BigInteger calc(int num) {
		BigInteger result = BigInteger.ZERO;
		for (int i = 1; i <= num - 1; i++) {
			result = result.add(get(num, i));
		}
		return result.mod(BigInteger.valueOf(100003));
	}


}
