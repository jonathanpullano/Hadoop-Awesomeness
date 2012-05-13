package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import step2.Step2;
import constants.Constants;

public class StepTwoTest {
    Step2 step2 = new Step2();

    public StepTwoTest() {
        // This code is evil
        Constants.N = 16;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 2;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g+1);
        Constants.wLimit = 1.25f;
        Constants.wMin = .75f;
        Constants.COMPUTE_DIAGONAL = false;
    }

    @Test
    public void testTwoCase1() throws Exception {
        Constants.reducer1OutputDir = "data/step2/test1";
        step2.run();
        assertTrue(Util.checkOutput(Constants.reducer2OutputDir
                + "part-r-00000", "data/step2/ans1.txt"));
    }

    @Test
    public void testTwoCase2() throws Exception {
        Constants.reducer1OutputDir = "data/step2/test2";
        step2.run();
        assertTrue(Util.checkOutput(Constants.reducer2OutputDir
                + "part-r-00000", "data/step2/ans2.txt"));
    }

}
