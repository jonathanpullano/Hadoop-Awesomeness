package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import step2.Step2;

public class StepTwoTest {
    Step2 step2 = new Step2();

    @Test
    public void testTwoCase1() throws Exception {
        step2.run("data/step2/test1.txt");
        assertTrue(Util.checkOutput("data/output/part-r-00000",
                "data/step2/ans1.txt"));
    }

    @Test
    public void testTwoCase2() throws Exception {
        step2.run("data/step2/test2.txt");
        assertTrue(Util.checkOutput("data/output/part-r-00000",
                "data/step2/ans2.txt"));
    }

}
