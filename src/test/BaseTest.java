package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import base.BaseClass;
import constants.Constants;

public class BaseTest {
    private final BaseClass base = new BaseClass();
/*
    @Test
    public void test36() throws Exception {
        // This code is evil
        Constants.COMPUTE_DIAGONAL = false;
        Constants.N = 36;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 2;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        Constants.DEBUG = true;
        base.start("data/appendix_test_files/data6-36.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans-36.txt"));
    }
*/
    @Test
    public void testIsolation() throws Exception {
        Constants.COMPUTE_DIAGONAL = false;
        Constants.N = 1600;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 3;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        Constants.DEBUG = true;
        base.start("data/appendix_test_files/data2.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans2.txt"));
    }
/*
    @Test
    public void testSean() throws Exception {
        Constants.COMPUTE_DIAGONAL = false;
        Constants.N = 81;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 3;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1.25f;
        Constants.wMin = .75f;
        Constants.DEBUG = true;

        base.start("data/step1/test-sean.txt");
        assertTrue(Util.checkOutput(Constants.reducer3OutputDir
                + "/part-r-00000", "data/step1/ans-sean.txt"));
    }
*/
}
