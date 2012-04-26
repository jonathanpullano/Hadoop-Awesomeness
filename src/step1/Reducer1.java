package step1;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import constants.Constants;

public class Reducer1 extends Reducer<IntWritable, IntWritable, IntWritable, Tuple>{
	@Override
	/**
	 * At the moment, this does nothing but emit what it's given
	 */
	protected void reduce(IntWritable key, Iterable<IntWritable> values,
			Reducer<IntWritable, IntWritable, IntWritable, Tuple>. Context context)
					throws IOException, InterruptedException {
	    DisjointSet set = new DisjointSet(Constants.groupSize);
	    ArrayList<Integer> memory = new ArrayList<Integer>();
	    int len = Constants.groupLength;
        int height = Constants.M;
	    
	    for(IntWritable value : values) {
	        int p = value.get();
	        while(memory.size() > 0 && p - memory.get(0) < height) {
	            memory.remove(0);
	        }
	        
	        if(p / len != 0) {
	            
	        }
	            
	        if(p / len != height - 1) {
	            
	        }
	        
	        if(p % len != 0) {
	            
	        }
	        
	        if(p % len != len-1) {
	        
	        }
	        memory.add(p);
	    }
	    for(IntWritable value : values)
	        context.write(key, new Tuple(value.get(), set.find(value.get())));
	    //DisjointSet set = new DisjointSet(Constants.groupLength * Constants.M);
	    
//	    for (IntWritable value : values) {
//	    	int k = key.get();
//			context.write(new IntWritable(k*5),value);
//		}
	}
}
