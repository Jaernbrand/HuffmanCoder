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

/*
	@Test
	pubic void (){
		
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		HuffmanCoder theHuff = new HuffmanCoder();
		theHuff.writeBytes(output);
		String input = "alla gillar rally";
	}
	*/
}
