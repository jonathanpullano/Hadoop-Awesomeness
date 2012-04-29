package step3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import step1.GroupPartitioner;
import test.Util;

public class Step3 {

    public void run(final String inputPath) throws Exception {
        final Configuration conf = new Configuration();

        final Job job = new Job(conf, "Step3");
        job.setJarByClass(Step3.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(Map3.class);
        job.setReducerClass(Reducer3.class);

        job.setPartitionerClass(GroupPartitioner.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(inputPath));
        Util.deleteDir("data/output/");
        FileOutputFormat.setOutputPath(job, new Path("data/output/"));

        job.waitForCompletion(true);
    }
}
