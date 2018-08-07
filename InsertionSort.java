//09/01/2017    Surya Kiran Divakaruni Venkata(800975137)
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class InSort {
	public static void main(String[] args) {
		String fileName =args[0];
		BufferedReader br = null;
		try{
//reading file
			
br = new BufferedReader(new FileReader(fileName));		
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    String[] numArr = everything.split(";");
		    
//converting into int
		    ArrayList<Integer> nums = new ArrayList<Integer>();
		    
//sorting while adding to list
		    for (String num:numArr){
		    	int key = Integer.parseInt(num.trim());
		    	nums.add(key);
		    	if(nums.size()>1){
		    		int i =0;
		    		for (i=nums.size()-2;i>=0;i--) {		    			
		    			if(nums.get(i)>key){
		    				nums.set(i+1,nums.get(i));
		    			}else{
		    				break;
		    			}
		    		}
		    		nums.set(i+1,key);
		    	}
		    }

 //writing to file
		    PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
		    String result = "";

		    for (int i=0; i<nums.size(); i++) {
		    	if(i == nums.size() -1){
		    		result += nums.get(i);
		    	}else{
		    		result += nums.get(i)+"; ";
		    	}
		    	
		    }
		    writer.println(result);
	        writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{			
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
