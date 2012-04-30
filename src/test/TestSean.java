package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import step1.Step1;
import step2.Step2;
import step3.Step3;
import constants.Constants;

public class TestSean {
    @Test
    public void testSean() throws Exception {
        Constants.N = 81;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 3;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1.25f;
        Constants.wMin = .75f;
        Constants.DEBUG = true;
        
        Constants.reducer1OutputDir = "data/output/reducer1";
        Step1 stepOne = new Step1();
        stepOne.run("data/step1/test-sean.txt");
        Constants.reducer2OutputDir = "data/output/reducer2";
        Step2 stepTwo = new Step2();
        stepTwo.run();
        Constants.reducer3OutputDir = "data/output/reducer3";
        Step3 stepThree = new Step3();
        stepThree.run();
        assertTrue(Util.checkOutput(Constants.reducer3OutputDir + "/part-r-00000", "data/step1/ans-sean.txt"));
    }
}
