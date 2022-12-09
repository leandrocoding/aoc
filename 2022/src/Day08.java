import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Day08 {
	public static void main(String[] args) throws IOException {
		Path filepath = Paths.get("day08.txt");
		int p2 = 0; 

		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(filepath);
		int n = inputList.size(), m = inputList.get(0).length();
		boolean[][] visable = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			
			int height1 = -1;
			int height2 = -1;
			int height3 = -1;
			int height4 = -1;
			for (int j = 0; j<m ; j++) {
				
				//part 1

				int curHeight1 =(int) inputList.get(i).charAt(j)- 48;
				if(curHeight1>height1) {
					visable[i][j] = true;
					height1 = curHeight1;
				}
				int curHeight2 =(int) inputList.get(i).charAt(m- 1 - j)- 48;
				if(curHeight2>height2) {
					visable[i][m-1-j] = true;
					height2 = curHeight2;
				}
				int curHeight3 =(int) inputList.get(j).charAt(i)- 48;
				if(curHeight3>height3) {
					visable[j][i] = true;
					height3 = curHeight3;
				}
				int curHeight4 =(int) inputList.get(n-1-j).charAt(i)- 48;
				if(curHeight4>height4) {
					visable[n-1-j][i] = true;
					height4 = curHeight4;
				}
				//Part2
				
			
				
				int myheight = (int) inputList.get(i).charAt(j)- 48;
				int c1 = 0,c2 = 0,c3 = 0, c4 = 0;
				
				
				//going up
				
				for(int ii = i-1; ii>=0; ii--) {
					c1++;
					if(inputList.get(ii).charAt(j)- 48>=myheight) {
						break;
					}
				}
				//going down
				
				for(int ii = i+1; ii<n; ii++) {
					c2++;
					if(inputList.get(ii).charAt(j)- 48>=myheight) {
						break;
					}
				}
				//going right
				
				for(int jj = j-1; jj>=0; jj--) {
					c3++;
					if(inputList.get(i).charAt(jj)- 48>=myheight) {
						break;
					}
				}
				//going left
				
				for(int jj = j+1; jj<m; jj++) {
					c4++;
					if(inputList.get(i).charAt(jj)- 48>=myheight) {
						break;
					}
				}
				
				int newp2 =c1 * c2 * c3 * c4 ;
				
				p2 = newp2>p2 ? newp2 : p2;
	
			}
		}
		int p1 = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j<m ; j++) {
				if(visable[i][j]) p1++;
			}	
		}
		
		System.out.println("Part 1: " + p1);
		System.out.println("Part 2: " + p2);
	}
}
