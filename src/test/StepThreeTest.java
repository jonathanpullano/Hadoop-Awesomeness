package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import step3.Step3;
import constants.Constants;

public class StepThreeTest {
    public Step3 stepThree = new Step3();

    public StepThreeTest() {
        // This code is evil
        Constants.N = 16;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.g = 2;
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1.25f;
        Constants.wMin = .75f;
        Constants.DEBUG = true;
    }
    
    //@Test
    public void testHalfMonty() throws Exception {
        Constants.reducer1OutputDir = "data/step3/input1";
        Constants.reducer2OutputDir = "data/step3/input2";
        stepThree.disableReducer();
        stepThree.run();
        assertTrue(Util.checkOutput(Constants.reducer3OutputDir
                + "part-r-00000", "data/step3/ans-map.txt"));
    }

    @Test
    public void testFullMonty() throws Exception {
        Constants.reducer1OutputDir = "data/step3/input1";
        Constants.reducer2OutputDir = "data/step3/input2";
        stepThree.run();
        assertTrue(Util.checkOutput(Constants.reducer3OutputDir
                + "part-r-00000", "data/step3/ans-full.txt"));
    }
}
