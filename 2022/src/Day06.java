import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

public class Day06 {
	public static void main(String[] args) throws IOException {
		Path filepath = Paths.get("day06.txt");
		
		String inputString= Files.readString(filepath);
		char[] inputChars = inputString.toCharArray();
		boolean p1 = false;
		boolean p2 = false;
		
		for(int i = 0; i<inputChars.length-13;i++) {
			HashSet<Character> hs= new HashSet<>();
			HashSet<Character> hs2= new HashSet<>();

			for(int j = 0; j<4;j++) {
				hs.add(inputChars[i+j]);
			}
			for(int j = 0; j<14;j++) {
				hs2.add(inputChars[i+j]);
			}
			if(hs.size()==4 && !p1) {
				System.out.println("Part 1: " + (i+4));
				p1 = true;
			}
			if(hs2.size()==14 && !p2) {
				System.out.println("Part 2: " + (i+14));
				p2 = true;
			}
			hs.clear();
			hs2.clear();
			
			
		}
		
	}

}
