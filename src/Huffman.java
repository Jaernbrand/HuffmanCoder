
public class Huffman {
	
	public static void printHelp(){
		System.out.println("Unknown command.\n"+
				"\t -encode [input file] [output file]\n"+
				"\t -decode [input file] [output file]");
	}

	public static void main(String[] args){
		if (args.length < 3 && "-encode".equals(args[0]) ){
			
		} else if (args.length < 3 && "-decode".equals(args[0])){
			
		} else {
			printHelp();
		}
	}
}
