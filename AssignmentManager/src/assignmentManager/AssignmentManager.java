/*
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package assignmentManager;

import java.io.File;
import java.util.ArrayList;
 
public class AssignmentManager {

		private File sourceFilePath, targetFilePath;
		private ArrayList<File> assignmentFiles;
		public AssignmentManager(String filePath, String targetFilePath) {
			this.sourceFilePath = new File(filePath);
			this.targetFilePath = new File(targetFilePath);
			assignmentFiles = new ArrayList<File>();
		}
		
		public int process() {
			int countOfAssignments = 0, countOfUnzippedAssignments = 0;
			countOfAssignments = listFilesForFolder(sourceFilePath);
			countOfUnzippedAssignments = unzipAllAssignments();
			System.out.println("AssignmentManager.process(): " + countOfAssignments + " assignments were found. " + countOfUnzippedAssignments + " were unzipped.");
			return countOfUnzippedAssignments;
		}
		private int unzipAllAssignments() {
			int count = 0;
			for (File assignmentFile : assignmentFiles) {
				if (assignmentFile.getAbsolutePath().endsWith(".zip")) {
					unzip(assignmentFile);
					count++;
				} else {
					System.out.println("Assignment " + assignmentFile.getAbsolutePath() + " is not a .zip file");
				}
			}
			return count;
		}
		private void unzip(File file) {
			UnzipUtility unzipUtility = new UnzipUtility();
//            System.out.println("Unzipping " + file.getAbsolutePath());
            System.out.println("Unzipping " + file.getParent() + " : " + file.getName());
            // *****************************************************************************************
            // Make a directory to unzip into. This will prevent the unzipped files from overlapping.
            String targetDir;
            targetDir = file.getName();
            int firstUnderscore;
            firstUnderscore = targetDir.indexOf("_");
            int secondUnderscore;
            secondUnderscore = targetDir.substring(firstUnderscore + 1).indexOf("_");
            targetDir = targetDir.substring(0, firstUnderscore + secondUnderscore + 1);
            File newTargetFilePath = new File(targetFilePath.getAbsolutePath() + "\\" + targetDir);
            newTargetFilePath.mkdir();
            // *****************************************************************************************
            System.out.println("Unzipping into " + newTargetFilePath.getParent());
            try {
            	unzipUtility.unzip(file.getAbsolutePath(), newTargetFilePath.getAbsolutePath());
            } catch (Exception ex) {
            	System.out.println(ex.getLocalizedMessage());
            }
        }

		public int listFilesForFolder(final File folder) {
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isFile()) {
//		            System.out.println(fileEntry.getAbsolutePath());
		            assignmentFiles.add(new File(fileEntry.getAbsolutePath()));
//		            System.out.println(fileEntry.getName());
		        } else {
//		            listFilesForFolder(fileEntry);
		        }
		    }
		    return assignmentFiles.size();
		}		
}
