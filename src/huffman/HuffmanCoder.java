package huffman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class HuffmanCoder {

	private Map<Character,Integer> charCount; 
	private Map<Character,ArrayList<Integer>> charsByteRep; 
	private Queue<Node> pQueue; 
	
	public HuffmanCoder(){
		charCount = new HashMap<Character,Integer>();
		pQueue = new PriorityQueue<Node>();
		charsByteRep = new HashMap<Character, ArrayList<Integer>>();
	}
	
	public void readString(String text){
		if(text == null)
			throw new NullPointerException("Input text can't be null");
		if(text.length() == 0)
			throw new IllegalArgumentException("Input text must at least have one character");

		for(int i = 0; i < text.length(); ++i){
			char currentChar = text.charAt(i);
			if(charCount.get(currentChar) != null){ 			//snabbare än containsKey
				charCount.put(currentChar, charCount.get(currentChar) + 1);
			}else{
				charCount.put(currentChar, 1);
			}
		}
		createNodes();
		
	}
	
	/**
	 * Compresses a string using Huffman-encoding. 
	 * @param input
	 * the string to compress.
	 * @return
	 * a byte array representing the string.
	 */
	public byte[] encode(String input){
		// TODO
		return null;
	}
	
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
		
	private void printOccurrences(){
		Set<Character> keys = charCount.keySet();
		System.out.println("char | occurrences");
		for(Character c : keys)
			System.out.println(c + ": " + charCount.get(c));
	}
	
	public Node buildTree(){
		
		while(pQueue.size() != 1){
			Node node1 = pQueue.poll();
			Node node2 = pQueue.poll();
			Node newNode = new Node(null, node1.getWeight() + node2.getWeight(), node1, node2);
			pQueue.add(newNode);
		}
		
		return pQueue.poll();
	}
	
	
	
	public void saveCharByteRep(Node root){
		for(Character c : charCount.keySet()){
			ArrayList<Integer> charByte = dfs(root, c);
			charsByteRep.put(c, charByte);
		}
	}
	
	
	public ArrayList<Integer> dfs(Node root, char c){
		Set<Node> visited = new HashSet<Node>(); //Keeps track of visited
		ArrayList<Node> route = new ArrayList<Node>();//List to return
		ArrayList<Integer> intRoute = new ArrayList<Integer>();//int representation of way
		
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

