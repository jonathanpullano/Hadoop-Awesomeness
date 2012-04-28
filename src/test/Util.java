package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
        FileReader f1 = new FileReader(file1);
        FileReader f2 = new FileReader(file2);
        BufferedReader b1 = new BufferedReader(f1);
        BufferedReader b2 = new BufferedReader(f2);
        String line1 = b1.readLine();
        String line2 = b2.readLine();
        
        while(line1 != null && line2 != null) {
            if(!line1.equals(line2)) {
                return false;
            }
            line1 = b1.readLine();
            line2 = b2.readLine();
        }
        if(line1 == null && line2 != null || line1 != null && line2 == null)
            return false;
        return true;
    }
}
