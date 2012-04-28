package test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import step1.Step1;

public class StepOneTest {
    Step1 stepOne = new Step1();

    @Test
    public void testAllConnected() throws Exception {
        stepOne.run("data/step1/test1.txt");
        assertTrue(Util.checkOutput("data/output/part-r-00000", "data/step1/ans1.txt"));
    }
    
    @Test
    public void testAllZeros() throws Exception {
        //stepOne.run("data/step1/test2.txt");
        assertTrue(Util.checkOutput("data/output/part-r-00000", "data/step2/test2.txt"));
    }
    
    @Test
    public void testComponents() throws Exception {
        //stepOne.run("data/step1/test3.txt");
        assertTrue(Util.checkOutput("data/output/part-r-00000", "data/step3/test2.txt"));
    }
}
