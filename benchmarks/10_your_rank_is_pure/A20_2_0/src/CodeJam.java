

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CodeJam {
	
	private static final String RESULT_TEMPLATE = "Case #{0}: {1}";
	static final String FILE_NAME = "d:\\tt\\C-small-attempt0.in";
	
	public static void main(String[] args) {
		
		System.out.println("*** Starting application ***");
		
		try {
			Scanner sc = new Scanner(new File(FILE_NAME));
			FileWriter outFile = new FileWriter(FILE_NAME+".res");
			PrintWriter out = new PrintWriter(outFile);
					
			int numOfCases = sc.nextInt();
			
			for(int i = 1; i <= numOfCases; i++) {
				int N = sc.nextInt();
				String res = doCase(N);
				//res = MessageFormat.format(RESULT_TEMPLATE, i, res);
				res = "Case #"+i+": "+ res;
				out.println(res);
				System.out.println(res);				
			}
			
			sc.close();
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("*** Done ***");
	}
	
	public static String doCase(int n) {
		
		int res = 0;
		
		int k = 1<<n-1;
		
		
		
		for(int i = 2; i < k; i+=2) {
			int current  = n;
			while(true) {
				current = countOnes(i, current);
				if(i>>current-1 % 2 == 0) {
					break;
				}
				if(current <= 1) {
					res ++;
					break;
				}
			}
		}
		
		return (res+1)%100003 + "";
			
	}
	
	public static int countOnes(int n,int till) {
		int res = 0;
		n = n >> 1;
		for(int i = 1; i < till-1; i++) {
			if(n % 2 == 1) {
				res ++;
			} 
			n = n >> 1;
		}
		return res;
	}
	
}
