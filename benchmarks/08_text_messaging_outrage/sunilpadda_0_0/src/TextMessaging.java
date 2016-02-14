import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextMessaging {

	public static void main(String[] args) throws Exception {
		findNumberOfPresses (args[0], args[1]);
	}
	
	public static void findNumberOfPresses(String inputFile, String outputFile) throws IOException, FileNotFoundException {
		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		PrintWriter outputWriter = new PrintWriter(outputFile);
		int numberOfKeys = 0, numberOfPlaces = 0, numberOfLetters = 0;
		int[] frequency = null;

		String[] splits = null;
		String s = null;
		int state = 0;
		int caseIndex = -1, numberOfCases = 0;
		while((s = inputReader.readLine()) != null) {
			if(state == 1 && ++caseIndex == numberOfCases) {
				caseIndex = -1;
				//TODO error scenario, should not happen.
				break;
			}
			switch (state) {
			case 0:
				numberOfCases = Integer.parseInt(s);
				state = 1;
				break;
			case 1:
				splits = s.split(" ");
				numberOfPlaces = Integer.parseInt(splits[0]);
				numberOfKeys = Integer.parseInt(splits[1]);
				numberOfLetters = Integer.parseInt(splits[2]);
				state = 2;
				break;
			case 2:
				frequency = new int[numberOfLetters];
				splits = s.split(" ");
				int j = 0;
				for(String split : splits ) {
					frequency[j++] = Integer.parseInt(split);
				}
				Arrays.sort(frequency);
				
				int numberOfPresses = 0;
				outer: for(int i = 0; i < numberOfPlaces; i++) {
					for(int k = 0; k < numberOfKeys; k++) {
						if(i*numberOfKeys+ k + 1 > numberOfLetters) {
							break outer;
						}
						numberOfPresses += frequency[numberOfLetters - (i*numberOfKeys + k + 1)] * (i+1);
					}
				}
				outputWriter.println("Case #"+(caseIndex+1)+": "+numberOfPresses);
				state = 1;
				break;
			}
				
		}
		inputReader.close();
		outputWriter.flush();
		outputWriter.close();
	}
}
