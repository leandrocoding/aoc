import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

public class Day12 {
	static int height;
	static int breath;

	public static void main(String[] args) throws IOException {
		Path filepath = Paths.get("day12.txt");
//		Path filepath = Paths.get("test.txt");
	
		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(filepath);
		int[] startingPos =  new int[2];
		int[] endPos =  new int[2];
		
		height = inputList.size();
		breath = inputList.get(0).length();
		for (int r = 0; r<height;r++) {
			for (int c = 0; c < breath; c++) {
				if(inputList.get(r).charAt(c)=='S') {
					startingPos[0] = r;
					startingPos[1] = c;
				}
				if(inputList.get(r).charAt(c)=='E') {
					endPos[0] = r;
					endPos[1] = c;
				}		
			}
		}
		System.out.println("Part 1: " + bfs(startingPos, endPos, inputList, false));
		System.out.println("Part 2: " + bfs(endPos, null, inputList, true));	
	}
	static int bfs( int[] startingPos, int[] endPos, ArrayList<String> inputList, boolean inverse) {
		
		boolean visited[][] = new boolean[height][breath];
		int[] curPos = {startingPos[0], startingPos[1], 0};
		LinkedList<int[]> q = new LinkedList<>();
		
		int[][] leFourDirections = {{1,0},{-1,0},{0,1},{0,-1}};
		q.add(curPos);
		while(!q.isEmpty()) {
			curPos = q.pop();
			if(visited[curPos[0]][curPos[1]]) {
				continue;
			}
			visited[curPos[0]][curPos[1]] = true;
			for(int[] dir : leFourDirections) {
				int nextR = curPos[0] + dir[0]; 
				int nextC = curPos[1] + dir[1];
				if(!(nextR<height && nextR >=0 && nextC<breath && nextC >=0)) {
					continue;
				}
				int curCharVal = (int)inputList.get(curPos[0]).charAt(curPos[1]);
				int nextCharVal = (int) inputList.get(nextR).charAt(nextC);
				if(curCharVal == 83) {
					curCharVal  = 97;
				}
				if(nextCharVal == 69) {
					nextCharVal = 122;
				}
				if(nextCharVal == 83) {
					nextCharVal  = 97;
				}
				if(curCharVal == 69) {
					curCharVal = 122;
				}
				//Part 1
				if(curCharVal>=nextCharVal-1 && !inverse) {
					if(nextR == endPos[0] && nextC == endPos[1]) return curPos[2] + 1; // FOUND
					int [] next = {nextR,nextC,curPos[2] + 1};
					if(!visited[nextR][nextC]) {
						q.add(next);
					}
				}
				//Part 2
				if(nextCharVal>=curCharVal-1 && inverse) {
					if(nextCharVal == 97) return curPos[2] + 1;
					int [] next = {nextR,nextC,curPos[2] + 1};
					if(!visited[nextR][nextC]) {
						q.add(next);
					}
				}
			}
		}
		return -1; //Not found
	}
}