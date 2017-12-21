package hdoop;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TestMapper extends Mapper<LongWritable, Text, Text, LabelsDistWritable> {

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		String line = value.toString();
		String[] items = line.split(",");
		
		String test_id = items[0];
		double euc_sum=0;
		//Change for different dataset
		for(int i=1;i<=4;i++)
		{
			euc_sum+=Math.pow(Double.parseDouble(items[i])-Double.parseDouble(items[i+6]),2);
		}
		euc_sum = Math.sqrt(euc_sum);
		float euc_dist = (float)(euc_sum);
		
		context.write(new Text(test_id), new LabelsDistWritable(new FloatWritable(euc_dist),new Text(items[11])));
		
	}
}
