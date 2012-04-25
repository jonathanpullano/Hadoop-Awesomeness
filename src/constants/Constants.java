package constants;

public class Constants {
    public static int N = 10000; //Total size of the file (probably should be read)
    public static int M = (int) Math.sqrt(N);
    
    public static int g = 5; //Number of Column Groups
    public static int gLen = M/g; // Length of a ColumnGroup
}
