package constants;

public class Constants {

    // The production file has 400,000,000 lines, which will fit in an int

    // Total size of the file (probably should be read)
    public static int N = 16;

    public static int M = (int) Math.sqrt(N);

    // Number of Column Groups
    public static int g = 2;
    // Length of a ColumnGroup (not including boundaries)
    public static int groupLength = M / g;

    // Number of elements in a column group
    public static int groupSize = groupLength * M;

    // compute filter parameters for netid ak883
    public static float fromNetID = 0.388f;
    public static float desiredDensity = 0.59f;
    public static float wMin = 0.4f * fromNetID;
    public static float wLimit = wMin + desiredDensity;
}
