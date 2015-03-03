
public class HuffmanDecoder {

	public final int BIT_START_POSITION = 7;
	
	private int bitPosition = BIT_START_POSITION;
	
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
		
		for (byte b : input){
			do{
				byte currBit = b & (byte)(1 << bitPosition);
				if (mask == ) ){
					// move in huffTree
				}
					
			} while (bitPosition == BIT_START_POSITION);
		}
		
		return retStr;
	}
	
	private void decrementBitPosition(){
		bitPosition = --bitPosition % 8;
	}
} // HuffmanDecoder
