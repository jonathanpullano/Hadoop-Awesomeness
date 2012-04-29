package constants;

public class Constants {
    // Total size of the file (probably should be read)
    public final static int N = 16; // <- step 2
    // public final static int N = 9; // <- step 1

    public static int M = (int) Math.sqrt(N);
    public static int g = 2; // Number of Column Groups
    public static int groupLength = M / g; // Length of a ColumnGroup (not
                                           // including boundaries)
    public static int groupSize = groupLength * M; // Number of elements in a
                                                   // column group

    // compute filter parameters for netid ak883
    public static float fromNetID = 0.388f;
    public static float desiredDensity = 0.59f;
    public static float wMin = 0.4f * fromNetID;
    public static float wLimit = wMin + desiredDensity;

    public final static boolean DEBUG = true;
    public static final String outputDir = "data/output/";
    public static final String outputFile = outputDir + "part-r-00000";
    public static final String reducer1CpyPath = "data/step3/input/reducer1Output.txt";
    public static final String reducer2CpyPath = "data/step3/input/reducer2Output.txt";
}
