package step1;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import constants.Constants;

public class Reducer1 extends
        Reducer<IntWritable, IntWritable, IntWritable, Tuple> {
    
    DisjointSet set;
    ArrayList<Integer> memory = new ArrayList<Integer>();
    ArrayList<Integer> inputList = new ArrayList<Integer>();
    final int height = Constants.M;
    
    @Override
    /**
     * At the moment, this does nothing but emit what it's given
     */
    protected void reduce(
            final IntWritable key,
            final Iterable<IntWritable> values,
            final Reducer<IntWritable, IntWritable, IntWritable, Tuple>.Context context)
            throws IOException, InterruptedException {
        
            int len;
            int group = values.iterator().next().get();
            final int p = key.get();
            inputList.add(p);
            if(p-1 % Constants.groupSize == 0) {
                set = new DisjointSet(Constants.groupSize);
            }

            if ((group == 0) || (group == Constants.g - 1))
                len = Constants.groupLength + 1;
            else 
                len = Constants.groupLength + 2;
            while ((memory.size() > 0) && (p - memory.get(0) > height))
                memory.remove(0);

            if ((p > height) && (memory.size() > 0)
                    && memory.contains(p - height)) // left
                set.union(p, p - height);
            
            if ((p % len != 0) && (memory.size() > 0)
                    && (memory.get(memory.size() - 1) == p - 1)) // bottom
                set.union(p, p - 1);
            
            memory.add(p);
            
            if(p - 1 % Constants.groupSize == Constants.groupSize - 1) {
                memory.clear();
                for(Integer value : inputList) {
                    context.write(new IntWritable(value), new Tuple(group, set.find(value)));
                }
                inputList.clear();
            }
    }
}
