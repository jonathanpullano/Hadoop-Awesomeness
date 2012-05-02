package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import base.BaseClass;
import constants.Constants;

public class BaseTest {
    private final BaseClass base = new BaseClass();

    public BaseTest() {
        // This code is evil
        Constants.N = 36;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 2;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        Constants.DEBUG = true;
    }

    @Test
    public void test6subset() throws Exception {
        base.start("data/appendix_test_files/data6-36.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans-36.txt"));
    }
}
