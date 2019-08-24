package assignmentManager;

import java.io.File;
import java.util.ArrayList;

public class AssignmentManager {

		private File filePath;
		private ArrayList<File> assignmentFiles;
		public AssignmentManager(String filePath) {
			this.filePath = new File(filePath);
			assignmentFiles = new ArrayList<File>();
		}
		
		public void process() {
			listFilesForFolder(filePath);
			unzipAllAssignments();
			
		}
		private void unzipAllAssignments() {
			for (File assignmentFile : assignmentFiles) {
				if (assignmentFile.getAbsolutePath().endsWith(".zip")) {
					unzip(assignmentFile);
				} else {
					System.out.println("Assignment " + assignmentFile.getAbsolutePath() + " is not a .zip file");
				}
			}
		}
		private void unzip(File file) {
			UnzipUtility unzipUtility = new UnzipUtility();
            System.out.println("Unzipping " + file.getAbsolutePath());
            File targetFile = new File("c:\\test\\foo");
            try {
            	unzipUtility.unzip(file.getAbsolutePath(), targetFile.getAbsolutePath());
            } catch (Exception ex) {
            	System.out.println(ex.getLocalizedMessage());
            }
        }

		public void listFilesForFolder(final File folder) {
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isFile()) {
//		            System.out.println(fileEntry.getAbsolutePath());
		            assignmentFiles.add(new File(fileEntry.getAbsolutePath()));
//		            System.out.println(fileEntry.getName());
		        } else {
//		            listFilesForFolder(fileEntry);
		        }
		    }
		}		
}
