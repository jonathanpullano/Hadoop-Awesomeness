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
import structures.Tuple;
import test.Util;
import constants.Constants;

public class Step3 {
    boolean setReducer = true;
    
    public void disableReducer() {
        setReducer = false;
    }
    
    public void run() throws Exception {
        Configuration conf = new Configuration();
        Job job = new Job(conf, "Step3");
        
        job.setJarByClass(Step3.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Tuple.class);

        job.setMapperClass(Map3.class);
        if(setReducer)
            job.setReducerClass(Reducer3.class);
        
        job.setPartitionerClass(TupleGroupPartitioner.class);

        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        for(String filename : Util.getReducerOutputs(Constants.reducer1OutputDir))
            FileInputFormat.addInputPath(job, new Path(Constants.reducer1OutputDir + "/" + filename));
        //FileInputFormat.addInputPath(job, new Path());

        Util.deleteDir(Constants.reducer3OutputDir);
        FileOutputFormat.setOutputPath(job, new Path(
                Constants.reducer3OutputDir));

        job.waitForCompletion(true);
    }
}
