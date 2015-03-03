
public class HuffmanDecoder {

	public final int BIT_START_POSITION = 7;
	
	private int bitPosition = BIT_START_POSITION;
	private Node huffTree;
	
	/**
	 * Decompresses a encoded byte array using Huffman-decoding. 
	 * @param input
	 * the byte array to decompress.
	 * @return
	 * the decompressed original string.
	 */
	public String decode(byte[] input){
		// TODO
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
			} while (bitPosition == BIT_START_POSITION);
		}
		
		return retStr;
	}
	
	private void decrementBitPosition(){
		bitPosition = --bitPosition % 8;
	}
	
	private String searchNextNode(byte currBit){
		// TODO Take one step along the correct path and check if the new node is a leaf.
		// Return the character in the leaf or null if the node isn't a leaf.
		return null;
	}
	
} // HuffmanDecoder
