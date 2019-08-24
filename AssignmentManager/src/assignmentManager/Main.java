/*
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package assignmentManager;
 
import java.io.File;

public class Main {

	public static void main(String[] args) {
		// Don't forget the trailing \\ on the path names!
		AssignmentManager assignmentManager = new AssignmentManager("C:\\Temp\\Assignment08\\", "c:\\test\\foo\\");

		int count = assignmentManager.process();
		System.out.println(count + " assigments processed");
	}

}
