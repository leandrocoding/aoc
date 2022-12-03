import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Day03 {

	public static void main(String[] args) throws IOException {

		Path filepath = Paths.get("day03.txt");
		
		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(filepath);
		int sump1 = 0;
		int sump2 = 0;

		int i = 0;
		Set<Character> last3 = new HashSet<Character>();

		for(String line:inputList) {
	
			int len = line.length();

			//Part1
			String left = line.substring(0,len/2);
			String right = line.substring(len/2);
			
			
			Set<Character> wegot = new HashSet<Character>();
			for(char c: left.toCharArray()) {
				wegot.add(c);
				
			}
			for(char c: right.toCharArray()) {
				if(wegot.contains(c)) {
					int asci = ((int) c);
					
					int value =  Character.isUpperCase(c) ? asci-38 : asci-96;
					sump1+= value;
					break;
					
				}
			}
			//Part2:
			if(i==0) {
				last3.clear();
				for(char c: line.toCharArray()) {
					last3.add(c);
				}
			}else {
				Set<Character> curli = new HashSet<Character>();

				for(char carrr : line.toCharArray()) {
					curli.add(carrr);
				}
				
				last3.retainAll(curli);
			}
				
			if(i==2) {
				char cc = (char) last3.toArray()[0];
				int asci = ((int) cc);
				
				int value =  Character.isUpperCase(cc) ? asci-38 : asci-96;
				sump2+= value;
			}
			
			i++;
			i%=3;
		}
		System.out.println("Part 1: " + sump1);
		System.out.println("Part 2: " + sump2);

		
		

	}

}
