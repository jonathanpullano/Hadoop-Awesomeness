package step2;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import structures.DisjointSet;
import structures.Tuple;
import constants.Constants;

public class Reducer2 extends Reducer<IntWritable, Tuple, IntWritable, IntWritable>{
	
	@Override
	protected void reduce(IntWritable key, Iterable<Tuple> tuples,
			Reducer<IntWritable, Tuple, IntWritable, IntWritable>. Context context)
					throws IOException, InterruptedException {
	    DisjointSet set = new DisjointSet((2*Constants.g - 2)*Constants.M);
	    
	    Iterator<Tuple> it = tuples.iterator();
	    Tuple prev = it.next();
	    
	    for (;it.hasNext();) {
	    	Tuple tuple = it.next();
	    	if(prev.getP() == tuple.getP()){
	    		set.union(tuple.getP(), tuple.getQ());
	    		set.union(prev.getP(), prev.getQ());
	    	}
	    	prev = tuple;
		}
	    
	    Iterator<Tuple> it2 = tuples.iterator();
	    prev = it.next();
	    
	    for (;it2.hasNext();) {
	    	Tuple tuple = it2.next();
	    	if(prev.getP() == tuple.getP()){
	    		context.write(new IntWritable(set.find(tuple.getP())), new IntWritable(tuple.getQ()));
	    		context.write(new IntWritable(set.find(prev.getP())), new IntWritable(prev.getQ()));
	    	}
	    	prev = tuple;
		}
	}
}
