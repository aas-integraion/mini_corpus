import java.io.*;
import java.util.*;
import java.text.*;

public class DecisionTree
{
	public static int index;
	public static void main(String[] args)throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("DecisionTree.in"));
		PrintWriter out = new PrintWriter("DecisionTree.out");
		//PrintStream out = System.out;
		DecimalFormat df = new DecimalFormat("0.0000000");
		
		
		int testcase = Integer.parseInt(in.readLine());
		for (int t=1; t<=testcase; t++)
		{
			int lines = Integer.parseInt(in.readLine());
			StringBuffer total = new StringBuffer();
			for (int l = 0; l<lines; l++)
				total.append(in.readLine());
			String[] arrayed = total.toString().substring(1).split("[\\(\\)\t ]+");
			
			index = 0;
			
			Node top = buildTree(arrayed);
			
			int subjects = Integer.parseInt(in.readLine());
			
			out.println("Case #" + t + ":");
			
			for (int s=0; s<subjects; s++)
			{
				arrayed = in.readLine().split(" ");
				HashSet<String> chrs = new HashSet<String>();
				for (int x=0; x<Integer.parseInt(arrayed[1]); x++)
					chrs.add(arrayed[x+2]);
				
				double cute = 1;
				boolean exit = false;
				Node current = top;
				
				while(!exit)
				{
					cute *= current.weight;
					if (current.type != null)
					{
						if (chrs.contains(current.type))
							current = current.yes;
						else
							current = current.no;
					}
					else
						exit = true;
				}
				
				out.println(df.format(cute));
			}
		}
		out.close();
	}
	
	public static Node buildTree(String[] arrayed)
	{
		Node node = new Node(null, null, null, Double.parseDouble(arrayed[index]));
		if (index==arrayed.length-1)
			return node;
		if (Character.isDigit(arrayed[index+1].charAt(0)))
			return node;
		else
		{
			index++;
			node.type = arrayed[index];
			index++;
			node.yes = buildTree(arrayed);
			index++;
			node.no = buildTree(arrayed);
			return node;
		}
	}
}

class Node
{
	public String type;
	public Node yes;
	public Node no;
	public double weight;
	public Node(String t, Node y, Node n, double w)
	{
		type = t;
		yes = y;
		no = n;
		weight = w;
	}
	
	public String toString()
	{
		return type + " " + weight;
	}
}