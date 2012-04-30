package step3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import structures.MatrixUtilities;
import structures.Tuple;
import constants.Constants;

public class Map3 extends Mapper<LongWritable, Text, IntWritable, Tuple> {

    private final Text red1Word_g = new Text();
    private final Text red1Word_p = new Text();
    private final Text red1Word_q = new Text();
    // private static Queue<Tuple> queue = null;
    Scanner scanner = null;
    int red2_p, red2_q;

    private void init() throws FileNotFoundException {
        if (scanner != null) return;
        scanner = new Scanner(new File(Constants.reducer2OutputDir + "/part-r-00000")); //TODO: Update to use output dir
        readLine2();
    }

    @Override
    public void map(final LongWritable key, final Text value,
            final Context context) throws IOException, InterruptedException {
        init();
        final String line = value.toString();
        final StringTokenizer tokenizer = new StringTokenizer(line);
        int red1_g, red1_p, red1_q;
        red1Word_g.set(tokenizer.nextToken());
        red1Word_p.set(tokenizer.nextToken());
        red1Word_q.set(tokenizer.nextToken());

        red1_g = Integer.parseInt(red1Word_g.toString());
        red1_p = Integer.parseInt(red1Word_p.toString());
        red1_q = Integer.parseInt(red1Word_q.toString());
        
        if (MatrixUtilities.isBoundary(red1_p) 
                && MatrixUtilities.getColumnGroup(Constants.M, Constants.g, 
                   MatrixUtilities.getColumn(Constants.M, red1_p)) == red1_g + 1) 
            return;
                
        
        
        if(red1_p == red2_p) {
            context.write(new IntWritable(red1_g), new Tuple(red2_p, red2_q));
            readLine2();
        } else {
            context.write(new IntWritable(red1_g), new Tuple(red1_p, red1_p));
        }
    }
    
    public void readLine2() {
        if(scanner.hasNext()) {
            red2_p = scanner.nextInt();
            red2_q = scanner.nextInt();
        } else {
            red2_p = -1;
            red2_q = -1;
        }
    }
}