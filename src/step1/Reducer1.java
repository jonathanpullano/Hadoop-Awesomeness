package step1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.MatrixUtilities;
import structures.Tuple;
import constants.Constants;

public class Reducer1 extends
        Reducer<IntWritable, IntWritable, IntWritable, Tuple> {
    
    DisjointSet set;
    HashSet<Integer> memory;
    final int height = Constants.M;
    
    @Override
    protected void reduce(
            final IntWritable key,
            final Iterable<IntWritable> values,
            final Reducer<IntWritable, IntWritable, IntWritable, Tuple>.Context context)
            throws IOException, InterruptedException {
        
        set = new DisjointSet(Constants.groupSize);
        memory = new HashSet<Integer>(Constants.groupSize);
        
        int len;
        final int group = key.get();
        if(group == Constants.numGroups - 1)
            len = Constants.g;
        else 
            len = Constants.g + 1;
        
        //Pass 1 - Build a memory to "sort" the values
        for(IntWritable value : values) {
            memory.add(value.get());
        }
        
        int minP = MatrixUtilities.minInGroup(group);
        int maxP = MatrixUtilities.maxInGroup(group);

        //Pass 2 - Unions
        for(int p = minP; p <= maxP; p++) {
            if(!memory.contains(p))
                continue;
        
            if ((p > height) && memory.contains(p - height)) // left
                set.union(p, p - height);
            
            if ((p % height != 1) && memory.contains(p-1)) // bottom
                set.union(p, p - 1);
        }
        
        //Pass 3 - Find and output
        for(int p = minP; p <= maxP; p++) {
            if(!memory.contains(p))
                continue;
            
            context.write(key, new Tuple(p, set.find(p)));
        }
        memory.clear();
    }
}
