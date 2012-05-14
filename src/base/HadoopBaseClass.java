package base;

import step1.HadoopStep1;
import step2.HadoopStep2;
import step3.HadoopStep3;
import step4.HadoopStep4;
import step5.HadoopStep5;
import constants.Constants;

public class HadoopBaseClass {
    public static void main(final String[] args) throws Exception {

        // arg0 sets the bucket where the output folders will be created
        Constants.setOutputBucket(args[0]);

        // Set input
        final String input = args[1];

        // Set N and g
        // final int N = Integer.parseInt(args[2]);
        // final int g = Integer.parseInt(args[3]);
        // Constants.setParams(N, g);

        // arg1 sets the input folder or file
        final HadoopStep1 step1 = new HadoopStep1();
        step1.run(input);

        // steps 2-5 will be run, each using the output of the previous step as
        // input
        final HadoopStep2 step2 = new HadoopStep2();
        step2.run();

        final HadoopStep3 step3 = new HadoopStep3();
        step3.run();

        final HadoopStep4 step4 = new HadoopStep4();
        step4.run();

        final HadoopStep5 step5 = new HadoopStep5();
        step5.run();
    }

}
