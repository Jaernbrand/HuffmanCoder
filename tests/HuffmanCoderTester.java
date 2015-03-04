import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.io.ByteArrayOutputStream;

import huffman.*;

public class HuffmanCoderTester {

	HuffmanCoder theHuff = new HuffmanCoder();
	
//	@Test
//	public void testOccurrences() {
//		String input = "Hello hello, how are you?!";
//		theHuff.readString(input);
//		HashMap<Character, Integer> oracle = new HashMap<Character, Integer>();
//		for (int i=0; i < input.length(); ++i){
//			Character currChar = input.charAt(i);
//			if ( oracle.get(currChar) == null ){
//				oracle.put(currChar, 1);
//			} else {
//				oracle.put(currChar, oracle.get(currChar) + 1);
//			}
//		}
//		
//		Map<Character, Integer> occurrences = theHuff.getOccurrences();
//		for (Character c : occurrences.keySet() ){
//			assertEquals(oracle.get(c), occurrences.get(c));
//		}
//		assertEquals(oracle.keySet().size(), occurrences.keySet().size());
//	}
	
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
	public void testDfsTreePathForChars(){
		HuffmanCoder dustin = new HuffmanCoder();
		dustin.readString("alla gillar rally");
		
		Node root = dustin.buildTree();
		
		ArrayList<Integer> repForCharA = dustin.dfs(root, 'a');
		ArrayList<Integer> oracleForA = new ArrayList<Integer>(Arrays.asList(1,0));		
		for(int i = 0; i < repForCharA.size(); ++i)
			assertEquals(oracleForA.get(i),repForCharA.get(i));
		
		ArrayList<Integer> repForCharI = dustin.dfs(root, 'i');
		ArrayList<Integer> oracleForI = new ArrayList<Integer>(Arrays.asList(0,0,1,1));
		for(int i = 0; i < repForCharA.size(); ++i)
			assertEquals(oracleForI.get(i),repForCharI.get(i));
		
		ArrayList<Integer> repForCharG = dustin.dfs(root, 'g');
		ArrayList<Integer> oracleForG = new ArrayList<Integer>(Arrays.asList(0,0,1,0));
		for(int i = 0; i < repForCharG.size(); ++i)
			assertEquals(oracleForG.get(i),repForCharG.get(i));
		
	}//testDfsTreePath
	
	
}



