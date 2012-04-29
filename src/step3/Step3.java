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

public class Step3 {
    public void run(final String inputFile) throws Exception {

        final Configuration conf = new Configuration();

        final Job job = new Job(conf, "Step3");
        job.setJarByClass(Step3.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Tuple.class);

        job.setMapperClass(Map3.class);
        // job.setReducerClass(Reducer3.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.addInputPath(job, new Path(inputFile));

        Util.deleteDir(Constants.reducer3OutputDir);
        FileOutputFormat.setOutputPath(job, new Path(
                Constants.reducer3OutputDir));

        job.waitForCompletion(true);
    }

    public static void main(final String[] args) throws Exception {
        final Step3 step = new Step3();
        step.run("data/step3/input/red2Output.txt");
    }
}
