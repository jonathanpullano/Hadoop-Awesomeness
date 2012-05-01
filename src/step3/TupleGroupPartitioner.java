package step3;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import constants.Constants;

import structures.Tuple;

public class TupleGroupPartitioner extends Partitioner<IntWritable, Tuple> {

    @SuppressWarnings("unused")
    @Override
    public int getPartition(IntWritable key, Tuple value, int numGroups) {
        if(Constants.DEBUG)
            return key.get() % numGroups;
        return (key.get() / numGroups);
    }

}
