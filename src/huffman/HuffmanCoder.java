package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author Henrik Järnbrand 
 * henrikjarnbrand@gmail.com
 * @author Tomas Sandberg 
 * tomassandberg86@hotmail.com
 */
public class HuffmanCoder {

	private Map<Character,Integer> charCount; 
	private Map<Character,ArrayList<Integer>> charsByteRep; 
	private Queue<Node> pQueue;
	
	private Node latestTree;
	
	public HuffmanCoder(){
		charCount = new HashMap<Character,Integer>();
		pQueue = new PriorityQueue<Node>();
		charsByteRep = new HashMap<Character, ArrayList<Integer>>();
	}
	
	/**
	 * Gets the last Huffman tree that was used for compression.
	 * 
	 * @return
	 * root node of the Huffman tree.
	 */
	public Node getLatestTree(){
		return latestTree;
	}
	
	/**
	 * Takes an input string and counts every chars frequency in that particular string.
	 * After counting, calls createNodes() which creates the nodes that are needed to build tree.
	 * @param text
	 * The string that is to be analyzed.
	 */
	public void readString(String text){
		if(text == null)
			throw new NullPointerException("Input text can't be null");
		if(text.length() == 0)
			throw new IllegalArgumentException("Input text must at least have one character");

		for(int i = 0; i < text.length(); ++i){
			char currentChar = text.charAt(i);
			if(charCount.get(currentChar) != null){
				charCount.put(currentChar, charCount.get(currentChar) + 1);
			}else{
				charCount.put(currentChar, 1);
			}
		}
		createNodes();
	}
	
	
	/**
	 * Counts the number of bytes needed in the encoded array by multiplying
	 * every chars arraylist.size() with the same chars frequency number.
	 * The modulo operation tells us the final size that is needed to fit every byte since
	 * you can't have uneven bits.
	 * 
	 * @return
	 * An int representing the number of bytes needed to store this byte array.
	 */
	private int numbOfBytesToStore(){
		int size = 0;
		
		Set<Character> keys = charsByteRep.keySet();
		for(Character c : keys){ 
			int byteRep = charsByteRep.get(c).size();
			size += ((byteRep == 0)? 1 : byteRep) * charCount.get(c);
		}
			
		int diff = size % 8;
		return (size + (8 - diff)) / 8;
	}
	
	
	/**
	 * Compresses a string using Huffman-encoding. 
	 * @param input
	 * the string to compress.
	 * @return
	 * a byte array representing the string.
	 */
	public byte[] encode(String input){
		readString(input);
		latestTree = buildTree();
		
		byte[] encodedBytes = new byte[numbOfBytesToStore()];
		
		int currentByteIndex = 0;
		int bitsLeft = 7;
		
		for(int i = 0; i < input.length(); ++i){  //loops over chars in input
			char current = input.charAt(i);
			
			ArrayList<Integer> intRep = charsByteRep.get(current);
			for(int j = 0; j < intRep.size(); ++j){ //loops over chars arrayList 
				if(bitsLeft < 0){
					currentByteIndex++;
					bitsLeft = 7;					
				}
				byte b = encodedBytes[currentByteIndex];
				
				if(intRep.get(j) == 1){
					byte mask = 1;
					mask = (byte) (mask << bitsLeft);
					b = (byte) (b | mask);
					encodedBytes[currentByteIndex] = b;
				}
				bitsLeft--;
			}
		}
		return encodedBytes;
	}
	
	
	/**
	 * After the charaters has been counted, createNodes() creates the nodes
	 * that are needed to run part of the Huffman-Algorithm, buildTree().
	 * 
	 */
	private void createNodes(){
		Set<Character> keys = charCount.keySet();
		for(Character k : keys)
			pQueue.add(new Node(k, charCount.get(k)));
	}
	
	
	/**
	 * Only for testing purposes. 
	 * @return 
	 * a copy of the map containing the number of occurrences for each character.
	 */
	protected HashMap<Character, Integer> getOccurrences(){
		return new HashMap<Character, Integer>(charCount);
	}

	
	/**
	 * Builds the tree according to the Huffman algorithm. 
	 * After building tree, calls saveCharByteRep().
	 * @return 
	 * the root of the tree.
	 */
	public Node buildTree(){
		
		while(pQueue.size() != 1){
			Node node1 = pQueue.poll();
			Node node2 = pQueue.poll();
			Node newNode = new Node(null, node1.getWeight() + node2.getWeight(), node1, node2);
			pQueue.add(newNode);
		}
		Node root = pQueue.poll();
		saveCharByteRep(root);
		return root;
	}
	
	/**
	 * Uses the root of the tree in a dfs-search to find the integer representation of every
	 * char in the tree. After search, saves it in the map charsByteRep.
	 * @param root
	 * The root of the tree.
	 */
	public void saveCharByteRep(Node root){
		for(Character c : charCount.keySet()){
			ArrayList<Integer> charByte = dfs(root, c);
			charsByteRep.put(c, charByte);
		}
	}
	
	
	/**
	 * DFS-DepthFirstSearch, Highly ineffective in this scenario, probably should run bfs instead.
	 * 
	 * @param root 
	 * root of the tree, passed after buildTree() has been executed.
	 * @param c 
	 * the char that we want to find the path to.
	 * @return 
	 * An ArrayList<Integer> representing the binary code for the char that is passed as an arg.
	 */
	public ArrayList<Integer> dfs(Node root, char c){
		Set<Node> visited = new HashSet<Node>(); 
		ArrayList<Node> route = new ArrayList<Node>();
		ArrayList<Integer> intRoute = new ArrayList<Integer>();
		
		route.add(root);
		boolean done = false;
		while(!done){
			Node node = route.get(route.size()-1);
			
			if(node.getChar() != null && node.getChar() == c){
				done = true;
			}else{
				visited.add(node);
				
				boolean hasUnvisited = false;
				if(node.getRightChild() != null && !visited.contains(node.getRightChild())){
					route.add(node.getRightChild());
					intRoute.add(1);
					hasUnvisited = true;
				}
				else if(node.getLeftChild() != null && !visited.contains(node.getLeftChild())){
					route.add(node.getLeftChild());
					intRoute.add(0);
					hasUnvisited = true;
				}
				if(!hasUnvisited){
					route.remove(route.size()-1);
					intRoute.remove(intRoute.size()-1);
				}
					
			}
		}
		return intRoute;			
	}//dfs
	
	
	
} // HuffmanCoder

