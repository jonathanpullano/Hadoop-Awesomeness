package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Util {

    public static boolean deleteDir(final String path) {
        final File file = new File(path);
        return deleteDir(file);
    }

    // Deletes all files and subdirectories under dir.
    // Returns true if all deletions were successful.
    // If a deletion fails, the method stops attempting to delete and returns
    // false.
    public static boolean deleteDir(final File dir) {
        if (dir.isDirectory()) {
            final String[] children = dir.list();
            for (final String element : children) {
                final boolean success = deleteDir(new File(dir, element));
                if (!success) return false;
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    public static boolean checkOutput(final String file1, final String file2)
            throws Exception {
        return getFile(file1).equals(getFile(file2));
    }

    public static String getFile(final String filename) {
        Scanner scanner = null;
        final StringBuilder plaintext = new StringBuilder();
        try {
            scanner = new Scanner(new FileInputStream(filename));
            while (scanner.hasNextLine())
                plaintext.append(scanner.nextLine());
        } catch (final FileNotFoundException e) {
            System.out.println("File not found:" + filename);
        }
        return plaintext.toString();
    }
}
