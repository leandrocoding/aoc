import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Day11 {

	public static void main(String[] args) throws IOException {
		String inputstring = Files.readString(Paths.get("day11.txt"));
		String[] splitted = inputstring.split("((\\n\\r)|(\\r\\n)){2}|(\\r){2}|(\\n){2}");
		for (int i = 0; i < splitted.length; i++) {
			Monkey curMonkey1 = new Monkey(i);
			Monkey curMonkey2 = new Monkey(i);
			String[] monkeyLines = splitted[i].strip().split("\n");
			String[] startingItemsString = monkeyLines[1].strip().split(":");
			String[] startingNumbers = startingItemsString[1].strip().split(",");
			for(int j = 0; j<startingNumbers.length;j++) {
				int worry = Integer.parseInt(startingNumbers[j].strip());
				Item curItem1 = new Item(worry);
				Item curItem2 = new Item(worry);
				curMonkey1.items.add(curItem1);
				curMonkey2.items.add(curItem2);
			}
			curMonkey1.operatorType = monkeyLines[2].strip().split(" ")[4].strip();
			curMonkey2.operatorType = monkeyLines[2].strip().split(" ")[4].strip();
			try {
				curMonkey1.operationValue =Long.parseLong(monkeyLines[2].strip().split(" ")[5]) ;
				curMonkey2.operationValue =Long.parseLong(monkeyLines[2].strip().split(" ")[5]) ;
				
			}catch (Exception e) {
				if(monkeyLines[2].strip().split(" ")[5].strip().equals("old")) {
					
					curMonkey1.operationValue = -1;
					curMonkey2.operationValue = -1;
				}else {
					throw e;
				}
			}
			curMonkey1.testDivisibleBy = Integer.parseInt(monkeyLines[3].strip().split(" ")[3]);
			curMonkey2.testDivisibleBy = Integer.parseInt(monkeyLines[3].strip().split(" ")[3]);
			curMonkey1.toMonkeyIfTrue = Integer.parseInt(monkeyLines[4].strip().split(" ")[5]);
			curMonkey2.toMonkeyIfTrue = Integer.parseInt(monkeyLines[4].strip().split(" ")[5]);
			curMonkey1.toMonkeyIfFalse= Integer.parseInt(monkeyLines[5].strip().split(" ")[5]);
			curMonkey2.toMonkeyIfFalse= Integer.parseInt(monkeyLines[5].strip().split(" ")[5]);
			GameMaster.monkeyList1.add(curMonkey1);
			GameMaster.monkeyList2.add(curMonkey2);
		}
		
		GameMaster.setSCM();
		GameMaster.PlayNRounds1(20);
		System.out.println("Part 1: " + GameMaster.getres(GameMaster.monkeyList1));
		
		GameMaster.PlayNRounds2(10000);
		System.out.println("Part 2: " + GameMaster.getres(GameMaster.monkeyList2));
	}
}
class GameMaster{
	static ArrayList<Monkey> monkeyList1= new ArrayList<>();
	static ArrayList<Monkey> monkeyList2= new ArrayList<>();
	static int SmallestCommonMultiple = Integer.MAX_VALUE;
	
	static void PlayNRounds1(int n) {
		for(int i = 0; i<n;i++) {			
			PlayARound1();
		}
	}
	static void PlayNRounds2(int n) {
		for(int i = 0; i<n;i++) {			
			PlayARound2();
		}
	}
	static void PlayARound1() {
		for( Monkey monk : monkeyList1) {
			monk.inspectAll(false);
		}
	}
	static void PlayARound2() {
		for( Monkey monk : monkeyList2) {
			monk.inspectAll(true);
		}
	}
	static void setSCM(){
		int newSCM = 1;
		for( Monkey monk : monkeyList1) {
			newSCM*=monk.testDivisibleBy;
		}
		SmallestCommonMultiple = newSCM;

	}
	static long getres(ArrayList<Monkey> monkeyList){
		long[] monkeyInspectedNums = new long[monkeyList.size()];
		for(int i = 0; i<monkeyList.size();i++) {
			monkeyInspectedNums[i] = monkeyList.get(i).hasinspected;
		}
		Arrays.sort(monkeyInspectedNums);
		return monkeyInspectedNums[monkeyList.size()-1]*monkeyInspectedNums[monkeyList.size()-2];
	}
}
class Monkey{
	int id;
	Queue<Item> items;
	String operatorType;
	long operationValue;
	int testDivisibleBy;
	int toMonkeyIfTrue;
	int toMonkeyIfFalse;
	long hasinspected;
	Monkey(int id){
		this.id = id;
		this.items = new LinkedList<Item>();
		hasinspected = 0;;
	}
	void inspectAll(boolean part2) {
		ArrayList<Monkey> monkeyList = part2 ? GameMaster.monkeyList2 :  GameMaster.monkeyList1;
		while(!this.items.isEmpty()) {
			Item curItem = this.items.poll();
			if(curItem==null) {
				return;
			}
			curItem.applyOp(this.operatorType, this.operationValue);
			curItem.relief(part2);
			if(curItem.testDevisable(this.testDivisibleBy)) {
				monkeyList.get(this.toMonkeyIfTrue).items.add(curItem);
			}else {
				monkeyList.get(this.toMonkeyIfFalse).items.add(curItem);
			}
			hasinspected++;
		}
	}
	@Override
	public String toString() {
		return super.toString();
	}
}
class Item{
	long worryLevel;
	Item(int worry){
		this.worryLevel = worry;
	}
	long applyOp(String op, long val) {
		if(val == -1) {
			val = this.worryLevel;
		}
		if(op.equals("*")) {
			return this.worryLevel*=val;
			 
		}else if(op.equals("+")){
			return this.worryLevel+=val;
		}else {
			System.out.println("Invalid Operator: " + op);
			throw new RuntimeException("Invalid Operator");
		}
	}
	boolean testDevisable(int divisor) {
		return this.worryLevel%divisor == 0;
	}
	long relief(boolean part2) {
		if(part2) {
			
			return this.worryLevel%=GameMaster.SmallestCommonMultiple;
		}
		return this.worryLevel/=3;
	}
}