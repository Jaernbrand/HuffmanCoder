import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.io.ByteArrayOutputStream;

public class HuffmanCoderTester {

	HuffmanCoder theHuff = new HuffmanCoder();
	
	@Test
	public void testOccurrences() {
		String input = "Hello hello, how are you?!";
		theHuff.readString(input);
		HashMap<Character, Integer> oracle = new HashMap<Character, Integer>();
		for (int i=0; i < input.length(); ++i){
			Character currChar = input.charAt(i);
			if ( oracle.get(currChar) == null ){
				oracle.put(currChar, 1);
			} else {
				oracle.put(currChar, oracle.get(currChar) + 1);
			}
		}
		
		Map<Character, Integer> occurrences = theHuff.getOccurrences();
		for (Character c : occurrences.keySet() ){
			assertEquals(oracle.get(c), occurrences.get(c));
		}
		assertEquals(oracle.keySet().size(), occurrences.keySet().size());
	}
	
	@Test
	public void testByteEncodingFromString(){
		String input = "allllas";
		
		// l = 1, a = 01, s = 00
		byte oracle[] = {0x7C, 0x20};
		byte huffArr[] = theHuff.encode(input);
	
		assertEquals(oracle.length, huffArr.length);
		for (int i=0; i < oracle.length; ++i){
			assertEquals(oracle[i], huffArr[i]);
		}
	} // testByteEncodingFromString
	
	
	@Test
	public void testDfsTreePath(){
		HuffmanCoder dustin = new HuffmanCoder();
		dustin.readString("alla gillar rally");
		
		ArrayList<Integer> theWay = dustin.dfs(dustin.buildTree(), 'a');
		ArrayList<Integer> oracle = new ArrayList<Integer>(Arrays.asList(1,0));
		
		for(int i = 0; i < theWay.size(); ++i)
			assertEquals(oracle.get(i),theWay.get(i));
			
	}
	
	@Test
	public void testMapForSavingCharAndArraysWithInt(){
		HuffmanCoder dustin = new HuffmanCoder();
		dustin.readString("alla gillar rally");
		
		ArrayList<Integer> theWay = dustin.dfs(dustin.buildTree(), 'a');
		ArrayList<Integer> oracle = new ArrayList<Integer>(Arrays.asList(0,1));
		//saveCharByteRep(/*root*/);
		for(int i = 0; i < theWay.size(); ++i)
			assertEquals(oracle.get(i),theWay.get(i));
			
	}
	
	
}



