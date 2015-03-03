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
	public void test(){
		fail();
	}
	
	@Test
	public void testDfsTreePath(){
		HuffmanCoder dustin = new HuffmanCoder();
		dustin.readString("alla gillar rally");
		dustin.createNodes();
		
		ArrayList<Integer> theWay = dustin.dfs(dustin.buildTree(), 'a');
		ArrayList<Integer> oracle = new ArrayList<Integer>(Arrays.asList(0,1));
		
		for(int i = 0; i < theWay.size(); ++i)
			assertEquals(theWay.get(i), oracle.get(i));
			
	}
	
	
	
}



