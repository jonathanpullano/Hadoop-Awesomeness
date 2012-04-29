package constants;

public class Constants {
    // Total size of the file (probably should be read)
    public final static int N = 16; // <- step 2
    // public final static int N = 9; // <- step 1

    public final static int M = (int) Math.sqrt(N);

    // Number of Column Groups
    public final static int g = 2; // <- step 2
    // public final static int g = 1; // <- step 1

    // Length of a ColumnGroup (not including boundaries)
    public final static int groupLength = M / g;

    // Number of elements in a column group
    public final static int groupSize = groupLength * M;

    // compute filter parameters for netid ak883
    public final static float fromNetID = 0.388f;
    public final static float desiredDensity = 0.59f;
    public final static float wMin = 0.4f * fromNetID;
    public final static float wLimit = wMin + desiredDensity;

    public final static boolean DEBUG = true;
    public static final String outputDir = "data/output/";
    public static final String outputFile = outputDir + "part-r-00000";
    public static final String reducer1CpyPath = "data/step3/input/reducer1Output.txt";
    public static final String reducer2CpyPath = "data/step3/input/reducer2Output.txt";
}
