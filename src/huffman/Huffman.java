
public class Huffman {
	
	
	private static void encode(String inputFile, String outputFile){
		HuffmanCoder huff = new HuffmanCoder();
		huff.encode();
	}
	
	private static void decode(String inputFile, String outputFile){
		HuffmanDecoder deHuff = new HuffmanDecoder();
		deHuff.decode();
	}
	
	private static void printHelp(){
		System.out.println("Unknown command.\n"+
				"\t -encode [input file] [output file]\n"+
				"\t -decode [input file] [output file]");
	}

	public static void main(String[] args){
		if (args.length < 3 && "-encode".equals(args[0]) ){
			encode(args[1], args[2]);
		} else if (args.length < 3 && "-decode".equals(args[0])){
			decode(args[1], args[2]);
		} else {
			printHelp();
		}
	} // main
} // Huffman
