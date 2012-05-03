package step3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.Tuple;

public class HadoopMap3 extends Mapper<LongWritable, Text, IntWritable, Tuple> {

	private final Text red1Word_g = new Text();
	private final Text red1Word_p = new Text();
	private final Text red1Word_q = new Text();
	private static HashMap<Integer, Integer> red2Map = new HashMap<Integer, Integer>();
	Scanner scanner = null;
	int red2_p, red2_q;

	private void init() throws FileNotFoundException {
		if (scanner != null) {
			return;
		}
		final String file_name = "https://s3.amazonaws.com/edu-cornell-cs-cs5300s12-cgd36-test/outputStep2/part-r-00000";
		// new File(Constants.bucket + Constants.reducer2OutputDirAWS +
		// "/part-r-00000"
		scanner = new Scanner(new File(file_name));
		// TODO: Update to use output dir
		while (scanner.hasNext()) {
			red2Map.put(scanner.nextInt(), scanner.nextInt());
		}
	}

	@Override
	public void map(final LongWritable key, final Text value,
			final Context context) throws IOException, InterruptedException {
		init();
		final String line = value.toString();
		final StringTokenizer tokenizer = new StringTokenizer(line);
		@SuppressWarnings("unused")
		int red1_g, red1_p, red1_q;
		red1Word_g.set(tokenizer.nextToken());
		red1Word_p.set(tokenizer.nextToken());
		red1Word_q.set(tokenizer.nextToken());

		red1_g = Integer.parseInt(red1Word_g.toString());
		red1_p = Integer.parseInt(red1Word_p.toString());
		red1_q = Integer.parseInt(red1Word_q.toString());

		context.write(new IntWritable(red1_g), new Tuple(red1_p, red1_p));
		System.out.println(red1_g + " " + red1_p + " " + red1_p);

		if (red2Map.containsKey(red1_p)) {
			final int find = red2Map.get(red1_p);
			context.write(new IntWritable(red1_g), new Tuple(red1_p, find));
			System.out.println(red1_g + " " + red1_p + " " + find);
		}
	}
}