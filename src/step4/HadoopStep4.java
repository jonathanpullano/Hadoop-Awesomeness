package step4;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import constants.Constants;

public class HadoopStep4 {

    public void run() throws Exception {
        final Configuration conf = new Configuration();

        final Job job = new Job(conf, "HadoopStep4");
        job.setJarByClass(HadoopStep4.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(Map4.class);
        job.setReducerClass(Reducer4.class);

        // job.setPartitionerClass(GroupPartitioner.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(Constants.bucket
                + Constants.reducer3OutputDirAWS));

        FileOutputFormat.setOutputPath(job, new Path(Constants.bucket
                + Constants.reducer4OutputDirAWS));

        job.waitForCompletion(true);
    }
}