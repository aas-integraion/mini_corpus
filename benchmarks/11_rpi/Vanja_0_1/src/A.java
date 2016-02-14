
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

//import static java.lang.Math.*;

public class A {
	// public final static String inputFile = "Ainput.in";
	public final static String inputFile = "A-large.in";
	public final static String outputFile = "output.out";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new FileInputStream(inputFile));
		PrintStream out = new PrintStream(outputFile);

		int numCases = in.nextInt();
		for (int caseNum = 1; caseNum <= numCases; caseNum++) {
			int n = in.nextInt();
			Integer[][] schedule = new Integer[n][n];
			double[] gamesWon = new double[n];
			double[] gamesPlayed = new double[n];
			Double[] wp = new Double[n];
			Double[] owp = new Double[n];
			Double[] oowp = new Double[n];
			for (int i = 0; i < n; i++) {
				String line = in.next();
				for (int i2 = 0; i2 < n; i2++) {
					char c = line.charAt(i2);
					if (c == '1') {
						schedule[i][i2] = 1;
						gamesWon[i]++;
						gamesPlayed[i]++;
					} else if (c == '0') {
						schedule[i][i2] = 0;
						gamesPlayed[i]++;
					}
				}
				wp[i] = gamesWon[i] / gamesPlayed[i];
			}

			for (int i = 0; i < n; i++) {
				double totalOwp = 0;
				for (int i2 = 0; i2 < n; i2++) {
					if (schedule[i][i2] != null) {
						double w = gamesWon[i2] - schedule[i2][i];
						double p = gamesPlayed[i2] - 1;
						totalOwp += (w / p);
					}
					owp[i] = totalOwp / gamesPlayed[i];
				}
			}
			for (int i = 0; i < n; i++) {
				double totalOowp = 0;
				for (int i2 = 0; i2 < n; i2++) {
					if (schedule[i][i2] != null) {
						totalOowp += owp[i2];
					}
				}
				oowp[i] = totalOowp / gamesPlayed[i];
			}

			String result = "";
			out.println("Case #" + caseNum + ": " + result.trim());
			System.out.println("Case #" + caseNum + ": " + result.trim());
			for (int i = 0; i < n; i++) {
				double rpi = (0.25 * wp[i]);
				rpi += (0.5 * owp[i]);
				rpi += (0.25 * oowp[i]);
				out.println(rpi);
				System.out.println(rpi);
			}
		}
		out.close();
	}
}
