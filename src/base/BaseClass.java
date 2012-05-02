package base;

import step1.Step1;
import step2.Step2;
import step3.Step3;
import step4.Step4;
import step5.Step5;
import constants.Constants;

public class BaseClass {
    public static void main(final String[] args) throws Exception {
        final BaseClass base = new BaseClass();
        base.start("data/appendix_test_files/data8.txt");
    }

    public void start(final String inputFile) throws Exception {
        final Step1 step1 = new Step1();
        step1.run(inputFile);

        final Step2 step2 = new Step2();
        step2.run();

        final Step3 step3 = new Step3();
        step3.run();

        final Step4 step4 = new Step4();
        step4.run(Constants.reducer3OutputDir);

        final Step5 step5 = new Step5();
        step5.run(Constants.reducer4OutputDir);
    }

}
