package constants;

public class Constants {
	// Total size of the file (probably should be read)
	public static int N = 100000000;
	// stats:
	public static int M = (int) Math.sqrt(N);
	public static int g = M / 16;
	public static int numGroups = M / g;
	public static int groupSize = M * (g + 1);

	// compute filter parameters for netid ak883
	public static final float fromNetID = 0.388f;
	public static float desiredDensity = 0.59f;
	public static float wMin = 0.4f * fromNetID;
	public static float wLimit = wMin + desiredDensity;

	// debug and extra credit vars:
	public static boolean DEBUG = true;
	public static boolean COMPUTE_DIAGONAL = false;

	// local vars:
	public static String reducer1OutputDir = "data/output/reducer1/";
	public static String reducer2OutputDir = "data/output/reducer2/";
	public static String reducer3OutputDir = "data/output/reducer3/";
	public static String reducer4OutputDir = "data/output/reducer4/";
	public static String reducer5OutputDir = "data/output/reducer5/";

	// AWS vars:
	public static String bucket = "";
	public static String reducer1OutputDirAWS = "outputStep1";
	public static String reducer2OutputDirAWS = "outputStep2";
	public static String reducer3OutputDirAWS = "outputStep3";
	public static String reducer4OutputDirAWS = "outputStep4";
	public static String reducer5OutputDirAWS = "outputStep5";

	/**
	 * 
	 * @param output
	 *            bucket_name
	 */
	public static void setOutputBucket(String bucket_name) {
		if (!bucket_name.endsWith("/")) {
			bucket_name = bucket_name + "/";
		}
		if (!bucket_name.startsWith("s3n://")) {
			bucket_name = "s3n://" + bucket_name;
		}
		bucket = bucket + bucket_name;
	}

	public static void setLimit(final float f) {
		desiredDensity = f;
		wLimit = wMin + desiredDensity;
	}

	/*
	 * public static void setParams(final int N_in, final int g_in) { N = N_in;
	 * g = g_in; M = (int) Math.sqrt(N); numGroups = M / g; groupSize = M * (g +
	 * 1); }
	 */
}
