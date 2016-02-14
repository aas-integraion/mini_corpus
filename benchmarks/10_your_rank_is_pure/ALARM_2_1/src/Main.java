import java.io.*;
import java.util.*;

public class Main {

	Scanner in = new Scanner(new BufferedReader(
			new InputStreamReader(System.in)));
	PrintWriter out = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));

	private final String fileName = "e:\\Olimp\\Solutions\\Code Jam\\C";
	private final int mod = 100003;
	int[][] cnk = new int[501][501];
	{
		cnk[0][0] = 1;
		for (int i = 1; i < cnk.length; ++i) {
			for (int j = 0; j <= i; ++j) {
				cnk[i][j] = cnk[i - 1][j];
				if (j > 0) {
					cnk[i][j] += cnk[i - 1][j - 1];
				}
				if (cnk[i][j] >= mod) {
					cnk[i][j] -= mod;
				}
			}
		}
	}

	Integer[][] dp = new Integer[501][501];

	public void solution() throws IOException {
		in = new Scanner(new BufferedReader(new FileReader(fileName + ".in")));
		out = new PrintWriter(fileName + ".out");
		calc(500, 500);
		for (int cas = 1, q = in.nextInt(); q > 0; --q, ++cas) {
			int n = in.nextInt();
			out.printf("Case #%d: ", cas);
			int res = 0;
			for (int size = 1; size < n; ++size) {
				res += calc(size, n);
				if (res >= mod) {
					res -= mod;
				}
			}
			out.println(res);
		}
		out.flush();
	}

	private int calc(int size, int last) {
		if (size == 1) {
			return 1;
		}
		if (dp[size][last] != null) {
			return dp[size][last];
		}
		int res = 0;
		for (int pos = 1; pos < size; ++pos) {
			if (last - size - 1 >= 0 && size - pos - 1 >= 0) {
				res = (int) ((res + (long) calc(pos, size)
						* cnk[last - size - 1][size - pos - 1]) % mod);
			}
		}
		return dp[size][last] = res;
	}

	private class Scanner {

		BufferedReader reader;
		StringTokenizer tokenizer;

		public Scanner(BufferedReader reader) {
			this.reader = reader;
			this.tokenizer = new StringTokenizer("");
		}

		public boolean hasNext() throws IOException {
			while (!tokenizer.hasMoreTokens()) {
				String next = reader.readLine();
				if (next == null) {
					return false;
				}
				tokenizer = new StringTokenizer(next);
			}
			return true;
		}

		public String nextLine() throws IOException {
			tokenizer = new StringTokenizer("");
			return reader.readLine();
		}

		public String next() throws IOException {
			hasNext();
			return tokenizer.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public double nextDouble() throws IOException {
			return Double.parseDouble(next());
		}

	}

	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
}