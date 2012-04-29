package step3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.MatrixUtilities;
import structures.Tuple;
import constants.Constants;

public class Reducer3 extends
        Reducer<IntWritable, Tuple, IntWritable, IntWritable> {
    
    DisjointSet set = new DisjointSet(Constants.groupSize);
    HashSet<Integer> pointMemory = new HashSet<Integer>();
    HashMap<Integer, Integer> boundaryMemory = new HashMap<Integer, Integer>();
    final int height = Constants.M;
    
    @Override
    protected void reduce(
            final IntWritable key,
            final Iterable<Tuple> values,
            final Reducer<IntWritable, Tuple, IntWritable, IntWritable>.Context context)
            throws IOException, InterruptedException {
        
        set = new DisjointSet(Constants.groupSize);
        pointMemory = new HashSet<Integer>(Constants.groupSize);
        boundaryMemory = new HashMap<Integer, Integer>(Constants.groupSize);
        
        int len;
        final int group = key.get();
        if(group == Constants.g)
            len = Constants.g;
        else 
            len = Constants.g + 1;
        
        //Pass 1 - Build a memory to "sort" the values
        Iterator<Tuple> iter = values.iterator();
        int prevP = iter.next().getFirst();
        pointMemory.add(prevP);
        for(Tuple value : values) {
            int curP = value.getFirst();
            if(curP == prevP)
                boundaryMemory.put(value.getFirst(), value.getSecond());
            else
                pointMemory.add(value.getFirst());
        }
        
        int minP = MatrixUtilities.minInGroup(group);
        int maxP = MatrixUtilities.maxInGroup(group);

        //Pass 2 - Unions
        for(int p = minP; p <= maxP; p++) {
            if(!pointMemory.contains(p))
                continue;
        
            if ((p > height) && pointMemory.contains(p - height)) // left
                set.union(p, p - height);
            
            if ((p % len != 1) && pointMemory.contains(p-1)) // bottom
                set.union(p, p - 1);
            
            if(boundaryMemory.containsKey(p))
                set.union(p, boundaryMemory.get(p));
        }
        
        //Pass 3 - Find and output
        for(int p = minP; p < maxP; p++) {
            if(!pointMemory.contains(p))
                continue;
            
            context.write(new IntWritable(p), new IntWritable(set.find(p)));
        }
        pointMemory.clear();
        boundaryMemory.clear();
    }
}
