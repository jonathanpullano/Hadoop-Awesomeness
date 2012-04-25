package constants;

public class Constants {
    
    //The production file has 400,000,000 lines, which will fit in an int
    public static int N = 10000; //Total size of the file (probably should be read)
    public static int M = (int) Math.sqrt(N);
    public static int g = 5; //Number of Column Groups
    public static int groupLength = M/g; // Length of a ColumnGroup
}
