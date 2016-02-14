import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DecisionTree {

	static DecimalFormat format = new DecimalFormat("0.0000000");
	
	static {
		DecimalFormatSymbols s = new DecimalFormatSymbols();
		s.setDecimalSeparator('.');
		format.setDecimalFormatSymbols(s);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner scn = new Scanner(new FileInputStream(args[0]));
		FileWriter writer = new FileWriter(
				args[0].substring(0, args[0].lastIndexOf('.') + 1) + "out");
		
		int cases = scn.nextInt();
		
		for (int i = 0; i < cases; i++) {
			int lines = scn.nextInt(); scn.nextLine();
			StringBuilder builder = new StringBuilder();
			for (int j = 0; j < lines; j++) {
				builder.append(scn.nextLine());
			}
			int animalCount = scn.nextInt();
			List<Animal> animals = new ArrayList<Animal>(animalCount);
			for (int j = 0; j < animalCount; j++) {
				Animal animal = new Animal(scn.next());
				int atts = scn.nextInt();
				for (int k = 0; k < atts; k++) {
					animal.atts.add(scn.next());
				}
				animals.add(animal);
			}
			
			builder.deleteCharAt(builder.indexOf("("));
			builder.deleteCharAt(builder.lastIndexOf(")"));
			
			Node parent = buildTree(builder.toString());
			
			writer.write("Case #" + (i+1) +":\n");
			
			for (Animal animal : animals) {
				writer.write(format.format(calc(parent, animal)) + "\n");
			}
		}
		
		writer.close();
	}
	
	
	private static double calc(Node parent, Animal animal) {
		if (parent.att == null) {
			return parent.w;
		}
		
		if (animal.atts.contains(parent.att)) {
			return parent.w * calc(parent.yes, animal);
		}
		
		return parent.w * calc(parent.no, animal);
	}


	static Node buildTree(String tree) {
		Scanner scnT = new Scanner(tree);
		Node parent = new Node(Double.valueOf(scnT.next()));
		
		if (scnT.hasNext()) {
			parent.att = scnT.next();
			
			int firstIndexOfOpen = 0;
			boolean findFirst = false;
			int open = 0;
			int firstIndexOfClose = 0;
			
			for (int i = firstIndexOfOpen + 1; i < tree.length(); i++) {
				if (tree.charAt(i) == '(') {
					open++;
					if (!findFirst) {
						firstIndexOfOpen = i;
						findFirst = true;
					}
				}
				if (tree.charAt(i) == ')') {
					open--;
					if (open == 0 && findFirst) {
						firstIndexOfClose = i;
						break;
					}
				}
			}
			
			String left = tree.substring(firstIndexOfOpen + 1, firstIndexOfClose);
			
			int secondIndexOfOpen = firstIndexOfClose;
			findFirst = false;
			open = 0;
			int secondIndexOfClose = 0;
			
			for (int i = secondIndexOfOpen + 1; i < tree.length(); i++) {
				if (tree.charAt(i) == '(') {
					open++;
					if (!findFirst) {
						secondIndexOfOpen = i;
						findFirst = true;
					}
				}
				if (tree.charAt(i) == ')') {
					open--;
					if (open == 0 && findFirst) {
						secondIndexOfClose = i;
						break;
					}
				}
			}
			
			
			String right = tree.substring(secondIndexOfOpen + 1, secondIndexOfClose);
			
			parent.yes = buildTree(left.trim());
			parent.no = buildTree(right.trim());
		}
		
		return parent;
	}
}

class Node {
	String att;
	double w;
	Node yes;
	Node no;
	public Node(double w) {
		this.w = w;
	}
}

class Animal {
	String name;
	List<String> atts = new ArrayList<String>();
	
	public Animal(String name) {
		super();
		this.name = name;
	}
}
