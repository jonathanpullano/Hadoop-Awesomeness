package step3;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.MatrixUtilities;

import constants.Constants;

/**
 * 
 * @author chris d Reads in float values. Checks whether each value is in range.
 *         If it is, it assigns it a int representing its position (p) which is the key and a
 *         column group (G).
 */
public class Map3 extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
    private final Text word = new Text();

    @Override
    public void map(final LongWritable key, final Text value,
            final Context context) throws IOException, InterruptedException {

    }
}