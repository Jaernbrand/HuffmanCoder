import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import java.io.FileInputStream;

import huffman.*;

public class HuffmanTester {
	
	private static String readStringFromFile(String fileName){
		StringBuilder builder = new StringBuilder();
		
		BufferedReader br = null;
		try{
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			int tmpChar = br.read();
			while(tmpChar != -1){
				builder.append( (char)tmpChar );
				tmpChar = br.read();
			}
			
		} catch (IOException e){
			e.printStackTrace();
			
		} finally {
			try {
				br.close();

			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
		return builder.toString();
	} // readStringFromFile

	
	
	@Test
	public void testCompressAndDecompressFileTestinput0(){
		String[] args = {"-encode", "testFiles/testInput0.txt", "testFiles/testOutput0.huff"};
		Huffman.main(args);
		
		String[] args2 = {"-decode", "testFiles/testOutput0.huff", "testFiles/newTestInput0.txt"};
		Huffman.main(args2);
		
		String original = readStringFromFile("testFiles/testInput0.txt");
		String translated = readStringFromFile("testFiles/newTestInput0.txt");
		assertEquals(original, translated);
	}
	
	@Test
	public void testCompressAndDecompressFileTestinput1(){
		String[] args = {"-encode", "testFiles/testInput1.txt", "testFiles/testOutput1.huff"};
		Huffman.main(args);
		
		String[] args2 = {"-decode", "testFiles/testOutput1.huff", "testFiles/newTestInput1.txt"};
		Huffman.main(args2);
		
		String original = readStringFromFile("testFiles/testInput1.txt");
		String translated = readStringFromFile("testFiles/newTestInput1.txt");
		assertEquals(original, translated);
	}
	
	@Test
	public void testCompressAndDecompressFileCats(){
		String[] args = {"-encode", "testFiles/Cats.txt", "testFiles/Cats.huff"};
		Huffman.main(args);
		
		String[] args2 = {"-decode", "testFiles/Cats.huff", "testFiles/newCats.txt"};
		Huffman.main(args2);
		
		String original = readStringFromFile("testFiles/Cats.txt");
		String translated = readStringFromFile("testFiles/newCats.txt");
		assertEquals(original, translated);
	}
	
} // HuffmanTester
