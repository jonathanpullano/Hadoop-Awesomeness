package step2;

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

public class Step2 {
    public void run(final String inputFile) throws Exception {
        final Configuration conf = new Configuration();

        final Job job = new Job(conf, "Step2");
        job.setJarByClass(Step2.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.setMapperClass(Map2.class);
        job.setReducerClass(Reducer2.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(inputFile));
        Util.deleteDir(Constants.outputDir);
        FileOutputFormat.setOutputPath(job, new Path(Constants.outputDir));

        job.waitForCompletion(true);
    }

    public static void main(final String[] args) throws Exception {
        final Step2 step = new Step2();
        step.run("data/step2/test1.txt");
    }
}
