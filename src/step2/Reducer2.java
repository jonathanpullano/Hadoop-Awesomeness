package step2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import constants.Constants;

public class Reducer2 extends Reducer<IntWritable, Tuple, IntWritable, Tuple>{
	
	@Override
	protected void reduce(IntWritable key, Iterable<Tuple> tuples,
			Reducer<IntWritable, Tuple, IntWritable, Tuple>. Context context)
					throws IOException, InterruptedException {
	    DisjointSet set = new DisjointSet((2*Constants.g - 2)*Constants.M);
	    
	    Iterator<Tuple> it = tuples.iterator();
	    Tuple prev = null;
	    
	    for (;it.hasNext();) {
	    	Tuple tuple = it.next();
	    	
	    	System.out.println("Working with tuple (" + tuple.getP() + ", " + tuple.getQ() + ")");
	    	
	    	if(prev != null && prev.getP() == tuple.getP()){
	    		set.union(tuple.getP(), tuple.getQ());
	    		set.union(prev.getP(), prev.getQ());
	    		context.write(key, new Tuple(tuple.getP(), set.find(tuple.getP())));
	    	}	    	
	    	prev = tuple;
		}
	}
}
