package proj2;

import java.text.DecimalFormat;

/**
 * 
 *@author leonyin
 *
 *@parms absPath - absolute path name of a file
 *@params size - size of a given file
 *
 */

//TODO: override the toString Method
public class FileOnDisk implements Comparable<FileOnDisk>{
/**
 * This class contains the File constructor and getter functions.
 * <p>
 * The compareTo override is intended to sort the File objects according to their size in bytes, rather than say their path.
 * <p>
 * 
 */
String absPath;
long size;
DecimalFormat df = new DecimalFormat("#00.00"); //to return the formatted size within two decimal points
	/**
	 * Constructor for the FileOnDisk object.
	 * @param absPath represents the absolute path of the fileOnDisk object.
	 * @param size represents the size of the fileOnDisk object in bytes.
	 */
	public FileOnDisk(String absPath, long size){ //this is the constructor for the File object
		this.absPath = absPath;
		this.size=size;
	}
	
	//getters
	public long getSize() {
		return this.size;
	}	
	public String getAbsPath() {
		return this.absPath;
	}
	/**
	 * This override is to compare the File objects according to their size in bytes.
	 */
	@Override
	public int compareTo(FileOnDisk other){
		if(this.size < other.getSize()){
			return -1;
		}
		else if(this.size == other.getSize()){
			return 0;
		}
		else{
			return 1;
		}
	}
	/**
	 * This override is to return the FileOnDisk object's 
	 * properly formatted size and absolute path when called with the get() method in DirectorySize main.
	 */
	@Override
	public String toString(){
		if (size < 1024 ) { //print bytes
			return (String.valueOf(df.format(size))+" B\t"+absPath);
		}
		else if (size/1024 < 1024 ){//print kilobytes
			return (String.valueOf(df.format(size/1024.0))+" KB\t"+absPath);
		}
		else if (size/1024/1024 < 1024 ){//print megabytes
			return (String.valueOf(df.format(size/(1024.0*1024)))+" MB\t"+absPath);
		}
		else //print gigabytes
			return (String.valueOf(df.format(size/(1024.0 * 1024*1024)))+ " GB\t"+absPath);
	}
}
