package test;

import org.junit.Test;

import base.BaseClass;
import constants.Constants;

public class BaseTest {
    private final BaseClass base = new BaseClass();

    public BaseTest() {
        // This code is evil
        Constants.N = 36;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 3;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1.25f;
        Constants.wMin = .75f;
        Constants.DEBUG = true;
    }

    @Test
    public void testRun() throws Exception {
        base.start("data/appendix_test_files/data6-36.txt");
    }
}
