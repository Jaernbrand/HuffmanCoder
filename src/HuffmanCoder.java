import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class HuffmanCoder {

	private Map<Character,Integer> chars = new HashMap<Character,Integer>(); 
	private Queue<Node> pQueue = new PriorityQueue<Node>(); 
	
	public void readString(String text){
		if(text == null)
			throw new NullPointerException("Input text can't be null");
		if(text.length() == 0)
			throw new IllegalArgumentException("Input text must at least have one character");

		for(int i = 0; i < text.length(); ++i){
			char currentChar = text.charAt(i);
			if(chars.get(currentChar) != null){ 			//snabbare än containsKey
				chars.put(currentChar, chars.get(currentChar) + 1);
			}else{
				chars.put(currentChar, 1);
			}
		}
		
	}
	
	private void createNodes(){
		Set<Character> keys = chars.keySet();
		for(Character k : keys)
			pQueue.add(new Node(k, chars.get(k)));
	}
	
	protected HashMap<Character, Integer> getOccurrences(){
		return new HashMap<Character, Integer>(chars);
	}
		
	private void printOccurrences(){
		Set<Character> keys = chars.keySet();
		System.out.println("char | occurrences");
		for(Character c : keys)
			System.out.println(c + ": " + chars.get(c));
	}
	
	private Node buildTree(){
		
		while(pQueue.size() != 1){
			Node node1 = pQueue.poll();
			Node node2 = pQueue.poll();
			Node newNode = new Node(null, node1.getWeight() + node2.getWeight(), node1, node2);
			pQueue.add(newNode);
		} 
		return pQueue.poll();
	}
	
	
	//Node
	private static class Node implements Comparable<Node>{
		
		
		private Character character;
		private int weight;
		private Node leftChild;
		private Node rightChild;
		
		Node(Character car, int weight){
			character = car;
			this.weight = weight;
			leftChild = null;
			rightChild = null;
		}
		
		Node(Character car, int weight, Node leftChild, Node rightChild){
			this.weight = weight;
			this.character = car;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		
		int getWeight(){
			return weight;
		}
		
		void setWeight(int i){
			weight = i;
		}
		
		char getChar(){
			return character;
		}

		void setChar(char c){
			character = c;
		}
		
		Node getRightChild(){
			return rightChild;
		}
		
		void setRightChild(Node right){
			rightChild = right;
		}
		
		Node getLeftChild(){
			return leftChild;
		}
		
		void setLeftChild(Node left){
			leftChild = left;
		}

		@Override
		public int compareTo(Node other){
			return weight - other.getWeight();
		}
		
	}//Node
} // HuffmanCoder

