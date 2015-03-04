
package huffman;

/**
 * Decompresses bytes that have been compressed by HuffmanCoder.
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
	
	/**
	 * Auxiliary method for decode(byte[] input).
	 * 
	 * Does the actual decompression of the input-array. 
	 * 
	 * @param input
	 * a byte array containing Huffman-encoded bytes.
	 * 
	 * @return
	 * StringBuilder containing the decompressed input-array.
	 */
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
	
	/**
	 * Decrements bitPosition by one and sets it to BIT_START_POSITION if 
	 * bitPosition is less than zero.
	 */
	private void decrementBitPosition(){
		--bitPosition;
		if (bitPosition < 0){
			bitPosition = BIT_START_POSITION;
		}
	}
	
	/**
	 * Takes one step along one edge in the Huffman tree and returns the 
	 * equivalent character as a string if one exists. The step is based on the 
	 * supplied byte, which only should represent a masked bit. 
	 * 
	 * @param currBit
	 * the bitmask to
	 *   
	 * @return
	 * the string representation of the original decompressed character or null
	 * if a representation doesn't exist.
	 */
	private String searchNextNode(byte currBit){
		
		if (currBit != 0){
			currentNode = currentNode.getRightChild();
		
		// The tree will only consist of one node if the the original text 
		// consisted of solely one type of character.
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
