package step2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.Tuple;

public class MapTest extends Mapper<LongWritable, Text, IntWritable, Tuple> {
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();

	@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			String[] line = value.toString().split(" ");
			//StringTokenizer tokenizer = new StringTokenizer(line);
//			while (tokenizer.hasMoreTokens()) {
//				word.set(tokenizer.nextToken());
//				context.write(word, one);
//			}
			
			//context.write(new IntWritable(Integer.parseInt(line[0])), new IntWritable(Integer.parseInt(line[1])));
			Tuple tuple = new Tuple(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
			context.write(new IntWritable(42), tuple);
	}
}
