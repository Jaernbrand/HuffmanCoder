
package huffman;

import java.util.Map;
import java.util.HashMap;

/**
 * Decompresses files that have been compressed by HuffmanCoder.
 * 
 * @author Henrik Järnbrand 
 * henrikjarnbrand@gmail.com
 * @author Tomas Sandberg 
 * tomassandberg86@hotmail.com
 */
public class HuffmanDecoder {

	public final int BIT_START_POSITION = 7;
	
	private int bitPosition;
	private Node huffTreeRoot;
	private Node currentNode;
	private long charQuantity;
	
	/**
	 * Sets the root of the Huffman tree used for decompression.
	 * 
	 * @param root
	 * the root of the new Hufman tree.
	 */
	public void setHuffTreeRoot(Node root){
		if (root == null){
			throw new NullPointerException("root can't be null.");
		}
		
		huffTreeRoot = root;
	}
	
	/**
	 * Decompresses an encoded byte array using Huffman-decoding. 
	 * @param input
	 * the byte array to decompress.
	 * @return
	 * the decompressed original string.
	 */
	public String decode(byte[] input){
		if (huffTreeRoot == null){
			throw new NullPointerException("The Huffman root has not been set.");
		}
		if (input == null){
			throw new NullPointerException("input == null");
		}
		
		charQuantity = huffTreeRoot.getWeight();
		StringBuilder decompressed = decompress(input);
		
		return decompressed.toString();
	}
	
	private StringBuilder decompress(byte[] input){
		StringBuilder builder = new StringBuilder();
		String tmpStr = null;
		
		bitPosition = BIT_START_POSITION;
		currentNode = huffTreeRoot;
		
		for (int i=0; i < input.length && charQuantity > 0; ++i){
			do{
				byte currBit = (byte)( input[i] & (1 << bitPosition) );

				tmpStr = searchNextNode(currBit); // move in huffTree
				if (tmpStr != null){
					builder.append(tmpStr);
				}
				
				decrementBitPosition();
			} while (bitPosition != BIT_START_POSITION && charQuantity > 0);
		}
		
		return builder;
	}
	
	private void decrementBitPosition(){
		--bitPosition;
		if (bitPosition < 0){
			bitPosition = BIT_START_POSITION;
		}
	}
	
	/**
	 * TODO
	 * @param currBit
	 * @return
	 */
	private String searchNextNode(byte currBit){
		// TODO Root node behaves different. We need to check the root node for value if its the only node.
		
		if (currBit != 0){
			currentNode = currentNode.getRightChild();
		} else if (currBit == 0 && huffTreeRoot.getLeftChild() != null){
			currentNode = currentNode.getLeftChild();
		}
	
		String str = null;
		if (currentNode.getChar() != null){
			str = currentNode.getChar().toString();
			charQuantity--;
			currentNode = huffTreeRoot;
		}
		
		return str;
	}
	
} // HuffmanDecoder
