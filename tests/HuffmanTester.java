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
	
	/* Testet verkar inte längre fylla någon funktion.
	@Test
	public void testBytesAreCorrect(){
		String[] args = {"-encode", "testFiles/testInput0.txt", "testFiles/testOutput0.huff"};
		Huffman.main(args);
		
		// l = 1, a = 01, s = 00
		byte[] oracle = {0x7C, 0x20};
		byte[] huffArr = readByteArray("testFiles/testOutput0.huff");
		
		assertEquals(oracle.length, huffArr.length);
		for (int i=0; i < oracle.length; ++i){
			assertEquals(oracle[i], huffArr[i]);
		}
	} // testBytesAreCorrect
	*/
	
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
	
	@Test(expected=IllegalArgumentException.class)
	public void testCompressAndDecompressFileOneNewline(){
		String[] args = {"-encode", "testFiles/OneNewLine.txt", "testFiles/OneNewLine.huff"};
		Huffman.main(args);
		
		String[] args2 = {"-decode", "testFiles/OneNewLine.huff", "testFiles/newOneNewLine.txt"};
		Huffman.main(args2);
		
		String original = readStringFromFile("testFiles/OneNewLine.txt");
		String translated = readStringFromFile("testFiles/newOneNewLine.txt");
		assertEquals(original, translated);
	}
	
	@Test
	public void testCompressAndDecompressFileMultipleNewlines(){
		String[] args = {"-encode", "testFiles/MultipleNewLines.txt", "testFiles/MultipleNewLines.huff"};
		Huffman.main(args);
		
		String[] args2 = {"-decode", "testFiles/MultipleNewLines.huff", "testFiles/newMultipleNewLines.txt"};
		Huffman.main(args2);
		
		String original = readStringFromFile("testFiles/MultipleNewLines.txt");
		String translated = readStringFromFile("testFiles/newMultipleNewLines.txt");
		assertEquals(original, translated);
	}
	
	@Test
	public void testCompressAndDecompressFileOnlyOneLetter(){
		String[] args = {"-encode", "testFiles/OnlyOneLetter.txt", "testFiles/OnlyOneLetter.huff"};
		Huffman.main(args);
		
		String[] args2 = {"-decode", "testFiles/OnlyOneLetter.huff", "testFiles/newOnlyOneLetter.txt"};
		Huffman.main(args2);
		
		String original = readStringFromFile("testFiles/OnlyOneLetter.txt");
		String translated = readStringFromFile("testFiles/newOnlyOneLetter.txt");
		assertEquals(original, translated);
	}
	
} // HuffmanTester
