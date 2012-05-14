package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Util {

    public static boolean deleteDir(final String path) {
        return deleteDir(new File(path));
    }

    public static boolean copyFile(final String from, final String to)
            throws IOException {
        return copyFile(new File(from), new File(to));
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
                if (!success)
                    return false;
            }
        }
        // The directory is now empty so delete it
        return dir.delete();
    }

    public static boolean copyFile(final File fromFile, final File toFile)
            throws IOException {
        if (!toFile.exists())
            toFile.createNewFile();
        InputStream in = null;
        OutputStream out = null;

        try {
            in = new FileInputStream(fromFile);
            out = new FileOutputStream(toFile);

            // Transfer bytes from in to out
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
        } finally {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
        }
        return true;
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

    public static ArrayList<String> getReducerOutputs(
            final String reducerDirectory, final String prefix) {
        final File dir = new File(reducerDirectory);
        if (dir.isDirectory()) {
            final ArrayList<String> children = new ArrayList<String>(
                    Arrays.asList(dir.list()));
            // Pattern pattern = Pattern.compile("part-r-[0-9]*");

            final Iterator<String> iter = children.iterator();
            while (iter.hasNext()) {
                final String tmp = iter.next();

                if (!tmp.startsWith(prefix))
                    iter.remove();
            }

            System.out.println(children);
            return children;
        } else
            throw new RuntimeException("Not a reducer dir");
    }

    public static ArrayList<String> getReducerOutputs(
            final String reducerDirectory) {
        return getReducerOutputs(reducerDirectory, "part-r-");
    }
}
