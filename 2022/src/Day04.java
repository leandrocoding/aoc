import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day04 {

	public static void main(String[] args) throws IOException {
		Path filepath = Paths.get("day04.txt");
//		Path filepath = Paths.get("test.txt");
		
		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(filepath);
		int counter1 = 0;
		int counter2 = 0;
		for(String line : inputList) {
			String[] lineSplitt = line.split(",");
			String[][] doubleSplitt = {lineSplitt[0].split("-") ,lineSplitt[1].split("-")};
			int leftmin = Integer.valueOf(doubleSplitt[0][0]);
			int leftmax = Integer.valueOf(doubleSplitt[0][1]);
			int rightmin = Integer.valueOf(doubleSplitt[1][0]);
			int rightmax = Integer.valueOf(doubleSplitt[1][1]);
			
			//Part 1
			if(leftmin>=rightmin && leftmax <=rightmax) {
				counter1++;
			}else if(rightmin>=leftmin && rightmax <=leftmax) {
				counter1++;
			}
			
			//Part 2
			
			if(leftmax>=rightmin && leftmin<=rightmax) {
				counter2++;
			}else if(rightmax>=leftmin && rightmin <=leftmax) {
				counter2++;
			}
			
		}
		System.out.println("Part 1: " + counter1);
		System.out.println("Part 2: " + counter2);


	}

}
