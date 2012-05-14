package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import base.BaseClass;
import constants.Constants;

public class BaseTest {
    private final BaseClass base = new BaseClass();

    @Test
    public void test36() throws Exception {
        Constants.N = 36;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data6-36.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans-36.txt"));
    }

    @Test
    public void testIsolation() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data2.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans2.txt"));
    }

    @Test
    public void testFull() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data3.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans3.txt"));
    }

    @Test
    /**
     * Answers provided by ben are wrong, the correct component size is 20 and its a typo in his
     * notes.
     */
    public void testNorthEastCorner() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data4.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans4.txt"));
    }

    @Test
    public void testNorthEastDiag() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data5.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans5.txt"));
    }

    @Test
    public void testSouthWestCorner() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data6.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans6.txt"));
    }

    @Test
    public void testSouthWestDiag() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data7.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans7.txt"));
    }

    @Test
    public void testHorizontalStrips() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data8.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans8.txt"));
    }

    @Test
    public void testHorizontalStripsDiag() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data9.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans9.txt"));
    }

    @Test
    public void testVerticalStrips() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data10.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans10.txt"));
    }

    @Test
    public void testVerticalStripsDiag() throws Exception {
        Constants.N = 1600;
        Constants.g = 2;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1f;
        Constants.wMin = 0.1f;
        base.start("data/appendix_test_files/data11.txt");
        assertTrue(Util.checkOutput(Constants.reducer5OutputDir
                + "part-r-00000", "data/base/ans11.txt"));
    }

    @Test
    public void testSean() throws Exception {
        Constants.N = 81;
        Constants.g = 3;
        Constants.M = (int) Math.sqrt(Constants.N);
        Constants.numGroups = Constants.M / Constants.g;
        Constants.groupSize = Constants.M * (Constants.g + 1);
        Constants.wLimit = 1.25f;
        Constants.wMin = .75f;

        base.start("data/step1/test-sean.txt");
        assertTrue(Util.checkOutput(Constants.reducer3OutputDir
                + "/part-r-00000", "data/step1/ans-sean.txt"));
    }
}
