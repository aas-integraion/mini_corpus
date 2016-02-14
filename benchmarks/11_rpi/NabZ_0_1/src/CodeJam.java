import java.util.*;
import java.io.*;

public class CodeJam {
	  public static void main (String[] args){
	    try{
	      // read
	      FileInputStream instream = new FileInputStream("A-large.in");
	      DataInputStream in = new DataInputStream(instream);
	      BufferedReader br = new BufferedReader(new InputStreamReader(in));

	      // write
	      FileOutputStream outstream = new FileOutputStream("A-large.out");
	      DataOutputStream out = new DataOutputStream(outstream);
	      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));

	      // reading file information
	      int testcases = Integer.parseInt(br.readLine());
	      for(int i=0; i < testcases; i++){
	        // -------------------------------------------------------------------------------------------------------------
	        //int N = Integer.parseInt(br.readLine());
	        String line = br.readLine();
	        //String[] items = line.split(" ");
	        int N = Integer.parseInt(line);
	        String[] lines = new String[N];	
	        double[] WP = new double[N];
	        double[] OWP = new double[N];
	        double[] OOWP = new double[N];
	        for(int j=0; j<N;j++){
	        	lines[j] = br.readLine();
	        }
	        
	        double[] RPI = new double[N];
	        bw.write("Case #" + (i+1) + ": " + "" + "\r\n");
	        for(int j=0; j<N;j++){
	        	WP[j] = calcWP(lines[j]);
	        	OWP[j] = calcOWP(lines[j], lines, j);
	        	OOWP[j] = calcOOWP(lines[j], lines);
	        	RPI[j] = 0.25 * WP[j] + 0.50 * OWP[j] + 0.25 * OOWP[j];
	        	bw.write(RPI[j] + "\r\n");
	        }
	        
	        // -------------------------------------------------------------------------------------------------------------

	        
	      }

	      
	      in.close(); // close input file
	      bw.flush();
	      out.close(); // close output file
	    }catch(Exception e)
	    {
	      System.err.println("Error: " + e.getMessage());
	    }
	    finally
	    {
	    	System.out.println("Execution Complete");
	    }
	  }
	  
	  private static double calcWP(String str){
		  double total = 0, win =0;
		  for(char ch : str.toCharArray()){
			  if(ch == '1') { win++; total++;}
			  else if (ch == '0') {total++;}
		  }
		  return win/total;
	  }
	  
	  private static double calcWPByThrowingAGame(String str, int game){
		  double total = 0, win =0;
		  int g =0; 
		  for(char ch : str.toCharArray()){
			  if(g != game){
				  if(ch == '1') { win++; total++;}
				  else if (ch == '0') {total++;}
			  }
			  g++;
		  }
		  return win/total;
	  }
	  
	  private static double calcOWP(String str, String[] lines, int game){
		  double average = 0;
		  int total = 0;
		  for(int i=0; i<str.length();i++){
			  if(str.charAt(i) == '0' || str.charAt(i) == '1'){
				  average += calcWPByThrowingAGame(lines[i], game);
				  total++;
			  }
		  }
		  return average/total;
	  }
	  
	  private static double calcOOWP(String str, String[] lines){
		  double average = 0;
		  int total = 0;
		  for(int i=0; i<str.length();i++){
			  if(str.charAt(i) == '0' || str.charAt(i) == '1'){
				  average += calcOWP(lines[i], lines,i);
				  total++;
			  }
		  }
		  return average/total;
	  }
}

