import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Day05 {

	public static void main(String[] args) throws IOException {
		Path filepath = Paths.get("day05.txt");
				
//		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(filepath);
		String inputstring = Files.readString(filepath);
		String[] splitted = inputstring.split("((\\n\\r)|(\\r\\n)){2}|(\\r){2}|(\\n){2}");
		String[] originStacks = splitted[1].split("\\n");
		

		ArrayList<Stack<Character>> crateStacks1 = new ArrayList<Stack<Character>>();
		ArrayList<Stack<Character>> crateStacks2 = new ArrayList<Stack<Character>>();
		
		//Input into Stacks
		for(int i = 0; i<originStacks.length ; i++) {
			crateStacks1.add(i,new Stack<Character>());
			crateStacks2.add(i,new Stack<Character>());
			for(char c : originStacks[i].strip().toCharArray()) {
				
				crateStacks1.get(i).push(c);
				crateStacks2.get(i).push(c);
				
			}
		}
		
		
		//phrasing of Input2
		
		//move 2 from 2 to 8
		
		String[] moveInst = splitted[2].split("\\n");
		
		for(String inst : moveInst) {
			String[] is = inst.split(" ");
			int n = Integer.valueOf(is[1]);
			int f = Integer.valueOf(is[3])-1;
			int t = Integer.valueOf(is[5].strip()) -1;
			ArrayList<Character> tempP2 = new ArrayList<Character>();
			for(int j = 0; j<n; j++) {
				crateStacks1.get(t).push(crateStacks1.get(f).pop());
				tempP2.add(crateStacks2.get(f).pop());

			}
			Collections.reverse(tempP2);
			crateStacks2.get(t).addAll(tempP2);
			
			
			
		}
		
		//get Result
		
		for(Stack<Character> st : crateStacks1) {
			System.out.print(st.peek());
		}
		System.out.println();
		for(Stack<Character> st : crateStacks2) {
			System.out.print(st.peek());
		}

	}

}
