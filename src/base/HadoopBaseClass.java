package base;

import step1.HadoopStep1;
import step2.HadoopStep2;
import step3.HadoopStep3;
import step4.HadoopStep4;
import step5.HadoopStep5;
import constants.Constants;

public class HadoopBaseClass {
	public static void main(final String[] args) throws Exception {
		final HadoopStep1 step1 = new HadoopStep1();

		// arg0 sets the bucket where the output folders will be created
		Constants.setBucket(args[0]);

		// arg1 sets the input folder or file
		step1.run(args[1]);

		// steps 2-5 will be run using the output of the previous steps as input
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
