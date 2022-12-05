import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Day05 {

	public static void main(String[] args) throws IOException {
		String inputstring = Files.readString(Paths.get("day05.txt"));
		String[] splitted = inputstring.split("((\\n\\r)|(\\r\\n)){2}|(\\r){2}|(\\n){2}");
		
		int numberOfStacks = splitted[0].split("\\n")[0].toCharArray().length/4;
		
		ArrayList<Stack<Character>> crateStacks1 = new ArrayList<Stack<Character>>();
		ArrayList<Stack<Character>> crateStacks2 = new ArrayList<Stack<Character>>();
		
		//Input into Stacks n Stacks
		for(int i = 0; i<numberOfStacks ; i++) {
			crateStacks1.add(i,new Stack<Character>());
			crateStacks2.add(i,new Stack<Character>());
		}
		
		for(String line: splitted[0].split("\\n")) {
			for(int i = 0; i<numberOfStacks;i++) {
				int charpos = 1+4*i;
				if(line.charAt(charpos)!=' ') {
					crateStacks1.get(i).add(0,line.charAt(charpos));
					crateStacks2.get(i).add(0,line.charAt(charpos));
					
				}
			}
		}
		
		String[] moveInst = splitted[1].split("\\n");
		
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
		System.out.print("Part 1: ");
		for(Stack<Character> st : crateStacks1) {
			System.out.print(st.peek());
		}
		System.out.println();
		System.out.print("Part 2: ");
		for(Stack<Character> st : crateStacks2) {
			System.out.print(st.peek());
		}

	}

}
