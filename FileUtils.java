import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/*File Utilities for reading and writing
 *
 *@author: Nathan Bao
 *@since: August 25th, 2022
*/

public class FileUtils{
	/*
	 * This opens a file to read using the Scanner class
	 * @param filename	name of the file to open
	 * @return			the Scanner object to the file
	 */
	public static java.util.Scanner openToRead(String filename){
		java.util.Scanner input = null;
		try{
			input = new java.util.Scanner(new java.io.File(filename));
		}
		catch(java.io.FileNotFoundException e){
			System.err.println("Error: Cannot open "+filename+" for reading.");
			System.exit(1);
		}
		return input;
	}
	/*
	 * Opens a file to write using the PrintWriter class
	 * @param filename	name of the file to open
	 * @return			the PrintWriter object to the file
	 */
	public static PrintWriter openToWrite(String filename){
		PrintWriter output = null;
		try{
			output = new PrintWriter(new File(filename));
		}
		catch(FileNotFoundException e){
			System.err.println("Error: Cannot open "+filename+" for reading.");
			System.exit(1);
		}
		return output;
	}
}