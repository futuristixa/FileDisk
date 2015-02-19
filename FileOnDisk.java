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
DecimalFormat df = new DecimalFormat("#00.00");
	public FileOnDisk(String absPath, long size){ //this is the constructor for the File object
		this.absPath = absPath;
		this.size=size;
	}
	public long getSize() { //this method returns the size of the file object in bytes.
		return this.size;
	}
	
	public String getSize1() { //method returns the size of the file object with the correct file type.
		if (size < 1024 ) { //print bytes
			return (String.valueOf(df.format(size))+" B");
		}
		else if (size/1024 < 1024 ){//print kilobytes
			return (String.valueOf(df.format(size/1024.0))+" KB");
		}
		else if (size/1024/1024 < 1024 ){//print megabytes
			return (String.valueOf(df.format(size/(1024.0*1024)))+" MB");
		}
		else //print gigabytes
			return (String.valueOf(df.format(size/(1024.0 * 1024*1024)))+ " GB");
		
	}
	
	public String getAbsPath() { //this method returns the absolute path of the file object
		return this.absPath;
	}
	/**
	 * This override is to compare the File objects according to their size in bytes.
	 */
	@Override
	public int compareTo(FileOnDisk other){
		if(this.size < other.getSize()){
			return 1;
		}
		else if(this.size == other.getSize()){
			return 0;
		}
		else{
			return -1;
		}
	}
}
