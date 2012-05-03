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
import constants.Constants;

public class HadoopStep3 {
	boolean setReducer = true;

	public void disableReducer() {
		setReducer = false;
	}

	public void run() throws Exception {
		final Configuration conf = new Configuration();
		final Job job = new Job(conf, "HadoopStep3");

		job.setJarByClass(HadoopStep3.class);

		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Tuple.class);

		job.setMapperClass(HadoopMap3.class);
		if (setReducer) {
			job.setReducerClass(Reducer3.class);
		}

		job.setPartitionerClass(TupleGroupPartitioner.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(Constants.bucket
				+ Constants.reducer1OutputDirAWS));

		FileOutputFormat.setOutputPath(job, new Path(Constants.bucket
				+ Constants.reducer3OutputDirAWS));

		job.waitForCompletion(true);
	}
}
