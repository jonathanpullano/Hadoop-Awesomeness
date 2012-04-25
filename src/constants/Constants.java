package constants;

public class Constants {
    public static long N = 10000; //Total size of the file (probably should be read)
    public static long M = (long) Math.sqrt(N);
    public static long g = 5; //Number of Column Groups
    public static long groupLength = M/g; // Length of a ColumnGroup
}
