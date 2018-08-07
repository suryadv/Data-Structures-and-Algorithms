//Surya Kiran Divakaruni Venkata,800975137
package qst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class Qsort {
	
	public static void main(String[] args) {
		
		try {
			//create a file for answer
			File file = new File("answer.txt");
			if(file.createNewFile()) {
				System.out.println("File is created!");
			}else{
				System.out.println("File already exists.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//for loop for number of arguments supplied
		for(int i=0;i<args.length;i++) {
			String fileName = args[i];
			
			try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {     
			    String line;
			    ArrayList<Double> intArr = new ArrayList<Double>();
			    
			    //reading input line by line
			    while ((line = br.readLine()) != null) {
			    	if(!"".equals(line)) {
			    		
			    		String[] arr = line.split(";");
			    		
			    		//reading values into arraylist
			    		for(String s: arr) {
			    			s = s.trim();
			    			if(!"".equals(s)) {		    				
			    				intArr.add(Double.parseDouble(s));
			    			}
			    		}
			    	}
			    }
			    
			 
			    Double[] arr = intArr.toArray(new Double[intArr.size()]);
			    
			    long startTime = System.currentTimeMillis();
			    
			    int high = arr.length-1;
			    int low = 0;
			    
			    //quick sort
			    (new Qsort()).qukSort(arr, low, high);
			    
			    long endTime   = System.currentTimeMillis();
				long totalTime = (endTime - startTime);
			    System.out.println("Time taken: "+totalTime);
			    System.out.println("After sort");
			    		   
			    
			    //writing to file
			    String answer = "";
			    answer+="The sorted array is: \n";
			    answer+=Arrays.toString(arr)+"\n";
			    answer+="Performance Analysis: \n";
			    answer+="Input file: " + fileName+"\n";
			    answer+="Size: " + arr.length +"\n";
			    answer+="Sorting Time: " + totalTime + " milllisecond(s)\n";
			    
			    try {
			        Files.write(Paths.get("answer.txt"), answer.getBytes(), StandardOpenOption.APPEND);
			    }catch (Exception e) {
			        e.printStackTrace();
			    }
			    
			    
			}catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	
	//recursively calling partition and qucksort functions
	public void qukSort(Double[] a, int low, int high) {
		if(low<high) {
			int mid = partition(a,low,high);
			qukSort(a,low,mid-1);
			qukSort(a,mid+1,high);
		}
	}
	
	
	//partitioning the array around pivot
	public int partition(Double[] arr, int low, int high) {
		Double pivot = arr[high];
		int i = low-1;
		for(int j=low;j<high;j++) {
			if(arr[j]<=pivot) {
				i++;
				
				//swap
				Double temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			
			}
		}
		Double temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		return i+1;
	}
	
	
	
}
