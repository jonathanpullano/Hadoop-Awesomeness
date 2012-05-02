package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import step4.Step4;
import constants.Constants;

public class StepFourTest {
	Step4 step4 = new Step4();

	public StepFourTest() {
		// This code is evil
		Constants.N = 16;
		Constants.M = (int) Math.sqrt(Constants.N);
		Constants.g = 2;
		Constants.COMPUTE_DIAGONAL = false;
		// Constants.numGroups = Constants.M / Constants.g;
		// Constants.groupSize = Constants.M * (Constants.g+1);
		// Constants.wLimit = 1.25f;
		// Constants.wMin = .75f;
	}

	@Test
	public void testFourCase1() throws Exception {
		step4.run("data/step4/test1.txt");
		assertTrue(Util.checkOutput(Constants.reducer4OutputDir
				+ "part-r-00000", "data/step4/ans1.txt"));
	}
}
