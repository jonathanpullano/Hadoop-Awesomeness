package constants;

public class Constants {
	// Total size of the file (probably should be read)
	public static int N = 1600; // <- step 2
	// public final static int N = 9; // <- step 1

	public static int M = (int) Math.sqrt(N);
	public static int g = 4; // Length of Group
	public static int numGroups = M / g;
	public static int groupSize = M * (g + 1);

	// compute filter parameters for netid ak883
	public static float fromNetID = 0.388f;
	public static float desiredDensity = 0.59f;
	public static float wMin = 0.4f * fromNetID;
	public static float wLimit = wMin + desiredDensity;

	public static boolean DEBUG = true;

	public static String reducer1OutputDir = "data/output/reducer1/";
	public static String reducer2OutputDir = "data/output/reducer2/";
	public static String reducer3OutputDir = "data/output/reducer3/";
	public static String reducer4OutputDir = "data/output/reducer4/";
	public static String reducer5OutputDir = "data/output/reducer5/";

	public static String bucket = "s3n://edu-cornell-cs-cs5300s12-cgd36-test";
	public static String reducer1OutputDirAWS = "/outputStep1";
	public static String reducer2OutputDirAWS = "/outputStep2";
	public static String reducer3OutputDirAWS = "/outputStep3";
	public static String reducer4OutputDirAWS = "/outputStep4";
	public static String reducer5OutputDirAWS = "/outputStep5";
}
