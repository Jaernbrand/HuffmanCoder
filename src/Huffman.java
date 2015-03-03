import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Huffman {
	
	private static String readText(String fileName){
		String returnStr = "";
		
		BufferedReader br = null;
		try{
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			String tmpStr = br.readLine();
			while(tmpStr != null){
				returnStr += br.readLine();
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
		
		return returnStr;
	}
	
	private static void encode(String inputFile, String outputFile){
		HuffmanCoder huff = new HuffmanCoder();
		huff.encode( readText(inputFile) );
	}
	
	private static void decode(String inputFile, String outputFile){
		HuffmanCoder huff = new HuffmanCoder();
		// TODO
		// huff.decode();
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
