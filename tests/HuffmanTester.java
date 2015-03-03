import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.Test;
import java.io.FileInputStream;


public class HuffmanTester {

	@Test
	public void testToFewMainArgs(){
		String[] args = {"-decode", "test.txt"};
		Huffman.main(args);
		// TODO
	}

	private byte[] readByteArray(String fileName){
		byte[] retArr = null;
		
		ByteArrayOutputStream output = null;
		FileInputStream fi = null;
		try{
			output = new ByteArrayOutputStream();
			fi = new FileInputStream(fileName);
			
			int tmp = fi.read();
			while (tmp != -1){
				output.write( (byte)tmp );
				tmp = fi.read();
			}
			retArr = output.toByteArray();
			
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				output.close();
				fi.close();
			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
		return retArr;
	}
	
	@Test
	public void testBytesAreCorrect(){
		String[] args = {"-encode", "testFiles/testInput0.txt", "testFiles/testOutput0.txt"};
		Huffman.main(args);
		
		// l = 1, a = 01, s = 00
		byte[] oracle = {0x7C, 0x20};
		byte[] huffArr = readByteArray("testFiles/testOutput0.txt");
		
		assertEquals(oracle.length, huffArr.length);
		for (int i=0; i < oracle.length; ++i){
			assertEquals(oracle[i], huffArr[i]);
		}
	} // testBytesAreCorrect
} // HuffmanTester
