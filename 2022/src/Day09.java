import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Day09 {
	public static void main(String[] args) throws IOException {
		Path filepath = Paths.get("day09.txt");
//		Path filepath = Paths.get("test.txt");
		
		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(filepath);
		
		int desiredTailLength = 9; 
		Node endOfTail = new Node();
		Node temp = endOfTail;
		for(int i = 0;i<desiredTailLength-1;i++) temp = new Node(temp);
		Node head = new Node(temp);

		for(String line : inputList) {
			String[] split = line.split(" ");
			char dir = split[0].strip().charAt(0);
			int distance = Integer.parseInt(split[1]);
			switch (dir) {
			case 'U':
				head.move(0, - distance);
				break;
			case 'D':
				head.move(0, distance);
				break;
			case 'R':
				head.move(distance,0);
				break;
			case 'L':
				head.move(-distance,0);
				break;
			default:
				break;
			}
		}
		System.out.println("Part 1: " + head.back.visitedCoords.size());
		System.out.println("Part 2: " + endOfTail.visitedCoords.size());
		
	}
}

class Node{
	Node front;
	Node back;
	Set<String> visitedCoords;
	int x;
	int y;
	
	Node(){
		visitedCoords = new TreeSet<String>();
		x = 0;
		y = 0;
		front = null;
		back = null;
		
	}
	Node(Node back){
		this();
		this.back = back; 
		this.back.front = this;
	}
	
	void updateBahind() {
		if(back==null) {
			return;
		}
		back.updatePos();
	}
	void move(int dx, int dy ) {
		x+=dx;
		y+=dy;
		updateBahind();
	}
	void addCoordsToSet(int xx, int yy) {
		visitedCoords.add("x: " + xx + ", y: " + yy );
	}
	void updatePos() {
		addCoordsToSet(x,y);
		if(front==null) return;
		//touching
		if(Math.abs(front.x -x)<=1 && Math.abs(front.y -y)<=1) return;
				
		if (front.x == x) {
			boolean neg = front.y - y < 0;
			int newy = neg ? front.y + 1:front.y-1;
			for(int yy = y; yy!=newy; yy+= neg?-1:+1) {
				addCoordsToSet(x, yy);
			}
			addCoordsToSet(x, newy);
			this.y = newy;	
			updateBahind();
			return;
		}
		if (front.y == y) {
			boolean neg = front.x - x < 0;
			int newx = neg ? front.x + 1:front.x-1;
			for(int xx = x; xx!=newx; xx+= neg?-1:+1) addCoordsToSet(xx, y);
		
			addCoordsToSet(newx, y);
			this.x = newx;	
			updateBahind();
			return;
		}
		
		// No cords are matching..
		//Move diagonal
		
		this.x += front.x>x?1:-1;
		this.y += front.y>y?1:-1;
		addCoordsToSet(x, y);
		updateBahind();
		updatePos();
		return;
	}
}