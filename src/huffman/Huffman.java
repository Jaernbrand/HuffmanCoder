
package huffman;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Compresses or decompresses textfiles using Huffman coding.
 * 
 * -encode [input file] [output file]
 * 		Compresses the inputfile and saves the compressed file as output file.
 * 
 * -decode [input file] [output file]
 * 		Decompresses the inputfile and saves the compressed file as output file.
 * 
 * @author Henrik Järnbrand 
 * henrikjarnbrand@gmail.com
 * @author Tomas Sandberg 
 * tomassandberg86@hotmail.com
 */
public class Huffman {
	
	private static final int TREE_INDEX = 0;
	private static final int BYTES_INDEX = 1;
	
	private static String readStringFromFile(String fileName){
		StringBuilder builder = new StringBuilder();
		
		BufferedReader br = null;
		try{
			FileReader fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			int tmpChar = br.read();
			while(tmpChar != -1){
				builder.append( (char)tmpChar );
				tmpChar = br.read();
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
		return builder.toString();
	} // readStringFromFile
	
	private static void printObjectList(ArrayList<Object> objList, String fileName){
		ObjectOutputStream oos = null;
		try{
			FileOutputStream fo = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fo);
			
			oos.writeObject(objList);
			
		} catch (IOException e){
			e.printStackTrace();
			
		} finally {
			try {
				oos.close();

			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
	} // printObjectList
	
	private static void printStringToFile(String output, String outputFile){
		
		BufferedWriter bw = null;
		try{
			FileWriter fw = new FileWriter(outputFile);
			bw = new BufferedWriter(fw);
			
			bw.write( output, 0, output.length() );
			
		} catch (IOException e){
			e.printStackTrace();
			
		} finally {
			try {
				bw.close();

			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
	} // printStringToFile
	
	private static ArrayList<Object> readObjectList(String fileName){
		ArrayList<Object> savedList = null;
		
		ObjectInputStream ois = null;
		try{
			FileInputStream fi = new FileInputStream(fileName);
			ois = new ObjectInputStream(fi);
			
			Object tmpObj = ois.readObject();
			savedList = (ArrayList<Object>)tmpObj;
			
		} catch (IOException e){
			e.printStackTrace();
			
		} catch (ClassNotFoundException cne){
			cne.printStackTrace();
		} catch (ClassCastException cce){
			System.err.println("Could not read from file.");
			cce.printStackTrace();
			
		} finally {
			try {
				ois.close();

			} catch (IOException ex){
				ex.printStackTrace();
			}
		}
		
		return savedList;
	} // readObjectList
	
	private static void encode(String inputFile, String outputFile){
		HuffmanCoder huff = new HuffmanCoder();
		
		String input = readStringFromFile(inputFile);
		byte[] output = huff.encode(input);
		
		ArrayList<Object> objList = new ArrayList<Object>();
		objList.add(TREE_INDEX, huff.getLatestTree() );
		objList.add(BYTES_INDEX, output);
		
		printObjectList(objList, outputFile);
	} // encode
	
	private static void decode(String inputFile, String outputFile){
		HuffmanDecoder deHuff = new HuffmanDecoder();
		
		List<Object> huffObjects = readObjectList(inputFile);
		
		Node huffTree = null; 
		byte[] input = null;
		try {
			huffTree = (Node)huffObjects.get(TREE_INDEX);
			input = (byte[])huffObjects.get(BYTES_INDEX);
			
		} catch(ClassCastException cce){
			System.err.println("Data saved in unknwon format.");
			cce.printStackTrace();
		}
		
		deHuff.setHuffTreeRoot(huffTree);
		String output = deHuff.decode(input);
		printStringToFile(output, outputFile);
	} // decode
	
	private static void printHelp(){
		System.out.println("Unknown command.\n"+
				"\t -encode [input file] [output file]\n"+
				"\t -decode [input file] [output file]");
	} // printHelp

	public static void main(String[] args){
		if (args.length == 3 && "-encode".equals(args[0]) ){
			encode(args[1], args[2]);
		} else if (args.length == 3 && "-decode".equals(args[0])){
			decode(args[1], args[2]);
		} else {
			printHelp();
		}
	} // main
} // Huffman
