package base;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import step1.Step1;
import step2.Step2;
import step3.Step3;
import step4.Step4;
import step5.Step5;
import constants.Constants;

public class ExtraCreditBase {

	public static void main(final String[] args) throws Exception {

		/*
		 * NOTE: IN ORDER TO EXPORT THE RESULTS OF THIS TEST, YOU MUST UNCOMMENT
		 * THE EXTRA CREDIT SECTION IN Reducer5.java!!!!
		 */

		final ExtraCreditBase base = new ExtraCreditBase();

		Constants.COMPUTE_DIAGONAL = true;

		new File("stats.csv").delete();

		final Writer stats = new OutputStreamWriter(new FileOutputStream(
				"stats.csv", true));
		try {
			stats.write("density, vertices, edges, components, max component, "
					+ "min component, raw average, weighted average, "
					+ "burn count\n");
		} finally {
			stats.close();
		}

		for (float i = 0.01f; i < 1.0; i += 0.01f) {
			Constants.setLimit(i);
			base.start("data/millionLines.txt");
		}

		Constants.COMPUTE_DIAGONAL = false;
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
