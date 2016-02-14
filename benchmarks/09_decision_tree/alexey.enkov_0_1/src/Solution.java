import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
import java.util.Queue;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

public class Solution implements Runnable {

	public static void main(String[] args) {
		(new Thread(new Solution())).start();
	}

	BufferedReader in;
	PrintWriter out;
	StringTokenizer st;

	String nextToken() throws IOException {
		while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(in.readLine());
		return st.nextToken();
	}

	int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}
	
	class Vert {
		double p;
		String ft;
		Vert l, r;
		Vert(double q) {
			p = q;
			ft = null;
			l = r = null;
		}
	}
	
	String res;
	int q;
	
	Vert tree() {
		while (res.charAt(q) != '(') q++;
		while (res.charAt(q) == ' ' || res.charAt(q) == '(') q++;
		int w = q;
		while (res.charAt(w) != ' ' && res.charAt(w) != ')') w++;
		Vert r = new Vert(Double.parseDouble(res.substring(q, w)));
		q = w;
		while (res.charAt(q) == ' ') q++;
		if (res.charAt(q) != ')') { 
			w = q;
			while (Character.isLetter(res.charAt(w))) w++;
			r.ft = res.substring(q, w);
			q = w;
			r.l = tree();
			r.r = tree();
		}
		q++;
		return r;
	}
	
	TreeSet<String> qq;
	
	double dfs(Vert q, double p) {
		p *= q.p;
		if (q.ft == null) return p; else {
			if (qq.contains(q.ft)) return dfs(q.l, p); else return dfs(q.r, p);
		}
	}
	
	void solve() throws IOException {
		int l = nextInt();
		res = "";
		for (int i = 0; i < l; i++) {
			res += " " + in.readLine();
		}
		q = 0;
		Vert root = tree();
		int a = nextInt();
		out.println();
		for (int i = 0; i < a; i++) {
			TreeSet<String> q = new TreeSet<String>();
			String s = nextToken();
			int nn = nextInt();
			for (int j = 0; j < nn; j++) q.add(nextToken());
			qq = q;
			out.println(dfs(root, 1));
		}
	}

	public void run() {
		Locale.setDefault(Locale.UK);
		try {
			if (System.getProperty("ONLINE_JUDGE") != null) {
				in = new BufferedReader(new InputStreamReader(System.in));
				out = new PrintWriter(System.out);
			} else {
				in = new BufferedReader(new FileReader(new File("input.txt")));
				out = new PrintWriter(new FileWriter(new File("output.txt")));
			}
			int t = nextInt();
			for (int nn = 1; nn <= t; nn++) {
				out.print("Case #" + nn + ": ");
				solve();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.flush();
	}

}
