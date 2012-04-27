package constants;

public class Constants {

    // The production file has 400,000,000 lines, which will fit in an int
    public static int N = 16; // Total size of the file (probably should be
                                 // read)
    public static int M = 4;//(int) Math.sqrt(N);
    public static int g = 1; // Number of Column Groups
    public static int groupLength = M / g; // Length of a ColumnGroup (not
                                           // including boundaries)
    public static int groupSize = groupLength * M; // Number of elements in a
                                                   // column group

    // compute filter parameters for netid ak883
    public static float fromNetID = 0.388f;
    public static float desiredDensity = 0.59f;
    public static float wMin = 0; //0.4f * fromNetID;
    public static float wLimit = 1;//wMin + desiredDensity;
}
