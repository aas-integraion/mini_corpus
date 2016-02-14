import java.io.*;
import java.util.*;
public class try1{
	public static void main(String arg[]){
		try{
		
		File outFile = new File("out.txt");

            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outFile)), true);

            InputStreamReader isr = new InputStreamReader(System.in);

            DataInputStream br = new DataInputStream(new FileInputStream("input.txt"));
            int testcases = Integer.parseInt(br.readLine());
            for(int i=1;i<testcases+1;i++){
            	int p,k,l;
            	String str = br.readLine();
            	int pos=str.indexOf(" ");
            	p=Integer.parseInt(str.substring(0,pos));
            	str=str.substring(pos+1,str.length());
            	//System.out.println("23");
            	pos=str.indexOf(" ");
            	k=Integer.parseInt(str.substring(0,pos));
            	str=str.substring(pos+1,str.length());
            	//pos=str.indexOf(" ");
            	//System.out.println("23");
            	l=Integer.parseInt(str);
            	//str=str.substring(pos+1,str.length());
            	Vector freq=new Vector(l);
            	//System.out.println("23");
            	str=br.readLine();
            	for(int j=0;j<l-1;j++){
            		//freq.addElement(new Integer(Integer.parseInt(br)))
            		pos=str.indexOf(" ");
            	freq.addElement(new Integer(Integer.parseInt(str.substring(0,pos))));
            	//System.out.println("23");
            	str=str.substring(pos+1,str.length());
            	}
            	freq.addElement(new Integer(Integer.parseInt(str)));
            //	System.out.println("23");
            	int num;
            	int sum=0;
            	int no=1;
            	int length=freq.size();
            	Collections.sort(freq);
            	System.out.println(p+ "  "+k+"  "+l+"   "+length);
            	for(int j=1;j<p+1;j++){
            		for(int m=1;m<k+1;m++){
            			num=((Integer)freq.elementAt(length-1)).intValue();
            			sum=sum+ num*j;
            			System.out.println(num + "  " +sum);
            			length--;
            			no++;
            			if(no>l){
            				break;
            			}
            		}
            		if(no>l){
            				break;
            			}
            	}
            	length=freq.size();
            //	sum=sum+(Integer)freq.elementAt(length-1);
            	System.out.println("case"+sum);
            	out.println("Case #"+i+": "+sum);
            }
	}
	catch(Exception e){
	System.out.println(e.getMessage());
	}
	}
	}