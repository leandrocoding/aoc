import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Collections;
import java.util.ArrayList; 
public class Day1 {
	
	public static void main(String[] args) {
		try {
	      File myObj = new File("day01.txt");
	      Scanner myReader = new Scanner(myObj);
	      ArrayList<Integer> curCal = new ArrayList<Integer>();
	      ArrayList<Integer> totalCals = new ArrayList<Integer>();
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        if(data.equals("")){
	        	int cursum = 0;
	        	for(int elem : curCal) {
	        		cursum += elem;
	        	}
	        	totalCals.add(cursum);
	        	curCal.clear();
	        }else {
	        	curCal.add(Integer.valueOf(data));
	        }
	   
	      }
	      Collections.sort(totalCals);
	      System.out.println("Part 1: " + totalCals.get(totalCals.size()-1)); 
	      System.out.println("Part 2: " + (totalCals.get(totalCals.size()-1)+totalCals.get(totalCals.size()-2)+totalCals.get(totalCals.size()-3))); 

	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	
	}
}
