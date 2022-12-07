import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.text.StyledEditorKit.ForegroundAction;

//$ cd /
//$ ls
//dir a
//14848514 b.txt
//8504156 c.dat
//dir d
//$ cd a
//$ ls
//dir e
//29116 f
//2557 g
//62596 h.lst
//$ cd e
//$ ls
//584 i
//$ cd ..
//$ cd ..
//$ cd d
//$ ls
//4060174 j
//8033020 d.log
//5626152 d.ext
//7214296 k

public class Day07 {

	public static void main(String[] args) throws IOException {
		Path filepath = Paths.get("day07.txt");
//		Path filepath = Paths.get("test.txt");
		
		ArrayList<String> inputList= (ArrayList<String>) Files.readAllLines(filepath);
		
		//input:
		
		Folder root = new Folder("/",null);
		FileSystem fs = new FileSystem();
		fs.curFolder = root;
		
		
		
		
		for (String line : inputList) {
			if(line.charAt(0)=='$') {
				//$ cd ..
				//$ cd d
				//$ ls
				String[] splitt = line.split(" ");
				String op = splitt[1];
				if(op.equals("cd")) {
					String goToPath = splitt[2];
					if(goToPath.equals("/")){
						fs.curFolder = root;
						fs.allCurParents.clear();
						continue;
					}else if(goToPath.equals("..")) {
						if(fs.curFolder!=root) {
							fs.allCurParents.remove(fs.curFolder.parent);
							fs.curFolder = fs.curFolder.parent;
						}
						
					}else {
						boolean foundSth = false;
						//goToSubFolder xxx
						for (FileSystemElement FsE : fs.curFolder.children) {
							if(FsE instanceof Folder && ((Folder) FsE).name.equals(splitt[2])) {
								fs.allCurParents.add(fs.curFolder);
								fs.curFolder = (Folder) FsE;
								foundSth = true;
								continue;
							}
						}
						if(!foundSth) System.out.println("Did not find " + splitt[2]);
					}
					
				}else if(op.equals("ls")) {
					continue;
					
				}
				//
				
				
			}else {
				//printing contents of currFolder
				String[] splitt = line.split(" ");
				if(splitt[0].equals("dir")) {
					
					
					//add subDir
					
					Folder newFolder = new Folder(splitt[1],fs.curFolder);
					if(!fs.curFolder.children.contains(newFolder)) {
						fs.curFolder.children.add(newFolder);
						fs.allFolders.add(newFolder);
					}else {
						System.out.println("Crazyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
					}
					
				}else {
					int currSize = Integer.parseInt(splitt[0]);
					String fileName = splitt[1];
					File newFile = new File(fileName, currSize);
					if(!fs.curFolder.children.contains(newFile)) {
						fs.curFolder.children.add(newFile);
						fs.curFolder.sumOfSizes+=currSize;
						for (Folder par : fs.allCurParents) {
							par.sumOfSizes+=currSize;
							par.allSubFiles.add(newFile);
						}
						
						
					}
					
					
				}
				
			}
		}
		int sum = 0;
		int smallestPossible = Integer.MAX_VALUE;
		
		
		fs.remainingSpace= fs.totalSpace - root.sumOfSizes;
		int freeUp = fs.neededSpace - fs.remainingSpace;
		
		for (Folder fold : fs.allFolders) {
			sum+= fold.sumOfSizes<=100000? fold.sumOfSizes : 0;
			smallestPossible = fold.sumOfSizes>=freeUp && fold.sumOfSizes<smallestPossible ? fold.sumOfSizes : smallestPossible;
		}
		System.out.println("Part 1 : " + sum);
		System.out.println("Part 2 : " + smallestPossible);

		
		
		
		
	}

}

class FileSystem {
	Folder curFolder;
	HashSet<Folder> allCurParents;
	HashSet<Folder> allFolders;
	int totalSpace = 70000000;
	int neededSpace = 30000000;
	int remainingSpace;
	
	FileSystem(){
		allCurParents = new HashSet<Folder>();
		allFolders = new HashSet<Folder>();
	}
	
}

class FileSystemElement{
	
	String name;
	Folder parent;
	
	
	String getName() {
		return name;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof FileSystemElement)) {
			return false;
		}
		FileSystemElement otherFsE = (FileSystemElement) obj;
		if(!this.name.equals(otherFsE.name)) {
			return false;
		}
		if((this.parent == otherFsE.parent)) {
			return false;
		}
		if(obj instanceof Folder != this instanceof Folder) {
			return false;
		}
		return true;
	}
	
	
}

class Folder extends FileSystemElement implements Comparable<Folder>{
	LinkedList<FileSystemElement> children;
	LinkedList<File> allSubFiles;
	int sumOfSizes = 0;
	Folder(){
		children = null;
		allSubFiles = null;
	}
	
	Folder(String name,Folder parent){
		this.children = new LinkedList<FileSystemElement>();
		this.allSubFiles = new LinkedList<File>();
		this.name = name;
		this.parent = parent;
	}

	@Override
	public int compareTo(Folder o) {
		
		return Integer.compare(this.sumOfSizes, o.sumOfSizes);
	}
	
	
}

class File extends FileSystemElement{
	
	int size;
		
	File(String name, int size){
		this.name = name;
		this.size = size;
	}
	
	
	
}
