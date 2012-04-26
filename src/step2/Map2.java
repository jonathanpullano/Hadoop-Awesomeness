package step2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Map2 extends Mapper<IntWritable, Text, IntWritable, IntWritable> {
	private Text word = new Text();

}
