package step1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import step2.Step2;
import step3.Step3;
import step4.Step4;
import step5.Step5;
import test.Util;
import constants.Constants;

public class Step1 {

	public void run(final String inputPath) throws Exception {
		final Configuration conf = new Configuration();

		final Job job = new Job(conf, "Step1");
		job.setJarByClass(Step1.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(Map1.class);
		job.setReducerClass(Reducer1.class);

		job.setPartitionerClass(GroupPartitioner.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));

		Util.deleteDir(Constants.reducer1OutputDir);
		FileOutputFormat.setOutputPath(job, new Path(
				Constants.reducer1OutputDir));

		job.waitForCompletion(true);
	}

	public static void main(final String[] args) throws Exception {
		final Step1 step1 = new Step1();
		step1.run("data/appendix_test_files/data6-36.txt");

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
