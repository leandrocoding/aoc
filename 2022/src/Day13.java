import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day13 {

	public static void main(String[] args) throws IOException {
		String inputstring = Files.readString(Paths.get("day13.txt"));
		String[] pairs = inputstring.split("((\\n\\r)|(\\r\\n)){2}|(\\r){2}|(\\n){2}");
		
		
		List<Packet> packs = new ArrayList<Packet>();
		
		int i = 1;
		int resp1 = 0;
		for(String pair : pairs) {
			
			String[] pairSplitted = pair.strip().split("\n");
			Packet p1 = new Packet(pairSplitted[0].strip());
			Packet p2 = new Packet(pairSplitted[1].strip());
			packs.add(p1);
			packs.add(p2);
			
			int dif =  p1.compareTo(p2);
			resp1 += dif>0 ? i: 0;
			i++;
			
		}
		System.out.println("Part 1: " + resp1);
		Packet d1 = new Packet("[[2]]");
		Packet d2 = new Packet("[[6]]");
		
		packs.add(d1);
		packs.add(d2);
		
		Collections.sort(packs);
		int i1 = packs.size() - packs.indexOf(d1);
		int i2 = packs.size() - packs.indexOf(d2);
		
		int resp2 =i1 * i2;
		System.out.println("Part 2: " + resp2);
	}
}

class Packet implements Comparable<Packet>{
	int val;
	List<Packet> children;
	boolean isInt;
	String stringRep;
	
	@Override
	public String toString() {
		String res = "";
		if(isInt) return res+= val;
		res+="[";
		for(int i = 0; i<children.size();i++) {
			res += children.get(i).toString() + (i<(children.size()-1)?",":"");
		}
		res += "]";
		return res;
	}
	
	Packet(String pack){
		stringRep = pack;
		children = new ArrayList<>();
		
		if(pack.equals("[]")) {
			val = -1;
		}
		if(pack.charAt(0) != '[') {
			//Must be a Integer 
			
			val = Integer.parseInt(pack);
			isInt = true;
			return;
		}
		if(pack.charAt(0) == '[') {
			//Evaluate all subelements as children / but only one level deep.
			pack = pack.substring(1,pack.length()-1);
			int level = 0;
			String currentElement = "";
			children = new ArrayList<Packet>();
			this.isInt = false;
			for(char c : pack.toCharArray()) {
				if(level == 0 && c==',') {
					children.add(new Packet(currentElement));
					currentElement = "";
				}else {
					currentElement+= c;
					switch(c) {
					case '[': level++;  break;
					case ']': level--; break;
					default: break;
					}
				}
				
			}
			//last element
			if(!currentElement.equals("")) {
				children.add(new Packet(currentElement));
			}
		}
	}
	Packet(int val){
		stringRep = "";
		this.val = val;
		this.isInt = true;
	}
	
	Packet(List<Packet> children){
		stringRep = "";
		this.children = children;
		this.isInt = false;
	}

	@Override
	public int compareTo(Packet o) {
		if(this.isInt && o.isInt) {
			return o.val-this.val;
		}
		
		if(this.isInt != o.isInt){
			Packet newLeft = this.isInt? new Packet("["+ this.val + "]") : this;
			Packet newRight= o.isInt? new Packet("["+ o.val + "]") : o;
			return newLeft.compareTo(newRight);
			
		}
		//no ints
		int minChildren = Math.min(this.children.size(), o.children.size());
		for(int i = 0; i<minChildren ; i++) {
			int dif = this.children.get(i).compareTo(o.children.get(i));
			
			if(dif!=0) return dif;
		}
		return o.children.size() - this.children.size();
	}
}