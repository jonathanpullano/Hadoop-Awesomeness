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
import constants.Constants;

public class HadoopStep5 {

	public void run() throws Exception {
		final Configuration conf = new Configuration();

		final Job job = new Job(conf, "Step5");
		job.setJarByClass(HadoopStep5.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Tuple.class);

		job.setMapperClass(Map5.class);
		job.setReducerClass(Reducer5.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(Constants.bucket
				+ Constants.reducer4OutputDirAWS));

		FileOutputFormat.setOutputPath(job, new Path(Constants.bucket
				+ Constants.reducer5OutputDirAWS));

		job.waitForCompletion(true);
	}
}