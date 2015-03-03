import java.util.HashMap;
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
	
		
	public void printOccurrences(){
		Set<Character> keys = chars.keySet();
		System.out.println("char | occurrences");
		for(Character c : keys)
			System.out.println(c + ": " + chars.get(c));
	}
	
	
	//Node
	private static class Node implements Comparable<Node>{
		
		private Character character;
		private int weight;
		private Node leftChild;
		private Node rightChild;
		
		Node(char character, int weight){
			this.character = character;
			this.weight = weight;
			leftChild = null;
			rightChild = null;
		}
		
		Node(int weight, char character, Node leftChild, Node rightChild){
			this.weight = weight;
			this.character = character;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		
		int getWeight(){
			return weight;
		}
		
		void setWeight(int i){
			weight = i;
		}

		@Override
		public int compareTo(Node other){
			return Integer.compare(weight, other.getWeight());
		}
		
	}//Node
	
	
	
	
	public static void main(String [] args){	
		HuffmanCoder theHuff = new HuffmanCoder();
		theHuff.readString("Hello hello, how are you?!");
		theHuff.printOccurrences();
		
	}
	
	

}
