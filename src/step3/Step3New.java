package step3;

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

public class Step3New {
	boolean setReducer = true;

	public void disableReducer() {
		setReducer = false;
	}

	public void run() throws Exception {
		final Configuration conf = new Configuration();
		final Job job = new Job(conf, "Step3");

		job.setJarByClass(Step3New.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Tuple.class);

		job.setMapperClass(Map3New.class);
		if (setReducer) {
			job.setReducerClass(Reducer3.class);
		}

		job.setPartitionerClass(TupleGroupPartitioner.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(Constants.reducer2OutputDir
				+ "/part-r-00000"));

		for (final String filename : Util
				.getReducerOutputs(Constants.reducer1OutputDir)) {
			FileInputFormat.addInputPath(job, new Path(
					Constants.reducer1OutputDir + "/" + filename));
		}

		Util.deleteDir(Constants.reducer3OutputDir);
		FileOutputFormat.setOutputPath(job, new Path(
				Constants.reducer3OutputDir));

		job.waitForCompletion(true);
	}

	public static void main(final String[] args) throws Exception {
		final Step3New step3 = new Step3New();
		step3.run();
	}
}
