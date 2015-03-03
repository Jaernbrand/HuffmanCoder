import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

import org.junit.Test;


public class HuffmanTester {

	@Test
	public void testToFewMainArgs(){
		String[] args = {"-decode", "test.txt"};
		Huffman.main(args);
		// TODO
	}

	@Test
	public void testBytesAreCorrect(){
		String[] args = {"-encode", "testFiles/testInput1.txt", "testFiles/testOutput.txt"};
		Huffman.main(args);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		HuffmanCoder theHuff = new HuffmanCoder();
		// TODO
		//theHuff.writeBytes(output);
		String input = "alla gillar rally";
		fail();
	}
}
