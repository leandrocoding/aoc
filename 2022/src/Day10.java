import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Day10 {
	static String p2 = "";
	public static void draw(int curX, int curCycle) {
		int drawPos = (curCycle-1) % 40; 
		if(drawPos == 0) {
			p2+="\n";
		}
		if(Math.abs(curX-drawPos)<=1) {
			p2+="█";
		}else {
			p2+=" ";
		}
	}
	public static void main(String[] args) throws IOException {
		Path filepath = Paths.get("day10.txt");
//		Path filepath = Paths.get("test.txt");
	
		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(filepath);
		p2 = "";
		Map<Integer, Integer> xRegister = new TreeMap<Integer,Integer>();
		int curx = 1;
		int curCycle = 1;	
		for(String line : inputList) {
			String[] split = line.split(" ");
			if(split[0].equals("noop")) {
				xRegister.put(curCycle,curx);
				draw(curx,curCycle);
				curCycle+=1;
			}else if(split[0].equals("addx")){
				xRegister.put(curCycle,curx);
				
				draw(curx,curCycle);

				curCycle++;
				xRegister.put(curCycle,curx);
				draw(curx,curCycle);
				curCycle++;
				
				curx += Integer.parseInt(split[1]);
				xRegister.put(curCycle,curx);
			}else {
				System.out.println("Invalid Input!");
			}
		}
		int p1 = 0;
		for (int i = 20; i <= 220; i+=40) {
			p1+= xRegister.get(i)*i;
		}
		System.out.println("Part 1: " + p1);
		System.out.println("Part 2: " + p2);
	}
}
