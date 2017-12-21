package hdoop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

 public class TestReducer
 extends Reducer<Text, LabelsDistWritable, Text, LabelsDistWritable> {

	 @Override
	 public void reduce(Text key, Iterable<LabelsDistWritable> values, Context context)
			 throws IOException, InterruptedException {
		 
		 List<LabelsDistWritable> arl = new ArrayList<LabelsDistWritable>();
		 for(LabelsDistWritable ldw:values)
		 {
			 arl.add(ldw);
		 }
		 int k = 10;
		 Collections.sort(arl);
		 Map<String,Integer> labels = new HashMap<String,Integer>();
		 for(int i=0;i<k;i++)
		 {
			 String current_label = arl.get(i).getLabel().toString();
			 if(labels.containsKey(current_label))
			 {
				 labels.put(current_label, labels.get(current_label)+1);
			 }
			 else
			 {
				 labels.put(current_label, 1);
			 }
		 }
		 Iterator<String> it_labels = labels.keySet().iterator();
		 int max_occ=0;
		 String max_label = "";
		 if(it_labels.hasNext())
		 {
		    max_label = it_labels.next();
			max_occ = labels.get(max_label);
		 }
		
		 while(it_labels.hasNext())
		 {
		 	String str = it_labels.next();
			if(labels.get(str)>max_occ)
			{
				max_occ = labels.get(str);
				max_label = str;
			}
		 }
		 /*
		 //Iterate all temperatures for a year and calculate maximum
		 for (FloatWritable value : values) {
			 maxClosePrice = Math.max(maxClosePrice, value.get());
		 }*/
		 
		 //Write output
		 FloatWritable f = new FloatWritable(0);
		 context.write(key, new LabelsDistWritable(f,new Text(max_label)));
	 }
 }

