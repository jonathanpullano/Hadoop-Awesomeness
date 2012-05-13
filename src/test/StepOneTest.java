package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import step1.Step1;
import constants.Constants;

public class StepOneTest {
    Step1 stepOne = new Step1();

    public StepOneTest() {
        // This code is evil
        Constants.N = 9;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 3;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g+1);
        Constants.wLimit = 1.25f;
        Constants.wMin = .75f;
        Constants.COMPUTE_DIAGONAL = false;
    }

    @Test
    public void testAllConnected() throws Exception {
        stepOne.run("data/step1/test1.txt");
        assertTrue(Util.checkOutput(Constants.reducer1OutputDir
                + "part-r-00000", "data/step1/ans1.txt"));
    }

    @Test
    public void testAllZeros() throws Exception {
        stepOne.run("data/step1/test2.txt");
        assertTrue(Util.checkOutput(Constants.reducer1OutputDir
                + "part-r-00000", "data/step1/ans2.txt"));
    }

    @Test
    public void testComponents() throws Exception {
        stepOne.run("data/step1/test3.txt");
        assertTrue(Util.checkOutput(Constants.reducer1OutputDir
                + "part-r-00000", "data/step1/ans3.txt"));
    }
}
