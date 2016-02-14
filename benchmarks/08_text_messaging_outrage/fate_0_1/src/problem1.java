import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

/**
 * 
 */

/**
 * @author sapan
 *
 */


class Message {

	int maxLetters;
	int nKeys;
	int nLetters;
	Vector<Long> lFreq = new Vector<Long>();
	
	public int getMaxLetters() {
		return maxLetters;
	}
	public void setMaxLetters(int maxLetters) {
		this.maxLetters = maxLetters;
	}
	public int getNKeys() {
		return nKeys;
	}
	public void setNKeys(int keys) {
		nKeys = keys;
	}
	public int getNLetters() {
		return nLetters;
	}
	public void setNLetters(int letters) {
		nLetters = letters;
	}
	public Vector<Long> getLFreq() {
		return lFreq;
	}
	public void setLFreq(Vector<Long> freq) {
		lFreq = freq;
	}
	
	public Message(int maxLetters, int keys, int letters, Vector<Long> freq) {
		super();
		this.maxLetters = maxLetters;
		nKeys = keys;
		nLetters = letters;
		lFreq = freq;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + maxLetters + ", "+nKeys + ", "+nLetters+ ", "+lFreq+" ]";
	}
}	

public class problem1 {

	public static Vector<Message> testCases = new Vector<Message>();
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		if(args.length != 2)
			throw new Exception("Usage: <InputFileName> <outputFileName>");
		
		readInputFile(args[0]);
		
		BufferedWriter outputWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1])),4096);

		for(int ncases = 0 ; ncases < testCases.size(); ncases++)
		{
			Message curMessage = testCases.get(ncases);
			sort(curMessage.getLFreq());
			int nkeys = curMessage.getNKeys();
			int nletters = curMessage.getNLetters();
			int maxLetters = curMessage.getMaxLetters();
			Vector<Long> lFreq = curMessage.getLFreq();
			Long finalCount = 0L;
			int mul = 0;
			for(int i=0;i<maxLetters; i++)
			{
				mul++;
				for(int j=0; j< nkeys; j++)
				{
					if(lFreq.size() == 0)
						break;
					finalCount += lFreq.remove(0) * mul; 
				}
			}

			outputWriter.write("Case #"+(ncases+1)+": "+finalCount+"\n");
			outputWriter.flush();
			System.out.println("Case #"+(ncases+1)+": "+finalCount);

		}
		outputWriter.close();
		
	}


	private static void sort(Vector<Long> freq) {
		Long temp;
		for(int i=0; i< freq.size(); i++)
		{
			Long iValue = freq.get(i);
			for(int j=0; j<freq.size(); j++)
			{
				if(iValue > freq.get(j))
				{
					temp = freq.get(j);
					freq.set(j, iValue);
					freq.set(i, temp);
					iValue = temp;
				}
			}
		}
	}


	private static void readInputFile(String string) throws Exception {
		try{
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(new FileInputStream(string)),4096);
			int nCases = Integer.parseInt(inputReader.readLine());
			for(int i=0; i< nCases; i++)
			{
				String[] paras = inputReader.readLine().split(" ");
				int maxLetters = Integer.parseInt(paras[0]);
				int nKeys = Integer.parseInt(paras[1]);
				int nLetters = Integer.parseInt(paras[2]);
				
				Vector<Long> lFreq = new Vector<Long>();
				paras = inputReader.readLine().split(" ");
				for(int j=0; j< nLetters; j++)
				{
					lFreq.add(Long.parseLong(paras[j]));
				}
				testCases.add(new Message(maxLetters,nKeys,nLetters,lFreq));
			}
			inputReader.close();
		}catch(Exception e)
		{
			throw new Exception("error in input file");
		}
	}


}
