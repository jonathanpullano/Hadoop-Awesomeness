package test;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class GenInput {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        FileWriter fw = new FileWriter("data/step1/test-sean.txt");
        Integer[] pts = new Integer[]{2,4,8,13,20,29,40,53,68,70,72,57,44,33,35,36,48,61,76};
        ArrayList<Integer> points = new ArrayList<Integer>(Arrays.asList(pts));
        for(int i = 1; i <= 81; i++) {
            if(points.contains(i))
                fw.write("1.000000000\n");
            else
                fw.write("0.000000000\n");
        }
        fw.close();
    }

}
