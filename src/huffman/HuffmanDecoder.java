
package huffman;

import java.util.Map;
import java.util.HashMap;

public class HuffmanDecoder {

	public final int BIT_START_POSITION = 7;
	
	private int bitPosition = BIT_START_POSITION;
	private Node huffTreeRoot;
	private Node currentNode;
	private long charQuantity;
	
	/**
	 * Decompresses a encoded byte array using Huffman-decoding. 
	 * @param input
	 * the byte array to decompress.
	 * @return
	 * the decompressed original string.
	 */
	public String decode(byte[] input){
		// TODO set charQuantity till huffTreeRoot.getWeight();
		return null;
	}
	
	private String decompress(byte[] input){
		String retStr = "";
		String tmpStr = null;
		
		for (byte b : input){
			do{
				byte currBit = (byte)( b & (1 << bitPosition) );

				tmpStr = searchNextNode(currBit);// move in huffTree
				
				decrementBitPosition();
			} while (bitPosition != BIT_START_POSITION);
		}
		
		return retStr;
	}
	
	private void decrementBitPosition(){
		bitPosition = --bitPosition % 8;
	}
	
	private String searchNextNode(byte currBit){
		// TODO Take one step along the correct path and check if the new node is a leaf.
		// Return the character in the leaf or null if the node isn't a leaf.
		
		// TODO Root node behaves different. We need to check the root nod for value if its the only node.
		if (currBit != 0){
			currentNode = currentNode.getRightChild();
		} else if (currBit == 0){
			currentNode = currentNode.getLeftChild();
		}
		
		String str = null;
		if (currentNode.getChar() != null){
			str = currentNode.getChar().toString();
			charQuantity--;
		}
		
		return str;
	}
	
} // HuffmanDecoder
