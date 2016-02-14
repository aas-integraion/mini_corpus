import java.io.*;
import java.util.*;

public class RPI {
	static Scanner sc;
	static PrintStream p;
	
	public static void main(String[] args) throws FileNotFoundException {	
		sc = new Scanner(new File("src/inp.txt"));
		p = new PrintStream(new FileOutputStream("src/out.txt"));
		
		int T = sc.nextInt();
		for (int i=1; i<=T; i++) {
			double[] ans = run();
			p.println("Case #" + i + ":");
			for (double an : ans)
				p.println(an);
		}
	}
	
	public static double[] run() {
		int N = sc.nextInt();
		char[][] arr = new char[N][N];
		for (int i=0; i<N; i++) {
			String s = sc.next();
			for (int j=0; j<N; j++)
				arr[i][j] = s.charAt(j); 
		}
		
		double[] WINS = new double[N];
		double[] GAMESPLAYED = new double[N];
		double[] WP = new double[N];
		for (int i=0; i<N; i++) {
			int gp=0;
			int wins=0;
			for (int j=0; j<N; j++) {
				if (arr[i][j] != '.')
					gp++;
				if (arr[i][j] == '1')
					wins++;
			}
			WINS[i] = wins;
			GAMESPLAYED[i] = gp;
			WP[i] = ((double)wins) / gp;
		}
		double[] OWP = new double[N];
		for (int i=0; i<N; i++) {
			int opps = 0;
			double sum = 0;
			for (int j=0; j<N; j++) {
				if (arr[i][j] != '.')
					opps++;
				if (arr[i][j] == '0')
					sum += (WINS[j]-1)/(GAMESPLAYED[j]-1);
				if (arr[i][j] == '1')
					sum += (WINS[j])/(GAMESPLAYED[j]-1);
			}
			OWP[i] = sum / opps;
		}
		double[] OOWP = new double[N];
		for (int i=0; i<N; i++) {
			int opps = 0;
			double sum = 0;
			for (int j=0; j<N; j++) {
				if (arr[i][j] != '.') {
					opps++;
					sum += OWP[j];
				}
			}
			OOWP[i] = sum / opps;
		}
		
		double[] ans = new double[N];
		for (int i=0; i<N; i++)
			ans[i] = .25*WP[i] + .50*OWP[i] + .25*OOWP[i];
		return ans;
	}
}
