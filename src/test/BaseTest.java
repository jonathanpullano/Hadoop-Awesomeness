package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import base.BaseClass;
import constants.Constants;

public class BaseTest {
	private final BaseClass base = new BaseClass();

	@Test
	public void test36() throws Exception {
		// This code is evil
		Constants.N = 1600;
		Constants.g = 4;
        Constants.M = (int) Math.sqrt(Constants.N);
		Constants.numGroups = Constants.M / Constants.g;
		Constants.groupSize = Constants.M * (Constants.g + 1);
		Constants.wLimit = 1f;
		Constants.wMin = 0.1f;
		Constants.DEBUG = true;
		base.start("data/appendix_test_files/data6.txt");
		assertTrue(Util.checkOutput(Constants.reducer5OutputDir
				+ "part-r-00000", "data/base/ans-36.txt"));
	}

	/*
    public void testIsolation() throws Exception {
        Constants.N = 1600;
        Constants.g = 3;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        Constants.DEBUG = true;
        base.start("data/appendix_test_files/data2.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans2.txt"));
    }

    @Test
    public void testFull() throws Exception {
        Constants.N = 1600;
        Constants.g = 1;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        Constants.DEBUG = true;
        base.start("data/appendix_test_files/data3.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans3.txt"));
    }


    @Test
        Constants.M = (int) Math.sqrt(Constants.N);
}
