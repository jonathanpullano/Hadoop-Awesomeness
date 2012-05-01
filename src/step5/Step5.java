package step5;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import structures.Tuple;
import test.Util;
import constants.Constants;

public class Step5 {

	public void run(final String inputPath) throws Exception {
		final Configuration conf = new Configuration();

		final Job job = new Job(conf, "Step5");
		job.setJarByClass(Step5.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Tuple.class);

		job.setMapperClass(Map5.class);
		job.setReducerClass(Reducer5.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(inputPath));

		Util.deleteDir(Constants.reducer5OutputDir);
		FileOutputFormat.setOutputPath(job, new Path(
				Constants.reducer5OutputDir));

		job.waitForCompletion(true);
	}

	public static void main(final String[] args) throws Exception {
		final Step5 step = new Step5();
		step.run("data/step5/test1.txt");
	}
}