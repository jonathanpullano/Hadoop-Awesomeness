package step4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import test.Util;
import constants.Constants;

public class Step4 {

	public void run(final String inputPath) throws Exception {
		final Configuration conf = new Configuration();

		final Job job = new Job(conf, "Step4");
		job.setJarByClass(Step4.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);

		job.setMapperClass(Map4.class);
		job.setReducerClass(Reducer4.class);

		// job.setPartitionerClass(GroupPartitioner.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));

		Util.deleteDir(Constants.reducer4OutputDir);
		FileOutputFormat.setOutputPath(job, new Path(
				Constants.reducer4OutputDir));

		job.waitForCompletion(true);
	}

	public static void main(final String[] args) throws Exception {
		final Step4 step = new Step4();
		step.run(Constants.reducer3OutputDir);
	}
}