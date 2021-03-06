package step2;

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

public class HadoopStep2 {
    public void run() throws Exception {
        final Configuration conf = new Configuration();

        final Job job = new Job(conf, "Step2");
        job.setJarByClass(HadoopStep2.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Tuple.class);

        job.setMapperClass(Map2.class);
        job.setReducerClass(Reducer2.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        // TODO: Consider interleaving on reducer2
        /*
         * for (final String filename : Util.getReducerOutputs( Constants.reducer1OutputDir, "")) {
         * FileInputFormat.addInputPath(job, new Path( Constants.reducer1OutputDir + "/" +
         * filename)); // FileInputFormat.addInputPath(job, new //
         * Path(Constants.reducer1OutputDir)); }
         */

        FileInputFormat.addInputPath(job, new Path(Constants.bucket
                + Constants.reducer1OutputDirAWS));

        FileOutputFormat.setOutputPath(job, new Path(Constants.bucket
                + Constants.reducer2OutputDirAWS));

        job.waitForCompletion(true);
    }
}
