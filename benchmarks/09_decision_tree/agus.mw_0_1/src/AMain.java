import java.util.*;
import java.io.*;
// Decision Tree

public class AMain{
	public static void main(String[] args){
		if(args.length<1){
			System.out.println("Usage: <fnIn>");
		}
		String fn = args[0];
		String fnOut = toFnOut(fn);
		try{
			BufferedReader br = new BufferedReader(new FileReader(fn));
			BufferedWriter bw = new BufferedWriter(new FileWriter(fnOut));
			String line = null;
			int NCase = Integer.valueOf(br.readLine().trim());
			for(int icase=0;icase<NCase;icase++){
				int L = Integer.valueOf(br.readLine().trim());
				String in = "";
				for(int i=0;i<L;i++){
					in += br.readLine();
				}
				int A = Integer.valueOf(br.readLine().trim());
				String[] As = new String[A];
				for(int i=0;i<A;i++){
					As[i] = br.readLine().trim();
				}
				String buf = new AMain().process(in,As);
				String out = "Case #"+(icase+1)+": "+buf;
				bw.write(out,0,out.length());
				bw.newLine();
				
			}
			br.close();
			bw.close();
		}catch(IOException ex){
			System.out.println(ex);
		}
	}
	
	String process(String text, String[] As){
		MyTree tree = new MyTree().create(text);
		String out = "";
		for(int i=0;i<As.length;i++){
			String[] parts = As[i].split("[\\s+]");
			HashSet<String> attr = new HashSet<String>();
			for(int j=2;j<parts.length;j++){
				attr.add(parts[j]);
			}
			double prob = tree.getProb(attr);
			out += String.format("\n%.7f",prob);
		}
		return out;
	}
	
	class MyTree{
		public double prob;
		public MyTree pos;
		public MyTree neg;
		public String childName = "";
		public MyTree(){
			prob = 0;
			pos = null;
			neg = null;
		}
		public double getProb(HashSet<String> attr){
			double cprob = prob;
			if(childName.length()>0){
				if(attr.contains(childName)){
					cprob *= pos.getProb(attr);
				}else{
					cprob *= neg.getProb(attr);
				}
			}
			return cprob;
		}
		public MyTree create(String text){
			//~ System.out.println(text);
			//~ System.out.flush();
			int start = 0;
			int end = text.length();
			while(text.charAt(start)!='(') start++;
			while(text.charAt(end-1)!=')') end--;
			start++;
			end--;
			boolean leaves = true;
			for(int i=start;i<end;i++) if(text.charAt(i)=='('){leaves = false;break;}
			MyTree out = new MyTree();
			if(leaves){
				out.prob = Double.valueOf(text.substring(start,end));
			}else{
				int a = start;
				while(!isWs(text.charAt(a))) a++;
				out.prob = Double.valueOf(text.substring(start,a));
				start = a;
				while(isWs(text.charAt(start))) start++;
				a = start;
				while(isAlpha(text.charAt(a))) a++;
				out.childName = text.substring(start,a);
				start = a;
				while(isWs(text.charAt(start))) start++;
				int depth = 0;
				a = start;
				while(true){
					if(text.charAt(a)=='(') depth++;
					if(text.charAt(a)==')') depth--;
					a++;
					if(depth==0) break;
				}
				out.pos = create(text.substring(start,a));
				out.neg = create(text.substring(a,end));
			}
			return out;
		}
		public boolean isAlpha(char c){
			return (c>='a' && c<='z') || (c>='A' && c<='Z');
		}
		public boolean isWs(char c){
			return c==' ' || c=='\n' || c=='\t' || c=='\r';
		}
	}
	
	static String toFnOut(String fn){
		if(fn.lastIndexOf('.')!=-1){
			return fn.substring(0,fn.lastIndexOf('.'))+".out";
		}else return fn + ".out";
	}
	
}
