import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Day14 {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(Paths.get("day14.txt"));

		
		Universe2 unip1 = new Universe2();
		Universe2 unip2 = new Universe2();
		for(String line: inputList) {
			String splitted[] = line.split(" -> ");
			for(int i = 1; i<splitted.length; i++) {
				String[] coordsLeft = splitted[i-1].split(",");
				String[] coordsRight = splitted[i].split(",");
				
				unip1.addStoneLine(Integer.parseInt(coordsLeft[0]),Integer.parseInt(coordsLeft[1]),Integer.parseInt(coordsRight[0]), Integer.parseInt(coordsRight[1]));
				unip2.addStoneLine(Integer.parseInt(coordsLeft[0]),Integer.parseInt(coordsLeft[1]),Integer.parseInt(coordsRight[0]), Integer.parseInt(coordsRight[1]));
			}
		}
		for(int i = 0; i<unip2.obs.length; i++) {
			unip2.obs[i][unip2.maxy+2] = 1;
		}
		unip2.maxy = unip2.maxy+3;
		int p1 = unip1.moveAllSand();
		System.out.println("Part 1: " + (p1));
		int p2 = unip2.moveAllSand();
		System.out.println("Part 2: " + (p2+1));
		
//		Files.writeString(Paths.get("day14.out"),unip2.toString());
		
	}

}

class Universe2 {
	int maxy = 0;
	int minx = Integer.MAX_VALUE;
	int maxx = 0;
	int[][] obs;
	@Override
	public String toString() {
		String res = "";
		for(int y = 0; y<=maxy+1 ; y++) {
			res += y + "\t";
			for(int x = minx-3; x<=maxx+3; x++) {
				if(obs[x][y]==1) {
					res+="#";
				}else if(obs[x][y] == 10){
					res+="o";
				}else {
					res+=".";
				}
			}
			res+= "\n";
		}
		return res;
	}
	Universe2(){
		obs  = new int[1000][1000];
	}
	boolean moveSandStep(Sand curSand) {
		
		if(obs[curSand.x][curSand.y+1]==0){
			curSand.y += 1;
			return true;	
		}
		if(obs[curSand.x-1][curSand.y+1]==0){
			curSand.y += 1;
			curSand.x += -1;
			return true;	
		}
		if(obs[curSand.x+1][curSand.y+1]==0){
			curSand.y += 1;
			curSand.x += 1;
			return true;	
		}
		
		
		return false;
	}
	
	boolean moveOneSand() {
		Sand curSand = new Sand(500, 0);
		while(moveSandStep(curSand)){
			if(curSand.y>maxy+10) {
				//Sand falls down forever
				return false;
			}
		}
		if(curSand.x>maxx) maxx = curSand.x;
		if(curSand.x<minx) minx = curSand.x;
		obs[curSand.x][curSand.y] = 10;
		
		return true;
	}
	int moveAllSand() {
		int res = 0;
		while(moveOneSand()&&obs[500][0] == 0) {
			res++;
		}
		return res;
	}

	void addStoneLine(int x1, int y1, int x2, int y2 ) {

		if(!(x1 == x2||y1 == y2)) {
			System.out.println("OHH NOOOO");
			throw new IllegalArgumentException("either x same or y");
			
		}
		addStone(x1, y1);
		addStone(x2, y2);
		if(x1 == x2) {
			int normDif = y1<y2?1:-1; 
			for(int yi = y1; yi!=y2; yi+=normDif) {
				addStone(x1, yi);
			}
		}else {
			int normDif = x1<x2?1:-1; 
			for(int xi = x1; xi!=x2; xi+=normDif) {
				addStone(xi, y1);
			}
		}
	}
	boolean addStone(int x, int y){
		if(y>maxy) maxy = y;
		if(x>maxx) maxx = x;
		if(x<minx) minx = x;
		obs[x][y] = 1;
		return true;
	}
}
class Sand{
	int x;
	int y;
	Sand(int x, int y){
		this.x = x;
		this.y = y;
	}
}