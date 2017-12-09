package knn.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CSVReader {
	public static void main(String[] args)
	{
		//Change for different dataset
		String filePath = "C:\\Users\\Rahul\\Desktop\\Iris_1.csv";
		FileReader fr;
		BufferedReader br = null;
		try{
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String line = "";
		List<String> dataset = new ArrayList<String>();
		try {
			line = br.readLine();
			while(line != null){
				line = br.readLine();
				if(line!=null){
					//String[] features = line.split(",");
					dataset.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Collections.shuffle(dataset);
		
		List<String> train = new ArrayList<String>();
		List<String> test = new ArrayList<String>();
		
		double percent_test = 0.2;
		int i;int test_no = (int)(dataset.size() * percent_test);
		
		for(i=1;i<=test_no;i++)
		{
			test.add(dataset.get(i-1));
		}
		for(;i<=dataset.size();i++)
		{
			train.add(dataset.get(i-1));
		}
		
		System.out.println(train.size());
		System.out.println(test.size());
		
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\Rahul\\Desktop\\foo.out")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int itr1=0;itr1<test.size();itr1++)
		{
			for(int itr2=0;itr2<train.size();itr2++)
			{
				out.println(test.get(itr1) + "," + train.get(itr2));
				out.flush();
			}
		}
		
		/*
		for(String[] s:test)
		{
			Map<Double, String> m = KNeighbors.classify(k,train,s);
			Iterator<Double> it = m.keySet().iterator();
			Map<String,Integer> labels = new HashMap<String,Integer>();
			int c = 0;
			while(it.hasNext() && c<=k)
			{
				Double code = it.next();
				String current_label = m.get(code);
				System.out.print("(" + code + ":" + current_label + ") ");
				if(labels.containsKey(current_label))
				{
					labels.put(current_label, labels.get(current_label)+1);
				}
				else
				{
					labels.put(current_label, 1);
				}
				
				c++;
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
			
			System.out.println("\n" + max_label + " " + s[45]);
			
		}*/
		
	}
}
