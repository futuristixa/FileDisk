package proj2;
import java.io.File;
/**
 * 
 * @author leonyin
 *
 *@parms absPath - absolute path name of a file
 *@params size - size of a given file
 */
public class FileOnDisk implements Comparable<FileOnDisk>{
String absPath;
long size;
	public FileOnDisk(String absPath, long size){
		this.absPath = absPath;
		this.size=size;
	}
	public long getSize() {
		return size;
	}
	public String getAbsPath() {
		return absPath;
	}
	
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
