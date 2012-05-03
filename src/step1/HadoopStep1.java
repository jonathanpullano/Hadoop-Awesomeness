package step1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import constants.Constants;

//import test.Util;

public class HadoopStep1 {

	public void run(final String inputPath) throws Exception {
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
		FileOutputFormat.setOutputPath(job, new Path(Constants.bucket
				+ Constants.reducer1OutputDirAWS));

		job.waitForCompletion(true);
	}
}
