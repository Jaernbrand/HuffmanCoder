import static org.junit.Assert.*;

import org.junit.Test;

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
	public void testEncodeAndDecodeString(){
		String input = "alla gillar rally";
		HuffmanDecoder theDeHuff = new HuffmanDecoder(); 
		
		byte[] compression = theHuff.encode(input);
		String decompression = theDeHuff.decode(compression);
		assertEquals( "alla giller rally", decompression);
	}
} // HuffmanCoderTester
