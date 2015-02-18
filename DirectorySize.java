package proj2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This program provides a tool that given a name of a directory,
 * explores all its sub-directories and files and does two things: 
 *  - computes the total size of all the files and sub-directories,
 *  - prints a list of n largest files (their sizes and absolute paths. 
 *  
 * @author Joanna Klukowska & Leon Yin
 *
 */
public class DirectorySize {
	
	/**list of files found in the directory structure */
	static List <FileOnDisk> listOfFiles ; 
	
	/**list of visited directories (kept to avoid 
	 * circular links and infinite recursion) */
	static List <String> listOfVisitedDirs;
	
	/** 
	 * This method expects one or two arguments. 
	 * @param args Array of arguments passed to the program. The first one 
	 * is the name of the directory to be explored. The second (optional) is the
	 * max number of largest files to be printed to the screen.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		//TODO:
		//check the number of command line arguments
		//terminate if not sufficient
		
		//TODO:
		// use directory name entered from the command line
		// verify if the directory is valid, terminate if not
		String directory = args[0];
		File dir = new File(directory);
		
		// verification goes here CHECK
		if (!dir.exists()){
			System.err.println("No such input file");
			System.exit(1);
		}
				
		//create an empty list of files
		listOfFiles = new LinkedList<FileOnDisk> ();
		//create an empty list of directories
		listOfVisitedDirs = new ArrayList<String> ();
		
		// Display the total size of the directory/file
		long size = getSize( dir );
		if (size < 1024 ) //print bytes
			System.out.printf("Total space used: %7.2f bytes", 
					(float) size  );
		else if (size/1024 < 1024 )//print kilobytes
			System.out.printf("Total space used: %7.2f KB\n", 
					(float) size / 1024.0 );
		else if (size/1024/1024 < 1024 )//print megabytes
			System.out.printf("Total space used: %7.2f MB\n", 
					(float) size / (1024.0 * 1024));
		else //print gigabytes
			System.out.printf("Total space used: %7.2f GB\n", 
					(float) size / (1024.0 * 1024*1024));
		
		
		// Display the largest files in the directory
		int numOfFiles = 20; //default value
		try {
			if (args.length == 2 )  {
				numOfFiles = Integer.parseInt(args[1]);
			}
		}
		catch (NumberFormatException ex) {
			System.err.printf("ERROR: Invalid number of files provided." +
					"The second argument should be an integer. \n");
			System.exit(1);
		}
		System.out.printf("Largest %d files: \n", numOfFiles );
		
		Collections.sort(listOfFiles);
		
		for (int i = 0; i < numOfFiles; i++)
			//print from the back so that the largest files are printed
			System.out.println(listOfFiles.get(listOfFiles.size() - i - 1) );
		
	}


	/**
	 * Recursively determines the size of a directory or file represented 
	 * by the abstract parameter object file.   
	 * This method populates the listOfFiles with all the files contained in the
	 * explored directory. 
	 * This method populates the listOfVisitedDirs with canonical path names of 
	 * all the visited directories. 
	 * @param file directory/file name whose size should be determined
	 * @return number of bytes used for storage on disk
	 * @throws IOException 
	 */
	public static long getSize (File file) throws IOException   {
		long size = 0; // Store the total size of all files
		
		//if potentialDirName is a directory
		if (file.isDirectory()){
			//make sure the directory has not been searched already
			if (!listOfVisitedDirs.contains(file.getAbsolutePath())){
				//add its size to totalSize 
				//NOTE:redundant?
				//get the list of all the files and sub-directories in potentialDirName CHECK
				File[] listOfFiles = file.listFiles();
				//for each of the files and sub-directories
				for (File subfile : listOfFiles) {
					//call exploreDir <-- this is the recursive call !!!
					getSize(subfile);
				}
				listOfVisitedDirs.add(file.getAbsolutePath()); //adds directory to list of visited directories
			}
		}
		if (file.isFile() || !listOfFiles.contains(file)){
		//otherwise potentialDirName is a file
			long sizeTemp =file.length(); 	//get the size of the file
			size += sizeTemp;				//add file�s size to totalSize CHECK
			String absPath = file.getAbsolutePath();	 //get absPath
			listOfFiles.add(new FileOnDisk(absPath,sizeTemp)); 	//add file (its name and size) to the list of files
		}
		//TODO: implement this method
		Collections.sort(listOfFiles);
		return size;
	}
	
}
