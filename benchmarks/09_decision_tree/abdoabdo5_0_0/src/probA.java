import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
class Tree{
	double w;
	String feature="";
	Tree lih,malhosh;
	
}
public class probA {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner scanner = new Scanner(new File("A-small.in"));
			File f = new File("B-small.out");
			FileOutputStream fop = new FileOutputStream(f);
			int testCases = 1,n=scanner.nextInt();
			scanner.nextLine();
			for (int i = 0; i < n; i++) {
				System.out.println("Case #" + testCases + ":");
				testCases++;
				String sol = null;
				int l=scanner.nextInt();
				scanner.nextLine();
				String t="";
				for (int j = 0; j < l; j++) {
					t+=scanner.nextLine();
				}
				Tree tree=parse(t.substring(1,t.length()-1));
				int animals=scanner.nextInt();
				scanner.nextLine();
				for (int j = 0; j < animals; j++) {
					String test=scanner.nextLine();
					String[] str=test.split("[ ]+");
					HashMap<String	,Integer> map = new HashMap<String, Integer>();

					for (int k = 2; k < str.length; k++) {
						map.put(str[k], k);
					}
					
					System.out.println(Double.toString(solve(map,tree,1)));
				}
				
			}			

		} catch (FileNotFoundException e) {
			// TODO: handle exception
		}
	}

	private static double solve(HashMap<String, Integer> map, Tree tree, double p) {
		// TODO Auto-generated method stub
		if(tree.feature.equals(""))   return p*tree.w;
		p*=tree.w;
		if(map.containsKey(tree.feature))     return solve(map, tree.lih, p);
		return solve(map, tree.malhosh, p);
	}

	private static Tree parse(String t) {
		// TODO Auto-generated method stub
		Tree sol=new Tree();
		Scanner s=new Scanner(t);
			sol.w=s.nextDouble();
			if(!s.hasNext())   return sol; 
			sol.feature=s.next();
			int first=t.indexOf("(");
			int count=1;
			int i = first+1;
			for (; i < t.length()&&count>0; i++) {
				if(t.charAt(i)=='(')   count++;
				if(t.charAt(i)==')')   count--;
			}
			sol.lih=parse(t.substring(first+1,i-1));
			String tmp=t.substring(i);
			first=tmp.indexOf("(");
			 count=1;
			 i = first+1;
			for (; i < tmp.length()&&count>0; i++) {
				if(tmp.charAt(i)=='(')   count++;
				if(tmp.charAt(i)==')')   count--;
			}
			sol.malhosh=parse(tmp.substring(first+1,i-1));
		return sol;
	}

	

}
