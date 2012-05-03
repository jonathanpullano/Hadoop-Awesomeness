package step1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import step2.HadoopStep2;
import step3.HadoopStep3;
import step4.HadoopStep4;
import step5.HadoopStep5;

//import test.Util;

public class HadoopStep1 {

	public void run(final String inputPath, final String outputPath)
			throws Exception {
		final Configuration conf = new Configuration();

		final Job job = new Job(conf, "HadoopStep1");
		job.setJarByClass(HadoopStep1.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(Map1.class);
		job.setReducerClass(Reducer1.class);

		job.setPartitionerClass(GroupPartitioner.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));
		FileOutputFormat.setOutputPath(job, new Path(outputPath));

		job.waitForCompletion(true);
	}

	public static void main(final String[] args) throws Exception {
		final HadoopStep1 step1 = new HadoopStep1();
		step1.run(args[0], args[1]);

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
