package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Util {
    
    public static boolean deleteDir(String path) {
        File file = new File(path);
        return deleteDir(file);
    }
    
    // Deletes all files and subdirectories under dir.
    // Returns true if all deletions were successful.
    // If a deletion fails, the method stops attempting to delete and returns false.
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }
    
    public static boolean checkOutput(String file1, String file2) throws Exception {
        return getFile(file1).equals(getFile(file2));
    }
    
    public static String getFile(String filename) {
        Scanner scanner = null;
        StringBuilder plaintext = new StringBuilder();
        try {
            scanner = new Scanner(new FileInputStream(filename));
            while (scanner.hasNextLine())
                plaintext.append(scanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("File not found:" + filename);
        }
        return plaintext.toString();
    }
}
