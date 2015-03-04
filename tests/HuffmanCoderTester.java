import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;
import java.util.LinkedList;


import huffman.*;

public class HuffmanCoderTester {
	
	private HuffmanCoder dustin = new HuffmanCoder();
	
	@Test
	public void testDfsTreePathForChars(){
		
		dustin.readString("alla gillar rally");
		Node root = dustin.buildTree();
		
		LinkedList<Integer> repForCharA = dustin.dfs(root, 'a');
		LinkedList<Integer> oracleForA = new LinkedList<Integer>(Arrays.asList(1,0));		
		for(int i = 0; i < repForCharA.size(); ++i)
			assertEquals(oracleForA.get(i),repForCharA.get(i));
		
		LinkedList<Integer> repForCharI = dustin.dfs(root, 'i');
		LinkedList<Integer> oracleForI = new LinkedList<Integer>(Arrays.asList(0,0,1,1));
		for(int i = 0; i < repForCharA.size(); ++i)
			assertEquals(oracleForI.get(i),repForCharI.get(i));
		
		LinkedList<Integer> repForCharG = dustin.dfs(root, 'g');
		LinkedList<Integer> oracleForG = new LinkedList<Integer>(Arrays.asList(0,0,1,0));
		for(int i = 0; i < repForCharG.size(); ++i)
			assertEquals(oracleForG.get(i),repForCharG.get(i));
		
	}//testDfsTreePath
	
	
}



